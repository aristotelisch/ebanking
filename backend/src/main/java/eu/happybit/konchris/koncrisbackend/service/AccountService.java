package eu.happybit.konchris.koncrisbackend.service;

import eu.happybit.konchris.koncrisbackend.repository.AccountRepository;
import eu.happybit.konchris.koncrisbackend.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class AccountService {

  @Autowired
  AccountRepository accountRepository;
  @Autowired
  TransactionsRepository transactionsRepository;

}
