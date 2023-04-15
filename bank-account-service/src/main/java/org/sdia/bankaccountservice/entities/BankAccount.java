package org.sdia.bankaccountservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sdia.bankaccountservice.enums.AccountType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date creatAt;
    private Double balance;
    private String currency;
    private AccountType type;
    @ManyToOne
    private Customer customer;
}
