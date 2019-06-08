package eu.happybit.konchris.koncrisbackend.repository;

import eu.happybit.konchris.koncrisbackend.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "accounts", path = "/api/accounts")
public interface AccountRepository extends CrudRepository<Account, Long> {

}
