/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pujcovna;

import auta.Auto;
import auta.Barva;
import auta.OsobniAuto;
import auta.UzitkoveAuto;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Radek
 */
public class NewCar extends Application{
    
    TextField spz = new TextField("SPZ");
    ChoiceBox<Barva> barva = new ChoiceBox<>();
    TextField nosnost = new TextField("Nosnost");
    TextField km = new TextField("Ujeté kilometry");
    
    VBox vbox = new VBox();
    
    Button btnOk = new Button("OK");
    Button btnCan = new Button("Cancel");
    
    IPobocka tmpPob;
    int co;
    EnumPozice kde;
    MainFXController tmpPuj;

    public NewCar(IPobocka jaka, int co, MainFXController pujcovna){
        tmpPob = jaka;
        this.co = co;
        this.kde = kde;
        tmpPuj = pujcovna;
        
        try {
            start(new Stage());
        } catch (Exception ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Chyba!");
                a.setContentText("Chyba!");
                a.showAndWait();
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        barva.getItems().addAll(Barva.BILA, Barva.CERNA, Barva.CERVENA, Barva.MODRA, Barva.ZELENA);
        barva.getSelectionModel().select(0);
        
        if(co == 0){
            vbox.getChildren().addAll(spz, barva, km);
            vbox.getChildren().add(new FlowPane(btnOk, btnCan));
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(5);
        }else{
            vbox.getChildren().addAll(spz, nosnost, km);
            vbox.getChildren().add(new FlowPane(btnOk, btnCan));
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(5);
        }
        
        btnCan.setOnAction((event) -> {
            primaryStage.close();
        });
        
        btnOk.setOnAction((event) -> {
            Auto tmpAuto;
            if(co == 0){
                try {
                    tmpPob.vlozAuto(new OsobniAuto(spz.getText(), Integer.parseInt(km.getText()), 0, barva.getValue()));
                    tmpPuj.obnovAuta();
                    primaryStage.close();
                } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Chyba!");
                a.setContentText("Chyba!");
                a.showAndWait();
                }
            }else{
                try {
                    tmpPob.vlozAuto(new UzitkoveAuto(spz.getText(), Integer.parseInt(km.getText()), 0, Integer.parseInt(nosnost.getText())));
                    tmpPuj.obnovAuta();
                    primaryStage.close();
                } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Chyba!");
                a.setContentText("Chyba!");
                a.showAndWait();
                }
            }
        });
        
        primaryStage.setScene(new Scene(vbox));
        primaryStage.setTitle("Nové auto");
        primaryStage.showAndWait();
    }
    
}
