package entities;

import ORM.anutations.Column;
import ORM.anutations.Entity;
import ORM.anutations.Id;

import java.time.LocalDate;
@Entity(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "age")
    private int age;
    @Column(name = "registration")
    private LocalDate registration;
    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String userName, int age, LocalDate registration,String email) {
        this.userName = userName;
        this.age = age;
        this.registration = registration;
        setEmail(email);
    }
    public User(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }
}
