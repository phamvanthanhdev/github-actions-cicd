package com.lakesidehotel.LakeSideHotel.controller;

import com.lakesidehotel.LakeSideHotel.exeption.UserAlreadyExistsExeption;
import com.lakesidehotel.LakeSideHotel.model.LoginRequest;
import com.lakesidehotel.LakeSideHotel.model.User;
import com.lakesidehotel.LakeSideHotel.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("Resgistration successfully");
        }catch (UserAlreadyExistsExeption ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> signin(@RequestBody LoginRequest req){
        String email = req.getEmail();
        String password = req.getPassword();

        String result = userService.loginAccount(email, password);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
