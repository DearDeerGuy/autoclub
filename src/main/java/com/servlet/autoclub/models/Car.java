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

    public static List<Car> GetCars() {
        Database.Connect();
        List<Car> cars = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement carsListStatement = Database.conn.createStatement();
            String selectCars = "SELECT * FROM cars";
            carsListStatement.executeQuery(selectCars);
            ResultSet carsSet = carsListStatement.getResultSet();
            while (carsSet.next()) {
                Car car = new Car();
                car.id = carsSet.getInt("id");
                car.user_id = carsSet.getInt("user_id");
                car.name = carsSet.getString("name");
                car.model = carsSet.getString("model");
                car.year = carsSet.getInt("year");
                car.fuel_type = carsSet.getString("fuel_type");
                cars.add(car);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public String toString() {
        return this.name + " " + this.model + " " + this.year + " " + this.fuel_type;
    }
}
