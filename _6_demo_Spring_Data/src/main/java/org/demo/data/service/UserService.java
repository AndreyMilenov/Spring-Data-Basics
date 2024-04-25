package org.demo.data.service;

import org.demo.data.entities.User;

public interface UserService {
    void register(User user);

    User findUserById(int id);
}
