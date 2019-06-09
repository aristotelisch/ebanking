package eu.happybit.konchris.koncrisbackend.controller;

import eu.happybit.konchris.koncrisbackend.entity.Account;
import eu.happybit.konchris.koncrisbackend.payload.AccountResponse;
import eu.happybit.konchris.koncrisbackend.payload.ApiResponse;
import eu.happybit.konchris.koncrisbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountsController {

  @Autowired
  AccountRepository accountRepository;

  @GetMapping("/{id}")
  public ResponseEntity getAccount(@PathVariable("id") Long id) {
    Optional<Account> account = accountRepository.findById (id);

    if (account.isPresent ()) {
      return ResponseEntity.ok( new AccountResponse(account.get()));
    } else {
      return ResponseEntity.ok(new ApiResponse (false, "Account not found"));
    }
  }
}
