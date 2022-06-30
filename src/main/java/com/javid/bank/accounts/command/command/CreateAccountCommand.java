package com.javid.bank.accounts.command.command;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Getter
public class CreateAccountCommand extends BaseCommand<String> {

    private final BigDecimal balance;

    public CreateAccountCommand(String id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }
}
