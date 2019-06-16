package eu.happybit.konchris.koncrisbackend.controller;

import eu.happybit.konchris.koncrisbackend.entity.User;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import eu.happybit.konchris.koncrisbackend.service.UserDTO;
import eu.happybit.konchris.koncrisbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "*")
public class ProfilesController {

  @Autowired UserRepository userRepository;
  @Autowired UserService userService;

  @GetMapping("/")
  public Iterable<User> profiles() {
    return userRepository.findAll();
  }

  @GetMapping("/{usernameOrEmail}")
  public ResponseEntity<User> userById(@PathVariable("usernameOrEmail") String usernameOrEmail) {
    Optional<User> optUser = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    return optUser
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
  }

  @PatchMapping("/{usernameOrEmail}")
  public User patchUser(
      @PathVariable("usernameOrEmail") String usernameOrEmail, @RequestBody User patch) {
    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();

    if (patch.getFirstName() != null) {
      user.setFirstName(patch.getFirstName());
    }

    if (patch.getLastName() != null) {
      user.setLastName(patch.getLastName());
    }

    return userRepository.save(user);
  }

  @PostMapping("/")
  public User saveAccount(@Valid @RequestBody UserDTO userDTO) {
    return userService.updateuser(
        userDTO.getEmail(),
        userDTO.getFirstName(),
        userDTO.getLastName(),
        userDTO.getUsername(),
        userDTO.getPhone(),
        userDTO.getAddress());
  }
}
