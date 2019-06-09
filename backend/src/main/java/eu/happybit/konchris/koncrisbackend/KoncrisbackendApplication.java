package eu.happybit.konchris.koncrisbackend;

import eu.happybit.konchris.koncrisbackend.entity.*;
import eu.happybit.konchris.koncrisbackend.repository.AccountRepository;
import eu.happybit.konchris.koncrisbackend.repository.RoleRepository;
import eu.happybit.konchris.koncrisbackend.repository.TransactionsRepository;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import eu.happybit.konchris.koncrisbackend.service.AccountService;
import eu.happybit.konchris.koncrisbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
public class KoncrisbackendApplication implements CommandLineRunner {

  @Autowired UserRepository userRepository;
  @Autowired AccountRepository accountRepository;
  @Autowired RoleRepository roleRepository;
  @Autowired UserService userService;
  @Autowired TransactionsRepository transactionsRepository;
  @Autowired AccountService accountService;

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
      user.get()
          .setPhoto("https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y");
      user.get().setPhone("123456789");
      user.get().setPassword(passwordEncoder.encode("dev"));

      Optional<Role> role = roleRepository.findByName(RoleName.ROLE_ADMIN);
      if (role.isPresent()) {
        Set<Role> roles = new HashSet<>();
        roles.add(role.get());
        user.get().setRoles(roles);
        userRepository.save(user.get());
      }
    }
    Account account2 = new Account();
    account2.setInitialBalance(300);
    account2.setDescription("My new checking account");
    account2.setIban("GR1232133123123312312312321666");
    account2.setType(AccountType.CHECKING);
    accountRepository.save(account2);
    Set<Account> accounts = new HashSet<>();
    accounts.add(account2);

    Account account = new Account();
    account.setInitialBalance(200.0);
    account.setDescription("My new savings account");
    account.setIban("GR1232133123123312312312321312");
    account.setType(AccountType.SAVINGS);
    accountRepository.save(account);
    accounts.add(account);
    if (user.isPresent()) {
      user.get().setAccounts(accounts);
      userRepository.save(user.get());
    }

    // Create initial transactions
    Transaction trs = new Transaction();
    trs.setAccount(account);
    trs.setUser(user.get());
    trs.setAmount(121.0);
    trs.setSenderName(user.get().getLastName());
    trs.setReceiver(account2);
    trs.setNote("This is a test note");
    trs.setExternal(false);
    transactionsRepository.save(trs);
  }
}
