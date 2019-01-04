package eu.happybit.konchris.koncrisbackend.repository;

import eu.happybit.konchris.koncrisbackend.entity.Role;
import eu.happybit.konchris.koncrisbackend.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName roleName);
}
