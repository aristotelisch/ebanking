package eu.happybit.konchris.koncrisbackend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "accounts")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
  @SequenceGenerator (name="account_generator", sequenceName = "account_seq", allocationSize = 50)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  private String description;

  private String note;

  @CreationTimestamp
  private Instant created_at;

  @UpdateTimestamp
  private Instant updated_at;

  @ManyToMany(mappedBy = "accounts")
  private Set<User> users = new HashSet<> ();
}
