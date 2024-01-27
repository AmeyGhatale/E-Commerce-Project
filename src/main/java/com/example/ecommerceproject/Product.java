package com.example.ecommerceproject;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleDoubleProperty price;
    private SimpleStringProperty name;


    public Product(int id, String name, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.price = new SimpleDoubleProperty(price);
        this.name = new SimpleStringProperty(name);
    }

    public static ObservableList<Product> getAllProducts()
    {
        String selectAllProducts = "select id, name, price FROM product";
        return fecthProduct(selectAllProducts);
    }

    public static ObservableList<Product>  fecthProduct(String query)
    {
        ObservableList<Product> data = FXCollections.observableArrayList();
        DbConnection dbConnection = new DbConnection();

        try{
            ResultSet rs = dbConnection.getQueryTable(query);
            while (rs.next()){
                Product product = new Product(rs.getInt("id"),  rs.getString("name"),  rs.getDouble("price"));
                data.add(product);
            }
            return data;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id.get();
    }
    public double getPrice() {
        return price.get();
    }
    public String getName() {
        return name.get();
    }

}
