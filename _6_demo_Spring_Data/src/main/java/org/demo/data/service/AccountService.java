package org.demo.data.service;

import org.demo.data.entities.Account;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal money, Integer id);
    void transferMoney(BigDecimal money,Integer id);

    void createAccount(Account account);
}
