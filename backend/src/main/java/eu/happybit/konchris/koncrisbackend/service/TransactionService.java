package eu.happybit.konchris.koncrisbackend.service;

import eu.happybit.konchris.koncrisbackend.entity.Transaction;
import eu.happybit.konchris.koncrisbackend.repository.AccountRepository;
import eu.happybit.konchris.koncrisbackend.repository.TransactionsRepository;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class TransactionService {

  @Autowired
  AccountRepository accountRepository;
  @Autowired
  TransactionsRepository transactionsRepository;

  @Autowired
  UserRepository userRepository;

  public Transaction createTransaction(TransactionDTO transactionDTO) {
    Transaction transaction = new Transaction();

    transaction.setAccount(accountRepository.findById ((long) 1).get()); // account_id
    transaction.setSenderName ("Test Name");
    transaction.setNote (transactionDTO.getNote());
    transaction.setReceiver (accountRepository.findById ((long) 2).get()); // Receiver Account id
    transaction.setAmount (transactionDTO.getAmount());
    transaction.setUser (userRepository.findByUsernameOrEmail (transactionDTO.getEmail (), transactionDTO.getEmail ()).get());
    transaction.setExternal (false);
    transactionsRepository.save(transaction);

    return transaction;
  }

}
