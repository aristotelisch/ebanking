package eu.happybit.konchris.koncrisbackend.service;

import eu.happybit.konchris.koncrisbackend.entity.User;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    user.get().setPhoto(UserService.getGravatarUrl(email));

    userRepository.save(user.get());

    return user.get();
  }

   public static String getGravatarUrl(String email) {
    String baseUrl = "https://www.gravatar.com/avatar/";
    StringBuilder gravatarUrlBuilder = new StringBuilder(baseUrl);
    String emailHash;

    try {
      emailHash = DigestUtils.md5DigestAsHex (new ByteArrayInputStream (email.getBytes ()));
    } catch (IOException ex) {
      emailHash = "";
    }

    gravatarUrlBuilder.append (emailHash);
    return gravatarUrlBuilder.toString();
  }
}
