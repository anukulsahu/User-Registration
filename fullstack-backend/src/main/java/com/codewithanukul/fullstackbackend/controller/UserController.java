package com.codewithanukul.fullstackbackend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.codewithanukul.fullstackbackend.exception.UserNotFoundException;
import com.codewithanukul.fullstackbackend.model.User;
import com.codewithanukul.fullstackbackend.repository.UserRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin("http://localhost:5173")
public class UserController {
  @Autowired
  private UserRepo userRepo;

  @PostMapping("users")
  public String postMethodName(@RequestBody User newUser) {
    userRepo.save(newUser);
    return "Saved Successfully";
  }

  @GetMapping("users")
  List<User> getMethodName() {
    return userRepo.findAll();
  }

  @DeleteMapping("users/{id}")
  public String deleteMethod(@PathVariable Long id) {
    if (!userRepo.existsById(id)) {
      throw new UserNotFoundException(id);
    }
    userRepo.deleteById(id);
    return "User with id " + id + " has been deleted successfully";

  }

  @GetMapping("users/{id}")
  public User getMethodName(@PathVariable Long id) {
    return userRepo.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  @PutMapping("users/{id}")
  public User putMethodName(@RequestBody User newUser, @PathVariable Long id) {
    return userRepo.findById(id)
        .map(user -> {
          user.setUsername(newUser.getUsername());
          user.setPhone(newUser.getPhone());
          user.setEmail(newUser.getEmail());
          return userRepo.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
  }

}
