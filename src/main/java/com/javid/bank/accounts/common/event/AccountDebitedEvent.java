package com.javid.bank.accounts.common.event;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Getter
public class AccountDebitedEvent extends BaseEvent<String> {

    private final BigDecimal amount;

    public AccountDebitedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
