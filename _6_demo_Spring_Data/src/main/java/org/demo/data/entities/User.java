package org.demo.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
 @Table(name = "users")
public class User extends BaseEntity{
    @Column(unique = true)
    private String userName;
    @Column(nullable = false)
    private int age;
    @OneToMany(mappedBy = "user")
    private Set<Account> accounts;

    public User() {
        this.accounts = new HashSet<>();
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
        this.accounts = new HashSet<>();
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

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
