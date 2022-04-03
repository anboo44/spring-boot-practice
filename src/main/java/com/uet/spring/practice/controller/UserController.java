package com.uet.spring.practice.controller;

import com.uet.spring.practice.exception.NotFoundUserException;
import com.uet.spring.practice.model.tag.Description;
import com.uet.spring.practice.model.tag.Tag;
import com.uet.spring.practice.model.user.User;
import com.uet.spring.practice.repository.TagRepository;
import com.uet.spring.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TagRepository tagRepository;

    //xml response doesn't apply with `EntityModel`
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestParam("name") Optional<String> nameOpt, @RequestParam("ver") Optional<Integer> verOpt) {
        var response = ResponseEntity.status(200);

        var namedOpt = nameOpt.map(name -> {
            var userOpt = userService.getByName(name, verOpt);

            if (userOpt.isPresent()) {
                return response.body(userOpt.get());
            } else {
                return response.body("");
            }
        });

        if (nameOpt.isPresent()) {
            return namedOpt.get();
        } else {
            return response.body(
                userService.getAll().stream().map(user -> {
                    // hypermedia link
                    var resource = EntityModel.of(user);
                    var selfLink = linkTo(methodOn(this.getClass()).list(Optional.empty(), Optional.empty()));
                    var linkTo = linkTo(methodOn(this.getClass()).get(user.getId()));
                    resource.add(selfLink.withRel("self"), linkTo.withRel("linkTo"));
                    return resource;
                })
            );
        }
    }

    @GetMapping("/{uid}")
    public User get(@PathVariable("uid") int uid) {
        return userService.getById(uid).orElse(null);
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
    @Transactional
    private void initData() {
        System.out.println("============[ Init User List ]=======================");

        var users = userService.getAll();
        if (users.size() == 0) {
            var user1 = new User(10, "Hung_Pham", "SIX");
            var user2 = new User(11, "Will_Smith", "SEVEN");
            var user3 = new User(12, "Alice", "NINE");

            var tag1 = new Tag(Description.SINGER);
            var tag2 = new Tag(Description.TEACHER);
            var tag3 = new Tag(Description.MANAGER);

            tagRepository.saveAll(List.of(tag1, tag2, tag3));

            user1.setTags(List.of(tag1, tag2));
            user2.setTags(List.of(tag3));
            user3.setTags(List.of(tag2));

            var list = List.of(user1, user2, user3);
            list.forEach(user -> userService.save(user));
            System.out.println("============[ Finish User Initialization ]=================");
            System.out.println("============[ Finish Tag Initialization ]=================");
        }
    }

    @Profile("local")
    @PreDestroy
    private void shutdownApp() {
        System.out.println("===========[ App is shutdown ]======================");
    }
}
