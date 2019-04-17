package eu.happybit.konchris.koncrisbackend.payload;

import eu.happybit.konchris.koncrisbackend.security.UserPrincipal;
import org.springframework.security.core.Authentication;

public class JwtAuthenticationResponse {
  private String accessToken;
  private String tokenType = "Bearer";
  private String usernameOrEmail;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
  private Authentication authentication;
  private UserPrincipal userPrincipal;

  public JwtAuthenticationResponse(
      String accessToken, String usernameOrEmail, Authentication authentication) {
    this.accessToken = accessToken;
    this.usernameOrEmail = usernameOrEmail;
    this.authentication = authentication;
    this.userPrincipal = (UserPrincipal) this.authentication.getPrincipal();
    this.setFirstName(this.userPrincipal.getFirstName());
    this.setLastName(this.userPrincipal.getLastName());
    this.setEmail(this.userPrincipal.getEmail());
    this.setUsername(this.userPrincipal.getUsername());
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsernameOrEmail() {
    return usernameOrEmail;
  }

  public void setUsernameOrEmail(String usernameOrEmail) {
    this.usernameOrEmail = usernameOrEmail;
  }
}
