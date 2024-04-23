package entiities.ex3;

import entiities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "informations")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class  Information extends BaseEntity {
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String LastName;
    @Column(name = "phone")
    private String phone;
}
