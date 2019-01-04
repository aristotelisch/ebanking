package eu.happybit.konchris.koncrisbackend;

import eu.happybit.konchris.koncrisbackend.entity.Role;
import eu.happybit.konchris.koncrisbackend.entity.RoleName;
import eu.happybit.konchris.koncrisbackend.entity.User;
import eu.happybit.konchris.koncrisbackend.repository.RoleRepository;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
public class KoncrisbackendApplication implements CommandLineRunner {
  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @PostConstruct
  void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  public static void main(String[] args) {
    SpringApplication.run(KoncrisbackendApplication.class, args);
  }

  @Override
  public void run (String... args) throws Exception {
//    // Create users
//    User simpleUser = new User ();
//    simpleUser.setFirstName ("Jane");
//    simpleUser.setLastName ("Doe");
//    simpleUser.setEmail ("dev@example.com");
//    simpleUser.setUsername ("user");
//    simpleUser.setPassword (passwordEncoder.encode("123456"));
//
//    Set<Role> roleSet = new HashSet<> ();
//    roleSet.add (roleRepository.findByName (RoleName.ROLE_USER).get());
//    simpleUser.setRoles (roleSet);
//    userRepository.save (simpleUser);
  }
}
