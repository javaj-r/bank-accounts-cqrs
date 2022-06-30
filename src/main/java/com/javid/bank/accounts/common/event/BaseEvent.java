package com.javid.bank.accounts.common.event;

import lombok.Getter;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Getter
public class BaseEvent<T> {

    private final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
