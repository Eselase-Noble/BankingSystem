package org.nobleson.banksystem.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nobleson.banksystem.enums.AccountStatus;
import org.nobleson.banksystem.enums.AccountType;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountID;
    private String accountName;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private BigDecimal accountBalance;
}
