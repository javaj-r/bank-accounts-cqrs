package com.javid.bank.accounts.query.repository;

import com.javid.bank.accounts.query.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author javid
 * Created on 6/30/2022
 */
public interface AccountRepository extends JpaRepository<Account, String> {
}
