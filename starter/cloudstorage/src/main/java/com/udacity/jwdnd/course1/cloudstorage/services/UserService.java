package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.form.SignupForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }
    public int createUser(SignupForm user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(),encodedSalt);
        return userMapper.createUser(new User(null, user.getUsername(), encodedSalt,hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public int getUserId(Authentication authentication){
        return userMapper.getUser(authentication.getName()).getUserid();
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUser(username) == null;
    }
}
