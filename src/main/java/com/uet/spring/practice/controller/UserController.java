package com.uet.spring.practice.controller;

import com.uet.spring.practice.exception.NotFoundUserException;
import com.uet.spring.practice.model.user.User;
import com.uet.spring.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> list(@RequestParam("name") Optional<String> nameOpt) {
        var response = ResponseEntity.status(200);

        var namedOpt = nameOpt.map(name -> {
            var userOpt = userService.getByName(name);

            if (userOpt.isPresent()) {
                return response.body(userOpt.get());
            } else {
                return response.body("");
            }
        });

        if (nameOpt.isPresent()) {
            return namedOpt.get();
        } else {
            return response.body(userService.getAll());
        }
    }

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(201).body(null);
    }

    @PutMapping("")
    public ResponseEntity update(@Valid @RequestBody User user) throws NotFoundUserException {
        userService.update(user);
        return ResponseEntity.status(201).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int userId) {
        userService.delete(userId);
        return ResponseEntity.status(201).body(null);
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
