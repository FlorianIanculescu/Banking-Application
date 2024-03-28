package com.spring.bank.service;

import com.spring.bank.dao.UserRepository;
import com.spring.bank.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User theUser) {

        userRepository.save(theUser);
    }
}
