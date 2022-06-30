package com.javid.bank.accounts.query.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Getter
@Setter
@Entity
public class Account {

    @Id
    private String accountId;
    private BigDecimal balance;
    private String status;
}
