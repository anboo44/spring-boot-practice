package com.uet.spring.practice.service;

import com.uet.spring.practice.exception.NotFoundUserException;
import com.uet.spring.practice.model.user.User;
import com.uet.spring.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getByName(String name, Optional<Integer> verOpt) {
        return verOpt.map(ver -> {
            switch (ver) {
                case 2: return userRepository.findByNameV2(name);
                case 3: return userRepository.findByNameV3(name);

                default: return userRepository.findByName(name);
            }
        }).orElseGet(() -> userRepository.findByName(name));
    }

    public List<User> getAll() {
        return userRepository.findAll(PageRequest.of(0,2)).get().collect(Collectors.toList());
    }

    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
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
