package entiities.ex4;

import entiities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {
    @Column
    private String name;
    @ManyToMany(mappedBy = "medicaments")
    private Set<Diagnose> diagnoses;

}
