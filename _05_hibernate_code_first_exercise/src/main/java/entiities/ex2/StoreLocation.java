package entiities.ex2;

import entiities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation extends BaseEntity {
    @Column(name = "location_name", nullable = false)
    private String locationName;
    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sale;
}
