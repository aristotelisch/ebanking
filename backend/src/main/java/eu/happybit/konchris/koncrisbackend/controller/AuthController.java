package eu.happybit.konchris.koncrisbackend.controller;

import eu.happybit.konchris.koncrisbackend.entity.Role;
import eu.happybit.konchris.koncrisbackend.entity.RoleName;
import eu.happybit.konchris.koncrisbackend.entity.User;
import eu.happybit.konchris.koncrisbackend.exception.AppException;
import eu.happybit.konchris.koncrisbackend.payload.ApiResponse;
import eu.happybit.konchris.koncrisbackend.payload.JwtAuthenticationResponse;
import eu.happybit.konchris.koncrisbackend.payload.LoginRequest;
import eu.happybit.konchris.koncrisbackend.payload.SignUpRequest;
import eu.happybit.konchris.koncrisbackend.repository.RoleRepository;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import eu.happybit.konchris.koncrisbackend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired AuthenticationManager authenticationManager;

  @Autowired UserRepository userRepository;

  @Autowired RoleRepository roleRepository;

  @Autowired PasswordEncoder passwordEncoder;

  @Autowired JwtTokenProvider tokenProvider;

  @PostMapping("/login")
  public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, loginRequest.getUsernameOrEmail(), authentication));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity(
          new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity(
          new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
    }

    // Creating user's account
    User user =
        new User(
            signUpRequest.getFirstName(),
            signUpRequest.getLastName(),
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            signUpRequest.getPassword());

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    Role userRole =
        roleRepository
            .findByName(RoleName.ROLE_USER)
            .orElseThrow(() -> new AppException("User Role not set."));

    user.setRoles(Collections.singleton(userRole));

    User result = userRepository.save(user);

    URI location =
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/users/{username}")
            .buildAndExpand(result.getUsername())
            .toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "User registered successfully"));
  }
}
