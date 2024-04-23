package entiities.ex2;

import entiities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    @Column
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customers;
    @ManyToOne
    @JoinColumn(name = "storL_location_id")
    private StoreLocation storeLocation;

}
