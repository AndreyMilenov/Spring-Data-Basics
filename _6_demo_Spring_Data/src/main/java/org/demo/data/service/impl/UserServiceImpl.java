package org.demo.data.service.impl;

import org.demo.data.entities.User;
import org.demo.data.repositories.UserRepository;
import org.demo.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user){
       this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(int id) {

        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        System.out.println("No such user whit given id - " + id);
        return null;
    }
}
