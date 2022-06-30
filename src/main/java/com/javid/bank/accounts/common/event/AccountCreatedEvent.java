package com.javid.bank.accounts.common.event;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Getter
public class AccountCreatedEvent extends BaseEvent<String>{

    private final BigDecimal balance;

    public AccountCreatedEvent(String id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }
}
