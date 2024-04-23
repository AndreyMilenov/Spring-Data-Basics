package entiities.ex3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Information{
    @Column(nullable = false)
    private String email;
    @Column(name = "salary_per_hour",nullable = false)
    private double salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;
}
