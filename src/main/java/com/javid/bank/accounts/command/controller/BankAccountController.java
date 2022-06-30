package com.javid.bank.accounts.command.controller;

import com.javid.bank.accounts.command.dto.CreateAccountRequest;
import com.javid.bank.accounts.command.dto.DepositRequest;
import com.javid.bank.accounts.command.dto.WithdrawRequest;
import com.javid.bank.accounts.command.service.AccountCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author javid
 * Created on 6/30/2022
 */
@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

    private final AccountCommandService accountCommandService;

    public BankAccountController(AccountCommandService accountCommandService) {
        this.accountCommandService = accountCommandService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest request) {
        try {
            var response = this.accountCommandService.creatAccount(request);

            return new ResponseEntity<>(response.get(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest request) {
        this.accountCommandService.depositToAccount(request);

        return new ResponseEntity<>("Account credited", HttpStatus.OK);
    }

    @PutMapping(value = "/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawRequest request) {
        this.accountCommandService.withdrawFromAccount(request);

        return new ResponseEntity<>("Account debited", HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
