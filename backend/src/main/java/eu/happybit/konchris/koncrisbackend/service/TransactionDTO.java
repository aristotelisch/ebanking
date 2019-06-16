package eu.happybit.konchris.koncrisbackend.service;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransactionDTO {
  private String senderName;

  @NotNull
  private String fromIban;

  @NotNull
  private String toIban;

  @NotNull
  private Double amount;

  private String email;

  private String note;

  public TransactionDTO () {
    // Empty constructor needed for Jackson.
  }
}
