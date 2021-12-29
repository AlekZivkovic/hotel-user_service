package com.raf.sk.hoteluserservice.service;

import com.raf.sk.hoteluserservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService{
    Page<UserDto> findAll(Pageable pageable);

    DiscountDto findDiscount(Long id);



    ManagerDto addManager(ManagerCreateDto userCreateDto);
    ClientDto addClient(ClientCreateDto userCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    UserDto restrainAccess(UserDto userDto);




}
