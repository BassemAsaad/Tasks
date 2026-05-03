package com.pioneers.prototype;

public class MainProtoType {

    public static void main(String[] args) {
        Car car = new Car("BMW", 200);
        Car clonedCar = car.clone();

        car.display();
        clonedCar.display();
    }
}
