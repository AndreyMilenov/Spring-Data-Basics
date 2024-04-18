package lab.composition;

import lab.inheritance.Car;

import javax.persistence.*;


@Entity
@Table(name = "plate_numbers")
public class PlateNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(targetEntity = Car.class, mappedBy = "plateNumber")
    private Car car;
    private String number;

    public PlateNumber(){}


    public PlateNumber(String number) {
        this.number = number;
    }
}
