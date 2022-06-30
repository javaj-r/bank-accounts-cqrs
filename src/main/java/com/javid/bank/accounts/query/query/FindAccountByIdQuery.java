package com.javid.bank.accounts.query.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author javid
 * Created on 6/30/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAccountByIdQuery {

    private String accountId;
}
