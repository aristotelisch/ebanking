package eu.happybit.konchris.koncrisbackend.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @Column(unique = true)
  private String username;

  private String password;
}
