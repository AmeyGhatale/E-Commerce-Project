package com.example.ecommerceproject;
import java.sql.*;

public class DbConnection {
        public final java.lang.String dbUrl = "jdbc:mysql://localhost:3306/ecommerce";
        public final java.lang.String username = "root";
        public final java.lang.String password = "admin";

        public Statement getStatement(){
                try{
                        Connection connection = DriverManager.getConnection(dbUrl, username, password);
                        return connection.createStatement();
                }
                catch( Exception e){
                        e.printStackTrace();
                }
                return null;
        }

        public ResultSet getQueryTable(String query){
                try{
                        Statement statement =  getStatement();
                        return statement.executeQuery(query);
                }
                catch (Exception e){
                        e.printStackTrace();
                }
                return null;
        }

        public int  updateDatabase(String query){
                try{
                        Statement statement =  getStatement();
                        return statement.executeUpdate(query);
                }
                catch (Exception e){
                        e.printStackTrace();
                }
                return 0;
        }

        public static void main(String[] args) {
                DbConnection conn = new DbConnection();
                ResultSet rs = conn.getQueryTable("SELECT * FROM CUSTOMER");
                if(rs!=null)
                        System.out.println("Connection Established");
                else
                        System.out.println("Connection Failed");
        }
}
