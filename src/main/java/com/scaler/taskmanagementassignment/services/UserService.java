package com.scaler.taskmanagementassignment.services;

import com.scaler.taskmanagementassignment.Entity.User;
import com.scaler.taskmanagementassignment.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("user service")
public class UserService {
     @Autowired
    private final UserRepo userRepo;

     @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public User authenticate(String username , String password){
        User user = userRepo.findByUsername(username);
        if(user!=null&& passwordEncoder.matches(password,user.getPassword())){
            return user;
        }
        return null;
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);

    }

    public void save(User user) {
        userRepo.save(user);
    }
}
