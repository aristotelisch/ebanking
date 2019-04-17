package eu.happybit.konchris.koncrisbackend;

import eu.happybit.konchris.koncrisbackend.entity.Account;
import eu.happybit.konchris.koncrisbackend.entity.Role;
import eu.happybit.konchris.koncrisbackend.entity.RoleName;
import eu.happybit.konchris.koncrisbackend.entity.User;
import eu.happybit.konchris.koncrisbackend.repository.AccountRepository;
import eu.happybit.konchris.koncrisbackend.repository.RoleRepository;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolationException;
import java.util.*;

@SpringBootApplication
public class KoncrisbackendApplication implements CommandLineRunner {

  @Autowired UserRepository userRepository;
  @Autowired AccountRepository accountRepository;
  @Autowired RoleRepository roleRepository;

  @Autowired PasswordEncoder passwordEncoder;

  @PostConstruct
  void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  public static void main(String[] args) {
    SpringApplication.run(KoncrisbackendApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    Optional<User> user;

    if (userRepository.existsByEmail("dev@example.com")) {
      user = userRepository.findByEmail("dev@example.com");
    } else {
      user = Optional.of(new User());
      user.get().setFirstName("John");
      user.get().setLastName("Doe");
      user.get().setEmail("dev@example.com");
      user.get().setUsername("dev");
      user.get().setPassword(passwordEncoder.encode("dev"));

      Optional<Role> role = roleRepository.findByName(RoleName.ROLE_ADMIN);
      if (role.isPresent()) {
        Set<Role> roles = new HashSet<>();
        roles.add(role.get());
        user.get().setRoles(roles);
        userRepository.save(user.get());
      }
    }

    Account account = new Account();
    account.setDescription("My new savings account");
    accountRepository.save(account);
    Set<Account> accounts = new HashSet<>();
    accounts.add(account);
    if (user.isPresent()) {
      user.get().setAccounts(accounts);
      userRepository.save(user.get());
    }
  }
}
