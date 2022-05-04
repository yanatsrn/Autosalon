package com.pluralsight.conference.entity;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private int purchaseId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private Person personForPurchase;

    public Purchase() {
    }

    public Purchase(Car car, Person personForPurchase) {
        this.car = car;
        this.personForPurchase = personForPurchase;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Person getPersonForPurchase() {
        return personForPurchase;
    }

    public void setPersonForPurchase(Person personForPurchase) {
        this.personForPurchase = personForPurchase;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", car=" + car +
                ", personForPurchase=" + personForPurchase +
                '}';
    }
}
