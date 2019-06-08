package eu.happybit.konchris.koncrisbackend.payload;

import eu.happybit.konchris.koncrisbackend.entity.Transaction;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TransactionListResponse {
  private List<TransactionResponse> transactions;

  public TransactionListResponse(List<Transaction> transactions) {
    this.transactions = transactions.stream().map(TransactionResponse::new).collect (Collectors.toList ());
  }
}
