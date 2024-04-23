package entiities.ex5;

import javax.persistence.Column;

public class BankAccount extends BillingDetails{
    @Column
    private String name;
    @Column(name = "swift_code")
    private String swiftCode;
}
