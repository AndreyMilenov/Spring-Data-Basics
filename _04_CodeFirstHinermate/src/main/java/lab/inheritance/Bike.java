package lab.inheritance;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle{
    private static final String BIKE_TYPE = "BIKE";
    public Bike() {

    }
    public Bike(String model, BigDecimal price, String fuelType) {
        super(BIKE_TYPE, model, price, fuelType);
    }


}
