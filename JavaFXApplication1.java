/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.awt.Checkbox;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.*;

/**
 *
 * @author Shahriar Ivan
 */
public class JavaFXApplication1 extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    
//    int userNum = 3;
//    User[] user = new User[userNum];
    Button exitButton,loginButton,btn2_1,btn2_2,orderButton;
    
    Stage mainWindow;
    Scene scene1,scene2;
    
    @Override
    public void start(Stage primaryStage) {
        User[] user = new User[3];
        for(int i=0;i<user.length;i++)
        {
            user[i] = new User();
        }
        user[0].setUser("Ivan", "ivan");
        user[1].setUser("Shakkhor", "shakkhor");
        user[2].setUser("Ahsan", "ahsan");
        
        mainWindow = primaryStage;
        mainWindow.setTitle("Main Window");
        
        //Scene 1 starts
       
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(10,10,10,10));   //sets padding on top,right,bottom,left 
        grid1.setVgap(8);
        grid1.setHgap(10);
        
        //Username label
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);
        
        //Username input
        TextField nameInput = new TextField("Ivan");
        GridPane.setConstraints(nameInput, 1, 0);
        
        //Password label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);
        
        //Password input
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Your password");    //displays grayedout placeholder text
        GridPane.setConstraints(passInput, 1, 1);
        
        Label passInvalidText = new Label();
        passInvalidText.setText("*Invalid Password!");
        passInvalidText.getStyleClass().add("red-label");
        GridPane.setConstraints(passInvalidText, 2, 1);
        passInvalidText.setVisible(false);
        
        //Login button
        loginButton = new Button();
        loginButton.setText("Log in");
        loginButton.setOnAction(e -> {
            if(validatePassword(user,3,nameInput.getText(),passInput.getText()))
            {
                passInput.setText("");
                passInvalidText.setVisible(false);
                mainWindow.setScene(scene2);
            }
            else
            {
                passInput.setText("");
                passInvalidText.setVisible(true);
            }
        });
        GridPane.setConstraints(loginButton, 1, 2);
        
        //Sign Up label
        Label signUpLabel = new Label("Don't have an account?");
        GridPane.setConstraints(signUpLabel, 0, 3);
        
        //Sign Up Hyperlink
        Hyperlink signUpLink = new Hyperlink();
        signUpLink.setText("Sign Up");
        signUpLink.setOnAction(e -> {
            signUpLink.setVisited(false);
            closeProgram(); //will change into signUpScreen()
        });
        signUpLink.setStyle("-fx-font-size: 12pt");
        GridPane.setConstraints(signUpLink, 1, 3);
        
        //ExitButton
        exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(e -> closeProgram());
        GridPane.setConstraints(exitButton, 1, 4);
        
        grid1.getChildren().addAll(nameLabel,nameInput,passLabel,passInput,passInvalidText,loginButton,signUpLabel,signUpLink,exitButton);
        
        scene1 = new Scene(grid1, 512, 400);  //Scene(layoutName, xsize, ysize)
        scene1.getStylesheets().add("Lush.css");
        
        //Scene 1 ends
        
        
        
        
        //Scene 2 starts
        
        btn2_1 = new Button();
        btn2_1.setText("Login page");
        btn2_1.setOnAction(e -> {
            mainWindow.setScene(scene1);
        });
        
        btn2_2 = new Button();
        btn2_2.setText("Exit");
        btn2_2.setOnAction(e -> closeProgram());
        
        //Checkboxes
        CheckBox box1 = new CheckBox("Chicken Burger");
        CheckBox box2 = new CheckBox("Pizza");
        CheckBox box3 = new CheckBox("Pasta");
        CheckBox box4 = new CheckBox("Set Menu");
        CheckBox box5 = new CheckBox("Noshto Saidul");
        
        //ChoiceBox
        ChoiceBox<String> paymentMethod = new ChoiceBox<>();
        paymentMethod.getItems().addAll("Paypal","Visa","Master card");
        paymentMethod.setValue("Paypal");
        
        //Order button
        orderButton = new Button("Place Order");
        orderButton.setOnAction(e -> handleOptions(box1,box2,box3,box4,box5,paymentMethod));
        
        
        HBox topMenu = new HBox(20);    //20 px xdistance between each element in topMenu
        topMenu.getChildren().addAll(btn2_1);
        topMenu.setAlignment(Pos.CENTER);
        
        VBox centreMenu = new VBox(10);    //10 px ydistance between each element in bottomMenu
        centreMenu.setPadding(new Insets(10,10,10,10));
        centreMenu.getChildren().addAll(box1,box2,box3,box4,box5,paymentMethod,orderButton);
        
        VBox bottomMenu = new VBox(10);    //10 px ydistance between each element in bottomMenu
        bottomMenu.getChildren().addAll(btn2_2);
        bottomMenu.setAlignment(Pos.CENTER);
        
        BorderPane borderPane = new BorderPane();   //BorderPane can embed both HBox and VBox in itself
        borderPane.setTop(topMenu);
        borderPane.setCenter(centreMenu);
        borderPane.setBottom(bottomMenu);
        
        scene2 = new Scene(borderPane, 512, 400);
        scene2.getStylesheets().add("Lush.css");
        
        //Scene 2 ends
        
        
        
        
        mainWindow.setScene(scene1);
        mainWindow.show();
        mainWindow.setOnCloseRequest(e -> {
            e.consume();    //consume the default close button event for handling it manually
            closeProgram();
        });
    }
    
    //close program- - both scene1 and scene2
    private void closeProgram(){
        boolean result = CloseAlert.display("Confirm close", "Confirm exit?");
        if(result==true)
        {
            mainWindow.close();
        }
    }
    
    //validate Password - scene1
    private boolean validatePassword(User[] user,int num, String name, String pass){
        
        boolean found=false;
        for(int i=0;i<num;i++)
        {
            if(name.equals(user[i].get_name()) && pass.equals(user[i].get_pass()))
            {
                found = true;
                break;
            }
            
        }
        
        if(found)
            return true;
        else
            return false;
    }
    
    //Handle checkbox options - scene2
    private void handleOptions(CheckBox box1, CheckBox box2, CheckBox box3, CheckBox box4,CheckBox box5,ChoiceBox<String> paymentMethod){
        String message = "User order:\n";
        boolean order = false;
        
        if(box1.isSelected())
        {
            order = true;
            message += box1.getText();
            message += "\n";
        }
        if(box2.isSelected())
        {
            order = true;
            message += box2.getText();
            message += "\n";
        }
        if(box3.isSelected())
        {
            order = true;
            message += box3.getText();
            message += "\n";
        }
        if(box4.isSelected())
        {
            order = true;
            message += box4.getText();
            message += "\n";
        }
        if(box5.isSelected())
        {
            order = true;
            message += box5.getText();
            message += " toh oboshshoi\n";
        }
        if(order==false)
        {
            message += "nothing\n";
        }
        
        message+="Payment method:\n"+paymentMethod.getValue()+"\n";
        
        
        System.out.println(message);
    }
}
