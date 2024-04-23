package entiities.ex5;

import entiities.BaseEntity;

import javax.persistence.*;

@Entity
@Inheritance
public abstract class BillingDetails extends BaseEntity {

    private int number;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
