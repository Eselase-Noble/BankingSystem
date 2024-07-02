package org.nobleson.banksystem.services;

import lombok.RequiredArgsConstructor;
import org.nobleson.banksystem.models.Account;
import org.nobleson.banksystem.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


/**
 * This class handles the all the service rendered on the account
 */

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * This method is used to create a new account
     * SQL EQUIVALENT: INSERT INTO ACCOUNT VALUES (?,?,?,?,...)
     * @param account
     * @return
     */
    @Transactional
    public Account createAccount(Account account) {
        if (account.getAccountBalance() == null || account.getAccountBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Account balance must be non-negative");
        }
        return accountRepository.save(account);
    }

    @Transactional
    public List<Account> createAccounts(List<Account> accounts) {

        if (accounts == null || accounts.isEmpty()) {
            throw new IllegalArgumentException("List of accounts must not be null or empty");
        }
        return accountRepository.saveAll(accounts);
    }


    /**
     * This method is used to query an account based on the account ID
     * SELECT * FROM ACCOUNT WHERE ACCOUNTID = {accountID}
     * @param accountID
     * @return
     */
    public Account getAccount(Long accountID) {
        if (!accountRepository.existsById(accountID)){
         throw  new RuntimeException("Account does not exist");
        }

        return accountRepository.findById(accountID).get();
    }

    /**
     * THIS QUERIES ALL THE ACCOUNT IN THE DATABASE
     * SQL EQUIVALENT: SELECT * FROM ACCOUNT
     * @return
     */
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * THIS METHOD IS USED TO UPDATE A PARTICULAR ACCOUNT
     * SQL EQUIVALENT: UPDATE ACCOUNT SET COLUMNS = VALUES WHERE ACCOUNTID = {ACCOUNTID}
     * @param account
     * @return
     */
    public Account updateAccount( Account account) {
        if (!accountRepository.existsById(account.getAccountID())){
            throw  new RuntimeException("Account does not exist");
        }
        return accountRepository.save(account);
    }

    /**
     * THIS METHOD IS USED TO DELETE A PARTICULAR ACCOUNT
     * DELETE FROM ACCOUNT WHERE ACCOUNTID = {ACCOUNTID};
     * @param accountID
     */

    public void deleteAccount(Long accountID) {
        if (!accountRepository.existsById(accountID)){
            throw  new RuntimeException("Account does not exist");
        }
        accountRepository.deleteById(accountID);
    }

    /**
     * THIS METHOD IS USED TO DELETE ALL THE ACCOUNTS
     */
    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }
}
