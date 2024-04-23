package entiities.ex4;

import entiities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {
    @Column(name = "first_name",nullable = false,updatable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column
    private String address;
    @Column
    private String email;
    @Column(name = "date_of_birt")
    private LocalDate dateIfBirt;
    @Column
    private String picture;
    @Column(name = "has_medical_insurance")
    private boolean hasMedicalInsurance;
    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;
}
