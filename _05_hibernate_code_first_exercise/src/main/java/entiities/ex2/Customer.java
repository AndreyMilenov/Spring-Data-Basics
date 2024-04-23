package entiities.ex2;

import entiities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(name = "credit_cart_number")
    private String creditCardNumber;
    @OneToMany(mappedBy = "customers")
    private Set<Sale> sales;

}
