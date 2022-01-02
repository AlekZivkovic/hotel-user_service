package com.raf.sk.hoteluserservice.service;

import com.raf.sk.hoteluserservice.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService{
    Page<UserDto> findAll(Pageable pageable);

    DiscountDto findDiscount(Long id);

    UserCreateDto registerClient(ClientCreateDto clientCreateDto);
    UserCreateDto registerManager(ManagerCreateDto managerCreateDto);

    //treba dodati verifikaciju koja ce pozvati addManager/Client


    //Dobijeni useri ce biti prolsedjeni notifikacionom servicu
    // koji ce da obavesti korisnika da je uspesno kreiran nalog
    UserDto verifyUser(Long id);

    UserModifyRequestDto requestModify(Long id);
    UserDto modifyUser(UserModifyResponseDto userModifyDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    AccessResponseDto restrainAccess(AccessRequestDto accessRequestDto);







}
