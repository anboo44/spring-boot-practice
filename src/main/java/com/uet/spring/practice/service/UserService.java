package com.uet.spring.practice.service;

import com.uet.spring.practice.exception.NotFoundUserException;
import com.uet.spring.practice.model.user.User;
import com.uet.spring.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void update(User user) throws NotFoundUserException {
        var userJDBC = userRepository.findById(user.getId());

        if (userJDBC.isEmpty()) {
            throw new NotFoundUserException(String.format("Not Found User with id: %d", user.getId()));
        } else {
            userRepository.save(userJDBC.get().copyFrom(user));
        }
    }

    public void delete(int userId) {
        userRepository.deleteById(userId);
    }
}
