package com.example.ecommerceproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterface {

    UserInterface(){
        loginPage();
        createHeaderBar();
        createFooterBar();
    }
    GridPane login;
    HBox headerBar, footerBar;
    Customer loggedInCustomer;
    ProductList productList = new ProductList();
    Button signInButton, placeOrderButton = new Button("Place Order");
    Label welcomeLabel;
    VBox productPage, body;
    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();
    public BorderPane createContent()
    {
        BorderPane root = new BorderPane();
//        root.getChildren().addAll(login);
        root.setPrefSize(800, 500);
        root.setCenter(login);
        root.setTop(headerBar);

        productPage = productList.getAllProducts() ;
//        root.setCenter(productPage);

        body = new VBox();
        body.setPadding(new Insets(10));
        root.setCenter(body);
        body.getChildren().add(productPage);

        root.setBottom(footerBar);
        return  root;
    }

    public void loginPage()
    {

        Text usernameText = new Text("UserName : ");
        Text PasswordText = new Text("Password : ");

        TextField username = new TextField("amey@gmail.com");
        username.setPromptText("Enter your UserName ");
        PasswordField password = new PasswordField();
        password.setText("Pass@123");
        password.setPromptText("Enter your Password");

        Button loginButton = new Button("Login");
        Label loginText = new Label("Login Here : ");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @java.lang.Override
            public void handle(ActionEvent actionEvent) {
                String name = username.getText();
                String pass = password.getText();
                Login login = new Login();
                loggedInCustomer = login.customerLogin(name, pass);

                if(loggedInCustomer!=null){
                    loginText.setText("Welcome, "+ loggedInCustomer.getName());
                    welcomeLabel.setText("Welcome, "+ loggedInCustomer.getName());
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                }
                else
                    loginText.setText("Login Failed! Please Log In Again!");
            }
        });

        login = new GridPane();
        login.setAlignment(Pos.CENTER);
        login.setHgap(10);
        login.setVgap(10);
        login.add(usernameText, 0, 0);
        login.add(username, 1, 0 );
        login.add(PasswordText, 0,1);
        login.add(password, 1,1);
        login.add(loginButton, 1,2);
        login.add(loginText, 0,2);
    }

    public  void createHeaderBar()
    {
        Button homeButton = new Button();
        Image img = new Image("D:\\Idea_Projects\\ECommerceProject\\src\\hm.png");
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        homeButton.setGraphic(imageView);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Here");
        searchBar.setPrefWidth(340);
        Button searchButton = new Button("Search");
         signInButton = new Button("Sign In");
         welcomeLabel = new Label();

         Button cartButton = new Button("My Cart");
         Button orderButton = new Button("Orders");

        headerBar = new HBox(10);
        headerBar.getChildren().addAll(homeButton, searchBar, searchButton, signInButton, cartButton, orderButton);
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setPadding(new Insets(20));

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(login);
                headerBar.getChildren().remove(signInButton);
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(loggedInCustomer==null) {
                    showDialogue("Please Log In First");
                    return;
                }
                body.getChildren().clear();
                VBox prodPage = ProductList.getProductInCart(itemsInCart);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(itemsInCart ==null){
                    showDialogue("Please select a product first");
                    return;
                }
                if(loggedInCustomer==null) {
                    showDialogue("Please Log In First");
                    return;
                }

                int count = Order.placeMultipleOrder(loggedInCustomer, itemsInCart);
                if(count!=0)
                    showDialogue("Order for "+count+" Products Place Succesfully!!");
                else
                    showDialogue("Order Failed!!");
            }

        });

        homeButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);

                if(loggedInCustomer==null) {
                    if(!headerBar.getChildren().contains(signInButton))
                        headerBar.getChildren().add(signInButton);
                }
            }
        });
    }

    public  void createFooterBar()
    {
        Button buyNowButton = new Button("Buy Now");
        Button addToCartButton = new Button("Add To Cart");

        footerBar = new HBox();
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.setPadding(new Insets(20));
        footerBar.getChildren().addAll(buyNowButton, addToCartButton);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = ProductList.getSelectedProduct();
                if(product==null){
                    showDialogue("Please select a product first");
                    return;
                }
                if(loggedInCustomer==null) {
                    showDialogue("Please Log In First");
                    return;
                }

                boolean status = Order.placeOrder(loggedInCustomer, product);
                if(status)
                    showDialogue("Order Place Succesfully!!");
                else
                    showDialogue("Order Failed!!");
            }
        });

        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = ProductList.getSelectedProduct();
                if(product==null){
                    showDialogue("Please select a product!!");
                    return;
                }

                if(loggedInCustomer==null) {
                    showDialogue("Please Log In First");
                    return;
                }

                itemsInCart.add(product);
                showDialogue("Added to Cart");
            }
        });
    }

    private void showDialogue(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.setTitle("Message");
        alert.showAndWait();
    }

}
