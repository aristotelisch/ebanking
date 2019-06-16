package eu.happybit.konchris.koncrisbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Table(name="transactions")
@Entity(name = "Transaction")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(name = "sender_name", updatable = false, nullable = true)
  private String senderName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "receiver_account_id")
  @JsonBackReference
  private Account receiver;

  @NotNull
  @Column(name = "amount", updatable = false, nullable = false)
  private Double amount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;

  @ManyToOne
  @JoinColumn(name = "account_id")
  @JsonBackReference
  private Account account;

  private String description;

  private String note;

  @NotNull
  private boolean isExternal;

  @CreationTimestamp
  private Instant created_at;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Transaction)) return false;
    return id != null && id.equals(((Transaction) o).getId());
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
