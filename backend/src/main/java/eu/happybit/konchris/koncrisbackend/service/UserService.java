package eu.happybit.konchris.koncrisbackend.service;

import eu.happybit.konchris.koncrisbackend.entity.User;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public User updateuser(String email, String firstName, String lastName, String username, String phone, String address) {
    Optional<User> user;

    user = userRepository.findByEmail(email);
    user.get().setFirstName(firstName);
    user.get().setLastName(lastName);
    user.get().setEmail(email);
    user.get().setUsername(username);
    user.get().setAddress (address);
    user.get().setPhone(phone);
//      // Create the correct gravatar url to save to the photo field
//      // TODO: theUser.setPhoto();
      userRepository.save(user.get());
      return user.get();
  }
}
