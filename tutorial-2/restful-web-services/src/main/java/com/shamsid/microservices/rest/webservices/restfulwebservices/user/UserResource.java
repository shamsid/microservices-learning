package com.shamsid.microservices.rest.webservices.restfulwebservices.user;

import com.shamsid.microservices.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class UserResource {

  @Autowired UserDaoService userDaoService;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userDaoService.findAll();
  }

  @GetMapping("/users/{id}")
  public User getUser(@PathVariable(name = "id") Integer id) {
    User user = userDaoService.findOne(id);
    if (Objects.isNull(user)) {
      throw new UserNotFoundException(String.format("No user exists with id %s", id));
    }
    return user;
  }

  @PostMapping("/users")
  public ResponseEntity<?> saveUser(@RequestBody User user) {
    Integer userId = userDaoService.save(user);
    user.setId(userId);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(userId)
            .toUri();
    return ResponseEntity.created(location).body(user);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Integer id) {
    userDaoService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
