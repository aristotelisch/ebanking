package eu.happybit.konchris.koncrisbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "accounts")
@Data
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
  @SequenceGenerator(name = "account_generator", sequenceName = "account_seq", allocationSize = 50)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private Set<Transaction> transactions;

  @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Transaction> receiverTransactions;

  @NotBlank
  @Column(name = "iban", updatable = false, nullable = false)
  private String iban;

  private String description;

  private String note;

  @Enumerated(EnumType.STRING)
  private AccountType type;

  @CreationTimestamp private Instant created_at;

  @UpdateTimestamp private Instant updated_at;

  @org.hibernate.annotations.ColumnDefault("0.0")
  private double initialBalance;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return id.equals(account.id) || iban.equals(account.iban);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @JsonBackReference
  @ManyToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
  private Set<User> users = new HashSet<>();

  public Account(
      String description, String note, Instant created_at, Instant updated_at, Set<User> users) {
    this.description = description;
    this.note = note;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.users = users;
  }

  public Account() {}

  public Double calculateBalance() {
    Double credit;
    Double debit;

    debit =
        this.getTransactions().stream().map(Transaction::getAmount).reduce(Double::sum).orElse(0.0);
    credit =
        this.getReceiverTransactions().stream()
            .map(Transaction::getAmount)
            .reduce(Double::sum)
            .orElse(0.0);

    return initialBalance + credit - debit;
  }
}
