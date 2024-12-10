package com.servlet.autoclub.models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Car {
    public int id;
    public int user_id;
    public String name;
    public String model;
    public int year;
    public String fuel_type;

    public Car(int id, int user_id, String name, String model, int year, String fuel_type) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.model = model;
        this.year = year;
        this.fuel_type = fuel_type;
    }
    public Car(int user_id, String name, String model, int year, String fuel_type) {
        this.user_id = user_id;
        this.name = name;
        this.model = model;
        this.year = year;
        this.fuel_type = fuel_type;
    }
    public static List<Car> GetCars() {
        List<Car> cars = new ArrayList<>();

        try {
            String selectCars = "SELECT * FROM cars";
            ResultSet carsSet = Database.GetResultSetFromSQL(selectCars);
            while (carsSet.next()) {
                Car car = new Car(
                        carsSet.getInt("id"),
                        carsSet.getInt("user_id"),
                        carsSet.getString("name"),
                        carsSet.getString("model"),
                        carsSet.getInt("year"),
                        carsSet.getString("fuel_type")
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }
    public List<Comment> GetComments() {
        return Comment.GetComments(id);
    }
    public static boolean AddCar(Car car) {
        String addCar = "INSERT INTO cars(user_id, name, model, year, fuel_type) VALUES (" + car.user_id + ", '" + car.name + "', '" + car.model + "', " + car.year + ", '" + car.fuel_type + "')";
        return Database.InsertFromSQL(addCar);
    }
    @Override
    public String toString() {
        return this.name + " " + this.model + " " + this.year + " " + this.fuel_type;
    }
}
