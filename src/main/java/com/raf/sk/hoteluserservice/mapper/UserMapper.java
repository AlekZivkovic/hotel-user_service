package com.raf.sk.hoteluserservice.mapper;

import com.raf.sk.hoteluserservice.domain.ClientsInfo;
import com.raf.sk.hoteluserservice.domain.Credentials;
import com.raf.sk.hoteluserservice.domain.ManagersInfo;
import com.raf.sk.hoteluserservice.domain.User;
import com.raf.sk.hoteluserservice.dto.ClientCreateDto;
import com.raf.sk.hoteluserservice.dto.ManagerCreateDto;
import com.raf.sk.hoteluserservice.dto.UserDto;
import com.raf.sk.hoteluserservice.repository.RoleRepositroy;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private RoleRepositroy roleRepository;

    public UserMapper(RoleRepositroy roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getCredentials().getName());
        userDto.setLastName(user.getCredentials().getLastName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public User clientCreateDtoToClient(ClientCreateDto userCreateDto) {
        User user = new User();

        user.setEmail(userCreateDto.getEmail());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setRole(roleRepository.findRoleByName("ROLE_USER").get());

        //credentials
        Credentials credentials=new Credentials();
        credentials.setName(userCreateDto.getCredentialsDto().getFirstname());
        credentials.setLastName(userCreateDto.getCredentialsDto().getLastName());
        credentials.setBirthday(userCreateDto.getCredentialsDto().getBirthday());
        credentials.setPhoneNumber(userCreateDto.getCredentialsDto().getPhoneNumber());
        user.setCredentials(credentials);

        //ClientsInfo
        ClientsInfo clientsInfo=new ClientsInfo();
        clientsInfo.setNumberOfReservation(0);
        clientsInfo.setPostcard(userCreateDto.getClientsInfoDto().getPostcard());
        user.setClientsInfo(clientsInfo);

        //access
        //mozda moze sa ovim da se igra
        user.setAccess(true);

        return user;
    }

    public  User managerCreateDtoToManager(ManagerCreateDto userCreateDto){
        User user= new User();

        user.setEmail(userCreateDto.getEmail());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setRole(roleRepository.findRoleByName("ROLE_MANAGER").get());

        //credentials
        Credentials credentials=new Credentials();
        credentials.setName(userCreateDto.getCredentialsDto().getFirstname());
        credentials.setLastName(userCreateDto.getCredentialsDto().getLastName());
        credentials.setBirthday(userCreateDto.getCredentialsDto().getBirthday());
        credentials.setPhoneNumber(userCreateDto.getCredentialsDto().getPhoneNumber());
        user.setCredentials(credentials);

        //managerInfo
        ManagersInfo managersInfo=new ManagersInfo();
        managersInfo.setHotelName(userCreateDto.getManagersInfoDto().getHotelName());
        managersInfo.setStartDate(userCreateDto.getManagersInfoDto().getStartDate());

        //access
        user.setAccess(true);
        return  user;
    }
}
