package com.pluralsight.conference.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;

    @Column
    private String body;

    @Column
    private String salon;

    @Column
    private String color;

    @Column(name = "parking_helper")
    private boolean isParkingHelper;

    @OneToMany(mappedBy = "typeCar", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Car> models;

    public Type() {
    }

    public Type(String body, String salon, String color, boolean isParkingHelper) {
        this.body = body;
        this.salon = salon;
        this.color = color;
        this.isParkingHelper = isParkingHelper;
        models = new ArrayList<Car>();
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isParkingHelper() {
        return isParkingHelper;
    }

    public void setParkingHelper(boolean parkingHelper) {
        isParkingHelper = parkingHelper;
    }

    public List<Car> getModels() {
        return models;
    }

    public void setModels(List<Car> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", body='" + body + '\'' +
                ", salon='" + salon + '\'' +
                ", color='" + color + '\'' +
                ", isParkingHelper=" + isParkingHelper +
                '}';
    }
}
