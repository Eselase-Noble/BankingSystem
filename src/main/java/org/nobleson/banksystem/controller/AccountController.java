package org.nobleson.banksystem.controller;

import lombok.RequiredArgsConstructor;
import org.nobleson.banksystem.models.Account;
import org.nobleson.banksystem.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;


    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/find/{accountID}")
        public ResponseEntity<Account> getAccount(@PathVariable("accountID") Long accountID) {

        return new ResponseEntity<>(accountService.getAccount(accountID), HttpStatus.OK);
        }

    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {

        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
        public ResponseEntity<Account> editAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.updateAccount(account), HttpStatus.OK);
        }

        @DeleteMapping("/delete/{accountID}")
     public ResponseEntity<String> deleteAccount(@PathVariable("accountID") Long accountID) {
        accountService.deleteAccount(accountID);
        return new ResponseEntity<>("Account deleted", HttpStatus.OK);
     }

     @DeleteMapping("/delete/all")
     public ResponseEntity<String> deleteAllAccounts() {
        accountService.deleteAllAccounts();
        return new ResponseEntity<>("All accounts deleted", HttpStatus.OK);
     }
}
