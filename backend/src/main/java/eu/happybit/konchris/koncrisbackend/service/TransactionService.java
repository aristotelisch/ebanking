package eu.happybit.konchris.koncrisbackend.service;

import com.sendgrid.*;
import eu.happybit.konchris.koncrisbackend.entity.Transaction;
import eu.happybit.konchris.koncrisbackend.repository.AccountRepository;
import eu.happybit.konchris.koncrisbackend.repository.TransactionsRepository;
import eu.happybit.konchris.koncrisbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class TransactionService {

  @Autowired AccountRepository accountRepository;
  @Autowired TransactionsRepository transactionsRepository;

  @Autowired UserRepository userRepository;

  public Transaction createTransaction(TransactionDTO transactionDTO) {
    Transaction transaction = new Transaction();

    transaction.setAccount(accountRepository.findById((long) 1).get()); // account_id
    transaction.setSenderName("Test Name");
    transaction.setNote(transactionDTO.getNote());
    transaction.setReceiver(accountRepository.findById((long) 2).get()); // Receiver Account id
    transaction.setAmount(transactionDTO.getAmount());
    transaction.setUser(
        userRepository
            .findByUsernameOrEmail(transactionDTO.getEmail(), transactionDTO.getEmail())
            .get());
    transaction.setExternal(false);
    transactionsRepository.save(transaction);
    try {
      sendEmail (transaction);
    } catch (IOException ex) {
      System.out.println (ex);
    }

    return transaction;
  }

  private void sendEmail(Transaction transaction) throws IOException {
    Email from = new Email("noreply@conchrisbank.eu");
    String subject = "Transaction completed";
    Email to = new Email(transaction.getUser().getEmail());

    Content content =
        new Content(
            "text/plain",
            "The transaction: "
                + transaction.getAmount()
                + " EUR to account "
                + transaction.getReceiver().getIban()
                + " is complete.");

    Mail mail = new Mail(from, subject, to, content);
    SendGrid sg = new SendGrid(System.getenv ("SENDGRID_API_KEY"));

    Request request = new Request();
    try {
      request.method = Method.POST;
      request.endpoint = "mail/send";
      request.body = mail.build();
      Response response = sg.api(request);
      System.out.println(response.statusCode);
      System.out.println(response.body);
      System.out.println(response.headers);
    } catch (IOException ex) {
      throw ex;
    }
  }
}
