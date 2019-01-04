package eu.happybit.konchris.koncrisbackend.repository;

import eu.happybit.konchris.koncrisbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsernameOrEmail (String usernameOrEmail, String usernameOrEmail1);
  Optional<User> findByEmail(String email);
  List<User> findByIdIn(List<Long> userIds);
  Optional<User> findByUsername(String username);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}
