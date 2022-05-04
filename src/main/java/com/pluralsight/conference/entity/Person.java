package com.pluralsight.conference.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "person_id")
    private int personId;

    @Column
    private String surname;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String phone;

    @Column
    private String mail;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "personForPurchase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Purchase> personForPurchase;

    public Person() {
    }

    public Person(String surname, String name, int age, String phone, String mail) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.mail = mail;
        user = new User();
        personForPurchase = new ArrayList<>();
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser(User newUser) {
        newUser.setPerson(this);
        user = newUser;
    }

    public List<Purchase> getPersonForPurchase() {
        return personForPurchase;
    }

    public void setPersonForPurchase(List<Purchase> personForPurchase) {
        this.personForPurchase = personForPurchase;
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
