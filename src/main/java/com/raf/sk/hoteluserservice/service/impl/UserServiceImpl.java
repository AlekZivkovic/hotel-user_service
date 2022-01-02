package com.raf.sk.hoteluserservice.service.impl;

import com.raf.sk.hoteluserservice.domain.User;
import com.raf.sk.hoteluserservice.domain.UserRating;
import com.raf.sk.hoteluserservice.dto.*;
import com.raf.sk.hoteluserservice.exceptions.AccessDeniedException;
import com.raf.sk.hoteluserservice.exceptions.NotFoundException;
import com.raf.sk.hoteluserservice.mapper.UserMapper;
import com.raf.sk.hoteluserservice.repository.UserRatingRepositroy;
import com.raf.sk.hoteluserservice.repository.UserRepository;
import com.raf.sk.hoteluserservice.security.service.TokenService;
import com.raf.sk.hoteluserservice.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private TokenService tokenService;
    private UserRepository userRepository;
    private UserRatingRepositroy userStatusRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, TokenService tokenService, UserRatingRepositroy userStatusRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
        this.userStatusRepository = userStatusRepository;
    }


    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public DiscountDto findDiscount(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        List<UserRating> userStatusList = userStatusRepository.findAll();
        //get discount
        Integer discount = userStatusList.stream()
                .filter(userStatus -> userStatus.getMaxNumberOfReservations() >= user.getClientsInfo().getNumberOfReservation()
                        && userStatus.getMinNumberOfReservations() <= user.getClientsInfo().getNumberOfReservation())
                .findAny()
                .get()
                .getDiscount();
        return new DiscountDto(discount);
    }

    @Override
    public UserCreateDto registerClient(ClientCreateDto clientCreateDto) {
        User user=userMapper.clientCreateDtoToClient(clientCreateDto);
        //User needs to verific account
        user.setAccess(false);
        userRepository.save(user);
        return userMapper.userToUserCreateDto(clientCreateDto.getUsername()) ;
    }

    @Override
    public UserCreateDto registerManager(ManagerCreateDto managerCreateDto) {
        User user=userMapper.managerCreateDtoToManager(managerCreateDto);
        //jos nije registrovan
        user.setAccess(false);
        userRepository.save(user);
        return userMapper.userToUserCreateDto(managerCreateDto.getUsername());
    }

    @Override
    public UserDto verifyUser(Long id) {
        //Za sad dok ne uradimo servis za notifikacije ovo je prazno
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        user.setAccess(true);
        userRepository.save(user);

        return  userMapper.userToUserDto(user);
    }


    @Override
    public UserModifyRequestDto requestModify(Long id) {
        //Try to find user by user id
        User user= userRepository.findById(id).orElseThrow(()-> new NotFoundException(String
                .format("User with id: %d not found",id)));
        UserModifyRequestDto userModifyRequestDto=userMapper.userToUserModifyRequestDto(user);
        return userModifyRequestDto;
    }

    @Override
    public UserDto modifyUser(UserModifyResponseDto userModifyDto) {
        //Try to find user by user id
        User user= userRepository.findById(userModifyDto.getId()).orElseThrow(()-> new NotFoundException(String
                .format("User with id: %d not found",userModifyDto.getId())));
        //Modifying changes to user
        user=userMapper.userModifyResponseDtoToUser(user,userModifyDto);
        //Saving that changes
        userRepository.save(user);
        //sending Updated userDto
        UserDto userDto=userMapper.userToUserDto(user);
        return userDto;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        User user = userRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //See if user has access
        if(!user.getAccess())
            throw new AccessDeniedException(String.format("User with username: %s has access denied.",tokenRequestDto.getUsername()));

        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public AccessResponseDto restrainAccess(AccessRequestDto accessRequestDto) {
        AccessResponseDto accessResponseDto=new AccessResponseDto();
        User user= userRepository.findById(accessRequestDto.getUserId()).orElseThrow(()-> new NotFoundException(String
                .format("User with id: %d not found",accessRequestDto.getUserId())));

        user.setAccess(accessRequestDto.getAccess());
        userRepository.save(user);
        accessResponseDto.setSavedChanges(true);
        return accessResponseDto;
    }
}
