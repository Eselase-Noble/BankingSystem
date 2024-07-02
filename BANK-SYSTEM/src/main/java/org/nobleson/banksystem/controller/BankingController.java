package org.nobleson.banksystem.controller;


import lombok.RequiredArgsConstructor;
import org.nobleson.banksystem.services.BankingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankingController {

    private final BankingService bankingService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestParam Long from, @RequestParam Long to, @RequestParam BigDecimal amount) {

        bankingService.transferMoney(from, to, amount);
        return new ResponseEntity<>("Money transferred successfully", HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestParam Long from, @RequestParam BigDecimal amount) {
        bankingService.withdrawMoney(from, amount);

        return new ResponseEntity<>( "Money withdrawn successfully",HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositMoney(@RequestParam Long accountID, @RequestParam BigDecimal amount) {
        bankingService.depositMoney(accountID, amount);
        return new ResponseEntity<>("Money deposited successfully", HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam Long accountID) {
        return new ResponseEntity<>(bankingService.checkBalance(accountID), HttpStatus.OK);
    }
}
