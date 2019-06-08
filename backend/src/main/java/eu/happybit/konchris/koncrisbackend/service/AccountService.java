package eu.happybit.konchris.koncrisbackend.service;

import eu.happybit.konchris.koncrisbackend.entity.Account;
import eu.happybit.konchris.koncrisbackend.entity.Transaction;
import eu.happybit.konchris.koncrisbackend.repository.AccountRepository;
import eu.happybit.konchris.koncrisbackend.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

  @Autowired
  AccountRepository accountRepository;
  @Autowired
  TransactionsRepository transactionsRepository;

}
