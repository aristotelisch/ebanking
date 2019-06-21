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

    Optional<User> user1;
    Optional<User> user2;

    if (userRepository.existsByEmail("dev1@example.com")) {
      user1 = userRepository.findByEmail("dev1@example.com");
    } else {
      user1 = Optional.of(new User());
      user1.get().setFirstName("Dev1");
      user1.get().setLastName("Dev");
      user1.get().setEmail("dev1@example.com");
      user1.get().setUsername("dev1");
      user1.get()
          .setPhoto("https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y");
      user1.get().setPhone("123456789");
      user1.get().setPassword(passwordEncoder.encode("dev"));

      Optional<Role> role = roleRepository.findByName(RoleName.ROLE_ADMIN);
      if (role.isPresent()) {
        Set<Role> roles = new HashSet<>();
        roles.add(role.get());
        user1.get().setRoles(roles);
        userRepository.save(user1.get());
      }
    }
    if (userRepository.existsByEmail("dev2@example.com")) {
      user2 = userRepository.findByEmail("dev2@example.com");
    } else {
      user2 = Optional.of(new User());
      user2.get().setFirstName("Dev2");
      user2.get().setLastName("Dev");
      user2.get().setEmail("dev2@example.com");
      user2.get().setUsername("dev2");
      user2.get()
             .setPhoto("https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y");
      user2.get().setPhone("123456789");
      user2.get().setPassword(passwordEncoder.encode("dev"));

      Optional<Role> role = roleRepository.findByName(RoleName.ROLE_ADMIN);

      if (role.isPresent()) {
        Set<Role> roles = new HashSet<>();
        roles.add(role.get());
        user1.get().setRoles(roles);
        user2.get().setRoles(roles);
        userRepository.save(user1.get());
        userRepository.save(user2.get());
      }
    }


    Account account = new Account();
    Account account2 = new Account();
    Set<Account> user1Accounts = new HashSet<>();

    Account account3 = new Account();
    Account account4 = new Account();
    Set<Account> user2Accounts = new HashSet<>();


    account.setInitialBalance(200.0);
    account.setDescription("Savings account");
    account.setIban("GR1232133123123312312312323331");
    account.setType(AccountType.SAVINGS);

    account2.setInitialBalance(300);
    account2.setDescription("Checking account");
    account2.setIban("GR1232133123123312312312323332");
    account2.setType(AccountType.CHECKING);

    account3.setInitialBalance(500.0);
    account3.setDescription("Savings account");
    account3.setIban("GR1232133123123312312312324441");
    account3.setType(AccountType.SAVINGS);

    account4.setInitialBalance(600);
    account4.setDescription("Checking account");
    account4.setIban("GR1232133123123312312312324442");
    account4.setType(AccountType.CHECKING);

    accountRepository.save(account2);
    accountRepository.save(account);
    user1Accounts.add(account);
    user1Accounts.add(account2);

    accountRepository.save(account3);
    accountRepository.save(account4);
    user2Accounts.add(account3);
    user2Accounts.add(account4);

    if (user1.isPresent()) {
      user1.get().setAccounts(user1Accounts);
      userRepository.save(user1.get());
    }

    if (user2.isPresent()) {
      user2.get().setAccounts(user2Accounts);
      userRepository.save(user2.get());
    }

    // Create initial transactions
    Transaction trs = new Transaction();
    trs.setAccount(account);
    trs.setUser(user1.get());
    trs.setAmount(121.0);
    trs.setSenderName(user1.get().getLastName());
    trs.setReceiver(account2);
    trs.setNote("This is a test note");
    trs.setExternal(false);
    transactionsRepository.save(trs);
  }
}
