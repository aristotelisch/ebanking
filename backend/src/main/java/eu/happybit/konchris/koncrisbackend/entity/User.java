package eu.happybit.konchris.koncrisbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id @GeneratedValue private Long id;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public String getFirstName () {
    return firstName;
  }

  public void setFirstName (String firstName) {
    this.firstName = firstName;
  }

  public String getLastName () {
    return lastName;
  }

  public void setLastName (String lastName) {
    this.lastName = lastName;
  }

  public String getAddress () {
    return address;
  }

  public void setAddress (String address) {
    this.address = address;
  }

  public String getPhone () {
    return phone;
  }

  public void setPhone (String phone) {
    this.phone = phone;
  }

  public String getPhoto () {
    return photo;
  }

  public void setPhoto (String photo) {
    this.photo = photo;
  }

  public String getEmail () {
    return email;
  }

  public void setEmail (String email) {
    this.email = email;
  }

  public String getUsername () {
    return username;
  }

  public void setUsername (String username) {
    this.username = username;
  }

  public String getPassword () {
    return password;
  }

  public void setPassword (String password) {
    this.password = password;
  }

  public Set<Role> getRoles () {
    return roles;
  }

  public void setRoles (Set<Role> roles) {
    this.roles = roles;
  }

  public Set<Account> getAccounts () {
    return accounts;
  }

  public void setAccounts (Set<Account> accounts) {
    this.accounts = accounts;
  }

  @NotBlank private String firstName;
  @NotBlank private String lastName;
  private String address;
  private String phone;
  private String photo;
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

  @OneToMany(mappedBy = "user",
          cascade = CascadeType.ALL,
          orphanRemoval = true)
  private List<Transaction> transactions = new ArrayList<> ();

  public User() {}

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public User(String firstName, String lastName, String username, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

}
