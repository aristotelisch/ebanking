package eu.happybit.konchris.koncrisbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
  @SequenceGenerator(name = "account_generator", sequenceName = "account_seq", allocationSize = 50)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @NotBlank
  @Column(name = "iban", updatable = false, nullable = false)
  private String iban;

  private String description;

  private String note;

  @Enumerated(EnumType.STRING)
  private AccountType type;

  @CreationTimestamp private Instant created_at;

  @UpdateTimestamp private Instant updated_at;

  public AccountType getType() {
    return type;
  }

  public void setType(AccountType type) {
    this.type = type;
  }
  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Instant getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Instant created_at) {
    this.created_at = created_at;
  }

  public Instant getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Instant updated_at) {
    this.updated_at = updated_at;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }
}
