package com.javid.bank.accounts.command.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Data
public class CreateAccountRequest {

    private BigDecimal startingBalance;
}
