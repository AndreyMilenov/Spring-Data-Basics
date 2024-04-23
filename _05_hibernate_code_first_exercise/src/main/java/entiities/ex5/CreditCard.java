package entiities.ex5;

import javax.persistence.*;

@Entity
@Table(name = "credit _cards")
public class CreditCard extends BillingDetails {
    @Enumerated(value = EnumType.STRING)
    private CardType cardType;
    @Column(name = "expiration_month")
    private int expirationMonth;
    @Column(name = "expiration_year")
    private int expirationYear;
}
