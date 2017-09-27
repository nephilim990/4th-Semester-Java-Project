/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.stage.Modality;

/**
 *
 * @author Shahriar Ivan
 */
public class CloseAlert {
    
    static boolean answer;
    
    public static boolean display(String title, String message){
        Stage closeAlert = new Stage();
        
        closeAlert.initModality(Modality.APPLICATION_MODAL);    //sets other Stages inactive when this stage is active
        closeAlert.setTitle(title);
        closeAlert.setMinWidth(250);
        
        Label label1 = new Label();
        label1.setText(message);
        label1.setStyle("-fx-font-size: 12pt");
        
        Button yes = new Button("OK");
        Button no = new Button("Cancel");
        
        yes.setOnAction(e -> {
            answer = true;
            closeAlert.close();
        });
        no.setOnAction(e -> {
            answer = false;
            closeAlert.close();
        });
        
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(10,10,10,10));   //sets padding on top,right,bottom,left 
        grid1.setVgap(8);
        grid1.setHgap(10);
        
        GridPane.setConstraints(label1, 0, 0);
        GridPane.setConstraints(yes, 1, 1);
        GridPane.setConstraints(no, 2, 1);
        
        grid1.getChildren().addAll(label1,yes,no);
        
        Scene scene1 = new Scene(grid1);
        
        closeAlert.setScene(scene1);
        closeAlert.showAndWait();
        
        return answer;
    }
}
