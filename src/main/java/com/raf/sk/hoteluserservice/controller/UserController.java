package com.raf.sk.hoteluserservice.controller;

import com.raf.sk.hoteluserservice.domain.User;
import com.raf.sk.hoteluserservice.dto.*;
import com.raf.sk.hoteluserservice.security.CheckSecurity;
import com.raf.sk.hoteluserservice.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get all users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorization,
                                                     Pageable pageable) {

        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}/discount")
    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findDiscount(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Register Client")
    @PostMapping("/client")
    public ResponseEntity<UserCreateDto> saveClient(@RequestBody @Valid ClientCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.registerClient(userCreateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Register Manager")
    @PostMapping("/manager")
    public ResponseEntity<UserCreateDto> saveManager(@RequestBody @Valid ManagerCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.registerManager(userCreateDto), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Request modify User")
    @GetMapping("/{id}/modify")
    public ResponseEntity<UserModifyRequestDto> modifyRequest(@PathVariable("id") Long id){
        return  new ResponseEntity<>(userService.requestModify(id),HttpStatus.OK);
    }

    @ApiOperation(value = "User made credientials changes")
    @PutMapping("/modify/save")
    public  ResponseEntity<UserDto> saveUserChanges(@RequestBody @Valid UserModifyResponseDto userModifyResponseDto){
        return  new ResponseEntity<>(userService.modifyUser(userModifyResponseDto),HttpStatus.OK);
    }

    @ApiOperation(value = "Verify an User")
    @GetMapping("/verify/{id}")
    public ResponseEntity<UserDto> verifyUser(@PathVariable("id") Long id){
        return  new ResponseEntity<>(userService.verifyUser(id),HttpStatus.OK);
    }


    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }


    @ApiOperation(value = "Restrain user Access")
    @PutMapping("/access")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public  ResponseEntity<AccessResponseDto> accessMOdify(@RequestHeader("Authorization") String authorization,
        @RequestBody @Valid AccessRequestDto accessRequestDto){
        return new ResponseEntity<>(userService.restrainAccess(accessRequestDto), HttpStatus.OK);
    }


    @ApiOperation(value = "List of managers for hotel")
   @GetMapping("/manager/hotel")
   public  ResponseEntity<ManagerResponseDto> hotelsManagers(@RequestBody @Valid ManagerRequestDto managerRequestDto){
       return new ResponseEntity<>(userService.hotelManagers(managerRequestDto), HttpStatus.OK);
   }

    @ApiOperation(value = "Find user by id")
    @GetMapping("/user/{id}")
    public  ResponseEntity<UserDto> findUserById(@PathVariable("id") long id){
        return new ResponseEntity<UserDto>(userService.findUserById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Reset password for user")
    @PutMapping("/resetpassword")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordRequestDto resetPasswordRequestDto){
        userService.resetpass(resetPasswordRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
