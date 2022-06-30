package com.javid.bank.accounts.command.service;

import com.javid.bank.accounts.command.command.CreateAccountCommand;
import com.javid.bank.accounts.command.command.DepositMoneyCommand;
import com.javid.bank.accounts.command.command.WithdrawMoneyCommand;
import com.javid.bank.accounts.command.dto.CreateAccountRequest;
import com.javid.bank.accounts.command.dto.DepositRequest;
import com.javid.bank.accounts.command.dto.WithdrawRequest;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Slf4j
@Service
public class AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> creatAccount(CreateAccountRequest createAccountRequest) {
        log.info("An CreateAccountRequest received.");

        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                createAccountRequest.getStartingBalance()
        ));
    }

    public CompletableFuture<String> depositToAccount(DepositRequest depositRequest) {
        log.info("An DepositRequest received.");

        return commandGateway.send(new DepositMoneyCommand(
                depositRequest.getAccountId(),
                depositRequest.getAmount()
        ));
    }

    public CompletableFuture<String> withdrawFromAccount(WithdrawRequest withdrawRequest) {
        log.info("An WithdrawRequest received.");

        return commandGateway.send(new WithdrawMoneyCommand(
                withdrawRequest.getAccountId(),
                withdrawRequest.getAmount()
        ));
    }
}
