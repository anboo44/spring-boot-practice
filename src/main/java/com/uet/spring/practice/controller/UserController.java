package com.uet.spring.practice.controller;

import com.uet.spring.practice.exception.NotFoundUserException;
import com.uet.spring.practice.model.user.User;
import com.uet.spring.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> list() {
        return userService.getAll();
    }

    @GetMapping("/{name}")
    public Optional<User> getByName(@PathVariable("name") String name) {
        return userService.getByName(name);
    }

    @PostMapping("")
    public void create(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("")
    public void update(@RequestBody User user) throws NotFoundUserException {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int userId) {
        userService.delete(userId);
    }

    //============[ Only Local ]=========================

    @Profile("local")
    @PostConstruct
    private void initData() {
        System.out.println("============[ Init User List ]=======================");

        var user1 = new User(10, "Hung_Pham", "SIX");
        var user2 = new User(11, "Will_Smith", "SEVEN");
        var user3 = new User(12, "Alice", "NINE");

        var list = List.of(user1, user2, user3);
        list.forEach(user -> userService.save(user));

        System.out.println("============[ Finish User Initialization ]=================");
    }

    @Profile("local")
    @PreDestroy
    private void shutdownApp() {
        System.out.println("===========[ App is shutdown ]======================");
    }
}
