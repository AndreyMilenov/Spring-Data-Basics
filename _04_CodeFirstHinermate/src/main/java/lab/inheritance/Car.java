package lab.inheritance;

import lab.composition.PlateNumber;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {
    private static final String CAR_TYPE = "CAR";
    @Basic
    private int seats;
    @OneToOne(optional = true)
    @JoinColumn(name = "plate_number_id",referencedColumnName = "id")
    private PlateNumber plateNumber;

    public Car() {
    }

    public Car(String model, BigDecimal price, String fuelType, int seats,PlateNumber plateNumber) {
        super(CAR_TYPE, model, price, fuelType);
        this.seats = seats;
        this.plateNumber = plateNumber;
    }

    public void setPlateNumber(PlateNumber plateNumber) {
        this.plateNumber = plateNumber;
    }
}
