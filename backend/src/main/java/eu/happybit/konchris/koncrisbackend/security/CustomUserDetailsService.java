package eu.happybit.konchris.koncrisbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import eu.happybit.konchris.koncrisbackend.entity.User;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername (String usernameOrEmail) throws UsernameNotFoundException {
    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
            );

    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException ("User not found with id: " + id)
    );

    return UserPrincipal.create(user);
  }
}
