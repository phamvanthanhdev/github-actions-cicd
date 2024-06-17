package com.lakesidehotel.LakeSideHotel.controller;

import com.lakesidehotel.LakeSideHotel.exeption.UserAlreadyExistsExeption;
import com.lakesidehotel.LakeSideHotel.model.User;
import com.lakesidehotel.LakeSideHotel.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(User user){
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("Resgistration successfully");
        }catch (UserAlreadyExistsExeption ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
