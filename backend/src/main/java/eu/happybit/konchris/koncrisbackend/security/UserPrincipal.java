package eu.happybit.konchris.koncrisbackend.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.happybit.konchris.koncrisbackend.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
  private Long id;
  private String firstName;
  private String lastName;
  private String username;

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

  @JsonIgnore
  private String email;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public String getEmail () {
    return email;
  }

  public void setEmail (String email) {
    this.email = email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities () {
    return authorities;
  }

  public UserPrincipal(Long id, String firstName, String lastName, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.email = email;
    this.authorities = authorities;
  }

  public static UserPrincipal create(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
            new SimpleGrantedAuthority (role.getName().name())
    ).collect(Collectors.toList ());

    return new UserPrincipal (
            user.getId(),
            user.getFirstName (),
            user.getLastName (),
            user.getUsername (),
            user.getEmail(),
            user.getPassword (),
            authorities
    );
  }

  @Override
  public String getPassword () {
    return this.password;
  }

  @Override
  public String getUsername () {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired () {
    return true;
  }

  @Override
  public boolean isAccountNonLocked () {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired () {
    return true;
  }

  @Override
  public boolean isEnabled () {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserPrincipal that = (UserPrincipal) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
