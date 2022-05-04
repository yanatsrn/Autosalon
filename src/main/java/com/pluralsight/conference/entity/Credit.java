package com.pluralsight.conference.entity;

public class Credit {
    private int price;
    private int procent;
    private int year;
    private int newPrice;

    public Credit() {
    }

    public Credit(int price, int procent, int year) {
        this.price = price;
        this.procent = procent;
        this.year = year;
        this.newPrice = 0;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProcent() {
        return procent;
    }

    public void setProcent(int procent) {
        this.procent = procent;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }


}
