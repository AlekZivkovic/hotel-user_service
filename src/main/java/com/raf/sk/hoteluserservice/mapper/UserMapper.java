package com.raf.sk.hoteluserservice.mapper;

import com.raf.sk.hoteluserservice.domain.ClientsInfo;
import com.raf.sk.hoteluserservice.domain.Credentials;
import com.raf.sk.hoteluserservice.domain.ManagersInfo;
import com.raf.sk.hoteluserservice.domain.User;
import com.raf.sk.hoteluserservice.dto.*;
import com.raf.sk.hoteluserservice.repository.RoleRepositroy;
import com.sun.istack.NotNull;
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
        user.setManagersInfo(managersInfo);
        //access
        user.setAccess(true);
        return  user;
    }

    public UserModifyRequestDto userToUserModifyRequestDto(User user){
        UserModifyRequestDto userModifyDto=new UserModifyRequestDto();

        userModifyDto.setEmail(user.getEmail());
        userModifyDto.setUsername(user.getUsername());
        userModifyDto.setPassword(user.getPassword());

        //credentials
        CredentialsDto credentialsDto=new CredentialsDto();
        credentialsDto.setBirthday(user.getCredentials().getBirthday());
        credentialsDto.setFirstname(user.getCredentials().getName());
        credentialsDto.setLastName(user.getCredentials().getLastName());
        credentialsDto.setPhoneNumber(user.getCredentials().getPhoneNumber());
        userModifyDto.setCredentialsDto(credentialsDto);

        //Clients
        if(user.getClientsInfo() != null) {
            ClientCreateDto.ClientsInfoDto clientsInfoDto = new ClientCreateDto.ClientsInfoDto();
            clientsInfoDto.setPostcard(user.getClientsInfo().getPostcard());
            userModifyDto.setClientsInfo(clientsInfoDto);
        }
        if(user.getManagersInfo() != null){
            ManagerCreateDto.ManagersInfoDto managersInfoDto= new ManagerCreateDto.ManagersInfoDto();
            managersInfoDto.setHotelName(user.getManagersInfo().getHotelName());
            managersInfoDto.setStartDate(user.getManagersInfo().getStartDate());
            userModifyDto.setManagersInfo(managersInfoDto);
        }
        return  userModifyDto;
    }

    public User userModifyResponseDtoToUser(User user,UserModifyResponseDto userModifyDto){
        if(userModifyDto.getUsername().isPresent())
            user.setUsername(userModifyDto.getUsername().get());
        if(userModifyDto.getPassword().isPresent())
            user.setPassword(userModifyDto.getPassword().get());

        if(userModifyDto.getCredentialsDto().isPresent()) {
            //Credentials
            Credentials credentials = new Credentials();
            credentials.setName(userModifyDto.getCredentialsDto().get().getFirstname());
            credentials.setLastName(userModifyDto.getCredentialsDto().get().getLastName());
            credentials.setBirthday(userModifyDto.getCredentialsDto().get().getBirthday());
            credentials.setPhoneNumber(userModifyDto.getCredentialsDto().get().getPhoneNumber());
            user.setCredentials(credentials);
        }
        if(user.getManagersInfo() != null && userModifyDto.getManagersInfo().isPresent()) {
            ManagersInfo managersInfo=new ManagersInfo();
            managersInfo.setHotelName(userModifyDto.getManagersInfo().get().getHotelName());
            managersInfo.setStartDate(userModifyDto.getManagersInfo().get().getStartDate());
            user.setManagersInfo(managersInfo);
        }
        if(user.getClientsInfo() !=null && userModifyDto.getClientsInfoDto().isPresent()){
            ClientsInfo clientsInfo=new ClientsInfo();
            clientsInfo.setPostcard(userModifyDto.getClientsInfoDto().get().getPostcard());
            user.setClientsInfo(clientsInfo);
        }

        return  user;
    }


    public  UserCreateDto userToUserCreateDto(@NotNull String username){
        UserCreateDto userCreateDto=new UserCreateDto();
        userCreateDto.setUsername(username);
        userCreateDto.setMessage("Credentials accepted. Check mail for verification");
        return  userCreateDto;
    }

    public NotificationDto userToNotificationDto(User user){
        NotificationDto notificationDto=new NotificationDto();

        notificationDto.setUserDto(this.userToUserDto(user));

        NotificationTypeDto notificationTypeDto=new NotificationTypeDto();
        notificationTypeDto.setType("TYPE_VERIFY");
        notificationDto.setNotificationType(notificationTypeDto);


        return  notificationDto;
    }


}
