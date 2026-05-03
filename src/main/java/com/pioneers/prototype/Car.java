package com.pioneers.prototype;

/**
 * Prototype Design Pattern
 * Cloneable has no methods its markup interface to tell jvm this object supports cloning
 */

public class Car implements Cloneable {

    private String model;
    private int speed;

    public Car(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    @Override
    public Car clone() {
        try {
            return (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void display() {
        System.out.println("Model: " + model + ", Speed: " + speed);
    }
}
