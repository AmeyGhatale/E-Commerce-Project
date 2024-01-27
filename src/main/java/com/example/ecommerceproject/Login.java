package com.example.ecommerceproject;

import java.sql.ResultSet;

public class Login {

    public Customer customerLogin(String username, String  password)
    {
        String query = "SELECT * FROM CUSTOMER WHERE EMAIL = '"+username+"' and PASSWORD = '"+password+"'";
        DbConnection connection = new DbConnection();
        try{
            ResultSet rs = connection.getQueryTable(query);
            if(rs.next())
                return new Customer(rs.getInt("id"), rs.getString("NAME"), rs.getString("EMAIL"),
                        rs.getString("MOBILE"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return   null;
    }

    public static void main(String[] args) {
        Login login = new Login();
        Customer customer = login.customerLogin("amey@gmail.com", "Pass@123");
        System.out.println("Hello, "+customer.getName());
    }
}
