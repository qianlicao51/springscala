package com.hsp.single;

/**
 * 单一原则
 */
public class SingleRespon01 {
    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.run("飞机");
        vehicle.run("汽车");
        vehicle.run("火车");

    }
}

/**
 * 违反了单一原则
 */
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " running ……");
    }
}