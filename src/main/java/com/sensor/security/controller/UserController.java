package com.sensor.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sensor.security.dto.user.request.ConfirmChangeUserEmailRequest;
import com.sensor.security.dto.user.request.ModifyDataRequest;
import com.sensor.security.dto.user.response.RegisteredUserResponse;
import com.sensor.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sensor.security.service.IUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;


    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RegisteredUserResponse>> getAllUsers() {
        List<RegisteredUserResponse> registeredUsers = userService.getAllUsers().stream().map(user -> userMapper.userEntityToRegisteredUserResponse(user)).collect(Collectors.toList());
        return new ResponseEntity<>(registeredUsers, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisteredUserResponse> getUserById(
            @PathVariable("userId") Long userId) {
        RegisteredUserResponse registeredUser = userMapper.userEntityToRegisteredUserResponse(userService.getUserById(userId));
        return new ResponseEntity<RegisteredUserResponse>(registeredUser, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RegisteredUserResponse> getUserLoggedInByEmailInToken() {
        RegisteredUserResponse registeredUser = userMapper.userEntityToRegisteredUserResponse(userService.getUserLoggedInByEmailInToken());
        return new ResponseEntity<RegisteredUserResponse>(registeredUser, HttpStatus.OK);

    }

    @PutMapping(path = "/modify-data", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> modifyData(@RequestBody @Valid ModifyDataRequest mdr) {
        this.userService.modifyData(this.userMapper.modifyDataRequestToUserEntity(mdr));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/confirm-data", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> confirmData(@RequestBody @Valid ConfirmChangeUserEmailRequest confirmChangeUserEmailRequest) {
        this.userService.confirmTokenEmailChange(confirmChangeUserEmailRequest.getToken());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
