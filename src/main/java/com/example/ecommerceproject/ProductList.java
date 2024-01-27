package com.example.ecommerceproject;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList{
    private static TableView<Product> productTable;

    public static VBox createTable( ObservableList<Product> data){
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



        productTable = new TableView<>();
        productTable.getColumns().addAll(id, price, name);
        productTable.setItems(data);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox();
        vBox.getChildren().add(productTable);
        return vBox;
    }


    public  VBox getDummyTable(){
          TableView<Product> productTable;

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


        ObservableList<Product> data1 = FXCollections.observableArrayList();
        data1.add(new Product(2, "Samsung S23", 51000));
        data1.add(new Product(42, "Samsung S21", 31000));
        data1.add(new Product(5, "Samsung S23 Ultra", 151000));

        productTable = new TableView<>();
        productTable.getColumns().addAll(id, name, price);
        productTable.setItems(data1);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox();
        vBox.getChildren().add(productTable);
        return vBox;
    }


    public VBox getAllProducts(){
        ObservableList<Product> data = Product.getAllProducts();
        return createTable(data);
    }

    public static Product getSelectedProduct(){
        return  productTable.getSelectionModel().getSelectedItem();
    }

    public static VBox getProductInCart(ObservableList<Product> data) {
        return createTable(data);
    }

}
