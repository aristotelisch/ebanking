package eu.happybit.konchris.koncrisbackend.payload;

import eu.happybit.konchris.koncrisbackend.entity.Transaction;
import lombok.Data;

@Data
public class TransactionResponse {
  private Long id;
  private String senderName;
  private Long accountId;
  private String accountIban;
  private Long receiverAccountId;
  private String receiverAccountIban;
  private Double amount;
  private Long userId;
  private String userFullName;

  public TransactionResponse(Transaction transaction) {
    id = transaction.getUser().getId();
    senderName = transaction.getSenderName();
    accountId = transaction.getAccount().getId();
    accountIban = transaction.getAccount().getIban ();
    receiverAccountId = transaction.getReceiver().getId();
    receiverAccountIban = transaction.getReceiver ().getIban ();
    amount = transaction.getAmount();
    userId = transaction.getUser().getId ();
    userFullName = transaction.getUser ().getFullName();
  }
}
