package com.pluralsight.conference.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int carId;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "distance")
    private int distance;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "fuel_consumption")
    private String fuelConsumption;

    @Column
    private String transmission;

    @Column(name = "price")
    private int price;

    @Column(name = "is_bought")
    private boolean isBought;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "type_id")
    private Type typeCar;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Purchase> purchases;

    public Car() {
    }

    public Car(String name, int year, int distance, String fuel, String fuelConsumption, String transmission, int price, boolean isBought) {
        this.name = name;
        this.year = year;
        this.distance = distance;
        this.fuel = fuel;
        this.fuelConsumption = fuelConsumption;
        this.transmission = transmission;
        this.price = price;
        this.isBought = isBought;
        purchases = new ArrayList<>();
    }

    public Car(String name, int year, int distance, String fuel, String fuelConsumption, String transmission, int price, boolean isBought, Company company, Type typeCar) {
        this.name = name;
        this.year = year;
        this.distance = distance;
        this.fuel = fuel;
        this.fuelConsumption = fuelConsumption;
        this.transmission = transmission;
        this.price = price;
        this.isBought = isBought;
        this.company = company;
        this.typeCar = typeCar;
        purchases = new ArrayList<>();
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(String fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Type getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(Type typeCar) {
        this.typeCar = typeCar;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", distance=" + distance +
                ", fuel='" + fuel + '\'' +
                ", fuelConsumption='" + fuelConsumption + '\'' +
                ", transmission='" + transmission + '\'' +
                ", price=" + price +
                ", isBought=" + isBought +
                ", company=" + company +
                ", type=" + typeCar +
                '}';
    }

    public static class Builder {
        //обязательные
        public String name;
        public int year;
        public String fuelConsumption;
        public int price;
        public Company company;
        public Type typeCar;
        //необязательные
        public String fuel = "Бензин";
        public int distance = 0;
        public String transmission = "Механика";
        public boolean isBought = false;

        //конструктор с обязательными параметрами
        public Builder(String name, int year, String fuelConsumption, int price, Company company, Type typeCar) {
            this.name = name;
            this.year = year;
            this.fuelConsumption = fuelConsumption;
            this.price = price;
            this.company = company;
            this.typeCar = typeCar;
        }

        //методы для необязательных параметров
        public Builder distance(int parameter) {
            distance = parameter;
            return this;
        }

        public Builder fuel(String parameter) {
            fuel = parameter;
            return this;
        }

        public Builder transmission(String parameter) {
            transmission = parameter;
            return this;
        }

        public Builder isBought(Boolean parameter) {
            isBought = parameter;
            return this;
        }

        public Car build() {
            return new  Car(this);

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Builder builder = (Builder) o;
            return year == builder.year && price == builder.price && distance == builder.distance && isBought == builder.isBought && Objects.equals(name, builder.name) && Objects.equals(fuelConsumption, builder.fuelConsumption) && Objects.equals(company, builder.company) && Objects.equals(typeCar, builder.typeCar) && Objects.equals(fuel, builder.fuel) && Objects.equals(transmission, builder.transmission);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, year, fuelConsumption, price, company, typeCar, fuel, distance, transmission, isBought);
        }
    }

    private Car(Builder builder) {
        name = builder.name;
        year = builder.year;
        distance = builder.distance;
        fuel = builder.fuel;
        fuelConsumption = builder.fuelConsumption;
        transmission = builder.transmission;
        price = builder.price;
        isBought = builder.isBought;
        company = builder.company;
        typeCar = builder.typeCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId && year == car.year && distance == car.distance && price == car.price && isBought == car.isBought && Objects.equals(name, car.name) && Objects.equals(fuel, car.fuel) && Objects.equals(fuelConsumption, car.fuelConsumption) && Objects.equals(transmission, car.transmission) && Objects.equals(company, car.company) && Objects.equals(typeCar, car.typeCar) && Objects.equals(purchases, car.purchases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, name, year, distance, fuel, fuelConsumption, transmission, price, isBought, company, typeCar, purchases);
    }
}
