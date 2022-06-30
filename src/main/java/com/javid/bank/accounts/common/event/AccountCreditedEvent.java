package com.javid.bank.accounts.common.event;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Getter
public class AccountCreditedEvent extends BaseEvent<String> {

    private final BigDecimal amount;

    public AccountCreditedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
