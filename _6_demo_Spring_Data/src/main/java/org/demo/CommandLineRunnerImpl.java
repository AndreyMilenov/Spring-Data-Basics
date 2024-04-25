package org.demo;

import org.demo.data.entities.Account;
import org.demo.data.entities.User;
import org.demo.data.service.AccountService;
import org.demo.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private final UserService userService;
    @Autowired
    private final AccountService accountService;

    public CommandLineRunnerImpl(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = this.userService.findUserById(1);
        Account account = new Account(BigDecimal.valueOf(5000),user);
        this.accountService.createAccount(account);

        System.out.println();

    }
}
