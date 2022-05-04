package com.pluralsight.conference.entity;

import jdk.jfr.BooleanFlag;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String role;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "discount_sum")
    private int discountSum;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public User() {
    }

    public User(String login, String password, String role, boolean isActive, int discountSum) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getDiscountSum() {
        return discountSum;
    }

    public void setDiscountSum(int discountSum) {
        this.discountSum = discountSum;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", isActive=" + isActive +
                ", discountSum=" + discountSum +
                ", person=" + person +
                '}';
    }
}
