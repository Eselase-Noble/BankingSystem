package org.nobleson.banksystem.services;

import lombok.RequiredArgsConstructor;
import org.nobleson.banksystem.models.Account;
import org.nobleson.banksystem.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class BankingService {
    private final AccountRepository accountRepository;

    @Transactional
    public void transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(()->new RuntimeException("Account not found"));
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(()->new RuntimeException("Account not found"));


        if (fromAccount.getAccountBalance().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setAccountBalance(fromAccount.getAccountBalance().subtract(amount));
        toAccount.setAccountBalance(toAccount.getAccountBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    @Transactional
    public void  withdrawMoney(Long accountId,  BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(()->new RuntimeException("Account not found"));

        if (account.getAccountBalance().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient balance");
        }

        account.setAccountBalance(account.getAccountBalance().subtract(amount));
        accountRepository.save(account);
    }

    @Transactional
    public void depositMoney(Long accountId,  BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(()->new RuntimeException("Account not found"));
        account.setAccountBalance(account.getAccountBalance().add(amount));
        accountRepository.save(account);
    }


    public BigDecimal checkBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(()->new RuntimeException("Account not found"));
        return account.getAccountBalance();
    }
}
