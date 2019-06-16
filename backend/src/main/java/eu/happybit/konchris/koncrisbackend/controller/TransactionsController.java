package eu.happybit.konchris.koncrisbackend.controller;

import eu.happybit.konchris.koncrisbackend.entity.Transaction;
import eu.happybit.konchris.koncrisbackend.entity.User;
import eu.happybit.konchris.koncrisbackend.payload.TransactionListResponse;
import eu.happybit.konchris.koncrisbackend.payload.TransactionResponse;
import eu.happybit.konchris.koncrisbackend.repository.TransactionsRepository;
import eu.happybit.konchris.koncrisbackend.service.TransactionDTO;
import eu.happybit.konchris.koncrisbackend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionsController {
  @Autowired
  TransactionsRepository transactionsRepository;

  @Autowired
  TransactionService transactionService;


  @GetMapping("/")
  public TransactionListResponse getTransactionList() {
    return new TransactionListResponse (transactionsRepository.findAll ());
  }

  @GetMapping("/{id}")
  public TransactionResponse getTransaction(@PathVariable("id") Long id) {

    return new TransactionResponse (transactionsRepository.findById (id).get());
  }

  @PostMapping("/")
  public Transaction saveAccount(@Valid @RequestBody TransactionDTO transactionDTO) {
    return transactionService.createTransaction(transactionDTO);
  }
}
