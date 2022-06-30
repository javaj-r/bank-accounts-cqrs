package com.javid.bank.accounts.command.command;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Getter
public class DepositMoneyCommand extends BaseCommand<String> {

    private final BigDecimal amount;

    public DepositMoneyCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
