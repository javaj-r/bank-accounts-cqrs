package com.javid.bank.accounts.command.aggregate;

import com.javid.bank.accounts.command.command.CreateAccountCommand;
import com.javid.bank.accounts.command.command.DepositMoneyCommand;
import com.javid.bank.accounts.command.command.WithdrawMoneyCommand;
import com.javid.bank.accounts.common.event.AccountActivatedEvent;
import com.javid.bank.accounts.common.event.AccountCreatedEvent;
import com.javid.bank.accounts.common.event.AccountCreditedEvent;
import com.javid.bank.accounts.common.event.AccountDebitedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Slf4j
@NoArgsConstructor
@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String accountId;

    private BigDecimal balance;
    private String status;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        log.info("CreateAccountCommand received.");

        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getBalance()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        log.info("An AccountCreatedEvent occurred.");
        this.accountId = accountCreatedEvent.getId();
        this.balance = accountCreatedEvent.getBalance();
        this.status = "CREATED";

        AggregateLifecycle.apply(new AccountActivatedEvent(
                accountId,
                "ACTIVATED"
        ));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("An AccountActivatedEvent occurred.");
        this.status = accountActivatedEvent.getStatus();
    }

    @CommandHandler
    public void on(DepositMoneyCommand depositMoneyCommand) {
        log.info("DepositMoneyCommand received.");

        AggregateLifecycle.apply(new AccountDebitedEvent(
                depositMoneyCommand.getId(),
                depositMoneyCommand.getAmount()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent accountCreditedEvent) {
        log.info("An AccountCreditedEvent occurred.");
        this.balance = this.balance.add(accountCreditedEvent.getAmount());
    }

    @CommandHandler
    public void on(WithdrawMoneyCommand withdrawMoneyCommand) {
        log.info("WithdrawMoneyCommand received.");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                withdrawMoneyCommand.getId(),
                withdrawMoneyCommand.getAmount()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent accountDebitedEvent) {
        log.info("An AccountDebitedEvent occurred.");
        this.balance = this.balance.subtract(accountDebitedEvent.getAmount());
    }
}
