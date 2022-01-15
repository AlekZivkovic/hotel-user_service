package com.raf.sk.hoteluserservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private TokenService tokenService;

    private UserRepository userRepository;
    private UserRatingRepositroy userStatusRepository;
    private UserMapper userMapper;
    private JmsTemplate notificationQue;
    private ObjectMapper objectMapper;
    private String destinationSendEmailsDestination;
    private String mailPath;

    public UserServiceImpl(TokenService tokenService, UserRepository userRepository
            , UserRatingRepositroy userStatusRepository, UserMapper userMapper,
                           JmsTemplate notificationQue,ObjectMapper objectMapper, @Value("${destination.notification.sendEmail}") String destinationSendEmailsDestination
                               , @Value("${mail.path}") String mailPath) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.userStatusRepository = userStatusRepository;
        this.userMapper = userMapper;
        this.notificationQue = notificationQue;
        this.objectMapper=objectMapper;
        this.destinationSendEmailsDestination=destinationSendEmailsDestination;
        this.mailPath=mailPath;
    }

    @Override
    public UserDto findUserById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent())
            throw new NotFoundException("User not found");

        User user = userOptional.get();
        return userMapper.userToUserDto(user);
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
    public void resetpass(ResetPasswordRequestDto resetPasswordRequestDto) {
        //check if user exists
        User user= userRepository.findUserByUsername(resetPasswordRequestDto.getUsername())
                .orElseThrow(()->new NotFoundException(String
                        .format("User with username: %d not found.", resetPasswordRequestDto.getUsername())));
        //set new password
        user.setPassword(UUID.randomUUID().toString().replace("-", "").substring(0,9));

        //send notification
        try {
            NotificationDto notificationDto= userMapper.userToNotificationDto(user,"TYPE_RESET_PASSWORD");
            notificationDto.setNewPassword(user.getPassword());
            notificationQue.convertAndSend(destinationSendEmailsDestination,objectMapper.writeValueAsString(notificationDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        userRepository.save(user);

        return;
    }

    @Override
    public UserCreateDto registerClient(ClientCreateDto clientCreateDto) {
        User user=userMapper.clientCreateDtoToClient(clientCreateDto);
        //User needs to verific account
        user.setAccess(false);
        userRepository.save(user);
        String link=mailPath.concat("/").concat(user.getId().toString());
        try {
            NotificationDto notificationDto=userMapper.userToNotificationDto(user, "TYPE_VERIFY");
            notificationDto.setVerifyLink(link);
            notificationQue.convertAndSend(destinationSendEmailsDestination,objectMapper.writeValueAsString(notificationDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userMapper.userToUserCreateDto(clientCreateDto.getUsername()) ;
    }

    @Override
    public UserCreateDto registerManager(ManagerCreateDto managerCreateDto) {
        User user=userMapper.managerCreateDtoToManager(managerCreateDto);
        //jos nije registrovan
        user.setAccess(false);
        userRepository.save(user);
        String link=mailPath.concat("/").concat(user.getId().toString());
        try {
            NotificationDto notificationDto=userMapper.userToNotificationDto(user,"TYPE_VERIFY");
            notificationDto.setVerifyLink(link);
            notificationQue.convertAndSend(destinationSendEmailsDestination,objectMapper.writeValueAsString(notificationDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
            throw new AccessDeniedException(String.format("User with username: %s unable to connect. If you didnt verify the account look for sent mail."
                    ,tokenRequestDto.getUsername()));

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

    @Override
    public ManagerResponseDto hotelManagers(String hotelName) {
        List<UserDto> lista= userRepository.findAll().stream()
                .filter(user -> user.getManagersInfo().getHotelName() ==hotelName )
                .map(userMapper::userToUserDto).collect(Collectors.toList());

        return  new ManagerResponseDto(lista);
    }
}
