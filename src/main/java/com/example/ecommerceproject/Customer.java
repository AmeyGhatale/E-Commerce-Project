package com.example.ecommerceproject;

public class Customer {
    private int id;
    private String email, mobile, name;

    Customer(int id, String name, String email, String mobile)
    {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }
}
