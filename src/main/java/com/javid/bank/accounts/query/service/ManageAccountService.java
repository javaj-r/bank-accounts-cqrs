package com.javid.bank.accounts.query.service;

import com.javid.bank.accounts.common.event.AccountActivatedEvent;
import com.javid.bank.accounts.common.event.AccountCreatedEvent;
import com.javid.bank.accounts.common.event.AccountCreditedEvent;
import com.javid.bank.accounts.common.event.AccountDebitedEvent;
import com.javid.bank.accounts.query.entity.Account;
import com.javid.bank.accounts.query.query.FindAccountByIdQuery;
import com.javid.bank.accounts.query.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Slf4j
@Service
@Transactional
public class ManageAccountService {

    private final AccountRepository accountRepository;

    public ManageAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        log.info("Handling AccountCreatedEvent...");
        Account account = new Account();
        account.setAccountId(accountCreatedEvent.getId());
        account.setBalance(accountCreatedEvent.getBalance());
        account.setStatus("CREATED");

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("Handling AccountActivatedEvent...");
        accountRepository
                .findById(accountActivatedEvent.getId())
                .ifPresent(account -> {
                    account.setStatus(accountActivatedEvent.getStatus());
                    accountRepository.save(account);
                });
    }

    @EventHandler
    public void on(AccountCreditedEvent accountCreditedEvent) {
        log.info("Handling AccountCreditedEvent...");
        accountRepository
                .findById(accountCreditedEvent.getId())
                .ifPresent(account -> {
                    account.setBalance(account.getBalance()
                            .add(accountCreditedEvent.getAmount()));
                    accountRepository.save(account);
                });
    }

    @EventHandler
    public void on(AccountDebitedEvent accountDebitedEvent) {
        log.info("Handling AccountDebitedEvent...");
        accountRepository
                .findById(accountDebitedEvent.getId())
                .ifPresent(account -> {
                    account.setBalance(account.getBalance()
                            .subtract(accountDebitedEvent.getAmount()));
                    accountRepository.save(account);
                });
    }

    @QueryHandler
    public Account handle(FindAccountByIdQuery findAccountByIdQuery) {
        log.info("Handling FindAccountByIdQuery...");
        return accountRepository.findById(findAccountByIdQuery.getAccountId())
                .orElse(null);
    }


}
