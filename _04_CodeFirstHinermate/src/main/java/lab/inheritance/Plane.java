package lab.inheritance;

import lab.composition.Company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle {
    private static final String PLANE_TYPE = "PLANE";
    @Column(name = "passenger_capacity")
    private int passengerCapacity;
    public Plane(){}
    @ManyToOne
    private Company owner;

    public Plane(String model, BigDecimal price, String fuelType, int passengerCapacity,Company owner) {
        super(PLANE_TYPE, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
        this.owner = owner;
    }
}
