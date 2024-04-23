package entiities.ex2;

import entiities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name ="priducts")
public class Product extends BaseEntity {
    @Column(nullable = false,unique = true)
    private String name;
    @Column
    private int quantity;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToMany(mappedBy = "products")
    private Set<Sale> sales;
}
