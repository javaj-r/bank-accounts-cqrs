package com.javid.bank.accounts.common.event;

import lombok.Getter;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Getter
public class AccountActivatedEvent extends BaseEvent<String>{

    private final String status;

    public AccountActivatedEvent(String id, String status) {
        super(id);
        this.status = status;
    }
}
