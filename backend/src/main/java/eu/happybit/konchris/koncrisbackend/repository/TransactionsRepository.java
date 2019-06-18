package eu.happybit.konchris.koncrisbackend.repository;

import eu.happybit.konchris.koncrisbackend.entity.Transaction;
import eu.happybit.konchris.koncrisbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findTransactionsByUser(User user);
}
