package eu.happybit.konchris.koncrisbackend.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.happybit.konchris.koncrisbackend.entity.Account;
import eu.happybit.konchris.koncrisbackend.entity.AccountType;
import eu.happybit.konchris.koncrisbackend.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@Data
public class AccountResponse {
  private Long id;
  private Double balance;
  private String iban;
  private String description;
  private String note;
  private AccountType type;
  private Instant created_at;
  private Instant updated_at;
  private Double initialBalance;

  public AccountResponse(Account account) {
    id = account.getId();
    balance = account.calculateBalance();
    iban = account.getIban();
    description = account.getDescription();
    note = account.getNote();
    type = account.getType();
    created_at = account.getCreated_at();
    updated_at = account.getUpdated_at();
    initialBalance = account.getInitialBalance();
  }

}
