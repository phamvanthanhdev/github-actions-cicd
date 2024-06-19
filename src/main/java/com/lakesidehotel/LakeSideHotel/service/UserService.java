package com.lakesidehotel.LakeSideHotel.service;

import com.lakesidehotel.LakeSideHotel.exeption.UserAlreadyExistsExeption;
import com.lakesidehotel.LakeSideHotel.model.Role;
import com.lakesidehotel.LakeSideHotel.model.User;
import com.lakesidehotel.LakeSideHotel.repository.RoleRepository;
import com.lakesidehotel.LakeSideHotel.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Override
    public User registerUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsExeption(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if(theUser != null){
            userRepository.deleteByEmail(email);
        }
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public String loginAccount(String email, String password) {
        User user = getUser(email);
        if(passwordEncoder.encode(password).equals(user.getPassword()))
            return "Login successful!";
        return "Login failed!";
    }
}
