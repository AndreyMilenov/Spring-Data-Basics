package lab.inheritance;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "vehicles")
public abstract class Vehicle extends IdType{

    @Basic
    private String model;
    @Basic
    private BigDecimal price;
    @Column(name = "fuel_type")
    private String fuelType;

    protected Vehicle() {
    }

    protected Vehicle(String type) {
        super.type = type;
    }

    public Vehicle(String type, String model, BigDecimal price, String fuelType) {

        this.type = type;
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
    }
}
