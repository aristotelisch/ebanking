package eu.happybit.konchris.koncrisbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id @GeneratedValue private Long id;

  @NotBlank private String firstName;
  @NotBlank private String lastName;
  private String address;
  private String phone;
  @NaturalId @Email @NotBlank private String email;

  @Column(unique = true)
  private String username;

  @NotBlank
  @Size(max = 100)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @JsonManagedReference
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "users_accounts",
          joinColumns = { @JoinColumn(name = "user_id") },
          inverseJoinColumns = { @JoinColumn(name = "account_id") })
  private Set<Account> accounts = new HashSet<>();

  public User() {}

  public User(String firstName, String lastName, String username, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
  }

}
