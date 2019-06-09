package eu.happybit.konchris.koncrisbackend.payload;

import eu.happybit.konchris.koncrisbackend.security.UserPrincipal;
import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
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

}
