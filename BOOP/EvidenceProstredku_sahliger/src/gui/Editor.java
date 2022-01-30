/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import prostredky.Dodavka;
import prostredky.DodavkaTyp;
import prostredky.DopravniProstredek;
import prostredky.MojeBarva;
import prostredky.NakladniAutomobil;
import prostredky.OsobniAutomobil;
import prostredky.ProstredekTyp;
import prostredky.Traktor;
import sprava.Ovladani;

/**
 *
 * @author Radek
 */
public class Editor extends Application{
    
    private DopravniProstredek edit = null;
    private Ovladani tmpSpravce = null;
    private ListView<DopravniProstredek> tmpList = null;
    private boolean wrong = false;
    
    TextField txtSpz = new TextField();
    TextField txtHmo = new TextField();
    TextField txtTah = new TextField();
    TextField txtNos = new TextField();
    TextField txtSed = new TextField();
    TextField txtNap = new TextField();
    
    ComboBox<MojeBarva> cmbBar = new ComboBox<>();
    ComboBox<DodavkaTyp> cmbDod = new ComboBox<>();
    
    VBox vbox = new VBox();
    
    Button btnOk = new Button("OK");
    Button btnCan = new Button("Cancel");
    
    
    public Editor(Ovladani spravce, ListView<DopravniProstredek> list){
        tmpSpravce = spravce;
        tmpList = list;
        edit = spravce.dejKopiiAktualniPolozky();
        try {
            start(new Stage());
        } catch (Exception ex) {
            tmpSpravce.nastavErrorLog(msg -> {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Chyba!");
                a.setContentText(msg);
                a.showAndWait();
                });
        }
    }
    
    public Editor(Ovladani spravce, ListView<DopravniProstredek> list, ProstredekTyp pt){
        tmpSpravce = spravce;
        tmpList = list;
        novyPr(new Stage(), pt);
    }

    @Override
    public void start(Stage stage) throws Exception {
            switch(edit.getTyp()){
                case OSOBNI_AUTOMOBIL:
                    Arrays.asList(MojeBarva.values()).stream().forEach(cmbBar.getItems()::add);
                    vbox.getChildren().addAll(new FlowPane(new Label("SPZ:"), txtSpz),
                    new FlowPane(new Label("Barva:"), cmbBar),
                    new FlowPane(new Label("Pocet sedadel:"), txtSed),
                    new FlowPane(new Label("Hmotnost:"), txtHmo));
                    
                    txtSpz.setText(edit.getPozn());
                    cmbBar.getSelectionModel().select(edit.getBarva());
                    txtSed.setText(Integer.toString(edit.getPocet()));
                    txtHmo.setText(Float.toString(edit.getHmotnost()));
                    
                    
                    break;
                case NAKLADNI_AUTMOBIL:
                    vbox.getChildren().addAll(new FlowPane(new Label("SPZ:"), txtSpz),
                    new FlowPane(new Label("Pocet naprav:"), txtNap),
                    new FlowPane(new Label("Nosnost:"), txtNos),
                    new FlowPane(new Label("Hmotnost:"), txtHmo));
                    
                    txtSpz.setText(edit.getPozn());
                    txtNap.setText(Integer.toString(edit.getPocetN()));
                    txtNos.setText(Long.toString(edit.getNosnost()));
                    txtHmo.setText(Float.toString(edit.getHmotnost()));
                    
                    break;
                case TRAKTOR:
                    vbox.getChildren().addAll(new FlowPane(new Label("SPZ:"), txtSpz),
                    new FlowPane(new Label("Tah:"), txtTah),
                    new FlowPane(new Label("Hmotnost:"), txtHmo));
                    
                    txtSpz.setText(edit.getPozn());
                    txtTah.setText(Integer.toString(edit.getTah()));
                    txtHmo.setText(Float.toString(edit.getHmotnost()));
                    
                    break;
                case DODAVKA:
                    Arrays.asList(DodavkaTyp.values()).stream().forEach(cmbDod.getItems()::add);
                    vbox.getChildren().addAll(new FlowPane(new Label("SPZ:"), txtSpz),
                    new FlowPane(new Label("Typ dodavky:"), cmbDod),
                    new FlowPane(new Label("Hmotnost:"), txtHmo));
                    
                    txtSpz.setText(edit.getPozn());
                    cmbDod.getSelectionModel().select(edit.getDTyp());
                    txtHmo.setText(Float.toString(edit.getHmotnost()));
                    
                    break;
                default:
                    break;
            }
            
            vbox.getChildren().addAll(new FlowPane(btnOk, btnCan));
            vbox.setPadding(new Insets(10, 10, 10, 10));
            vbox.setSpacing(5);
            
            btnCan.setOnAction(eh -> {
                stage.close();
            });
            
            btnOk.setOnAction(eh -> {
                wrong = false;
                try{
                    if(txtSpz.getText().isEmpty() == true){
                            throw new NumberFormatException();
                        }
                    switch(edit.getTyp()){
                        case OSOBNI_AUTOMOBIL:
                            Float.parseFloat(txtHmo.getText());
                            Integer.parseInt(txtSed.getText());
                            break;
                        case NAKLADNI_AUTMOBIL:
                            Float.parseFloat(txtHmo.getText());
                            Long.parseLong(txtNos.getText());
                            Integer.parseInt(txtNap.getText());
                            break;
                        case DODAVKA:
                            Float.parseFloat(txtHmo.getText());
                            break;
                        case TRAKTOR:
                            Float.parseFloat(txtHmo.getText());
                            Integer.parseInt(txtTah.getText());
                            break;
                        default:
                            break;    
                }}catch(NumberFormatException ex){
                tmpSpravce.nastavErrorLog(msg -> {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Chyba!");
                a.setContentText(msg);
                a.showAndWait();
                wrong = true;
                });}
                if(wrong == false){
                tmpSpravce.editujAktualniPolozku((DopravniProstredek p) -> editujTo(p));
                stage.close();
                }
            });
            
        stage.setScene(new Scene(vbox));
        stage.setTitle("Editace");
        stage.showAndWait();
    }
    
    public DopravniProstredek editujTo(DopravniProstredek p){
        switch(p.getTyp()){
            case OSOBNI_AUTOMOBIL:
                p.setBarva(cmbBar.getValue());
                p.setPozn(txtSpz.getText());
                p.setHmotnost(Float.parseFloat(txtHmo.getText()));
                p.setPocet(Integer.parseInt(txtSed.getText()));
                break;
            case NAKLADNI_AUTMOBIL:
                p.setNosnost(Long.parseLong(txtNos.getText()));
                p.setPocetN(Integer.parseInt(txtNap.getText()));
                p.setHmotnost(Float.parseFloat(txtHmo.getText()));
                p.setPozn(txtSpz.getText());
                break;
            case DODAVKA:
                p.setDTyp(cmbDod.getValue());
                p.setPozn(txtSpz.getText());
                p.setHmotnost(Float.parseFloat(txtHmo.getText()));
                break;
            case TRAKTOR:
                p.setPozn(txtSpz.getText());
                p.setHmotnost(Float.parseFloat(txtHmo.getText()));
                p.setTah(Integer.parseInt(txtTah.getText()));
                break;
            default:
                break;
        }
        tmpList.getItems().clear();
        tmpSpravce.vypis((DopravniProstredek t) -> tmpList.getItems().add(t));
        return p;
    }
    
    public void novyPr(Stage stage, ProstredekTyp pt){
        switch(pt){
                case OSOBNI_AUTOMOBIL:
                    Arrays.asList(MojeBarva.values()).stream().forEach(cmbBar.getItems()::add);
                    cmbBar.getSelectionModel().select(0);
                    vbox.getChildren().addAll(new FlowPane(new Label("SPZ:"), txtSpz),
                    new FlowPane(new Label("Barva:"), cmbBar),
                    new FlowPane(new Label("Pocet sedadel:"), txtSed),
                    new FlowPane(new Label("Hmotnost:"), txtHmo));
                    break;
                case NAKLADNI_AUTMOBIL:
                    vbox.getChildren().addAll(new FlowPane(new Label("SPZ:"), txtSpz),
                    new FlowPane(new Label("Pocet naprav:"), txtNap),
                    new FlowPane(new Label("Nosnost:"), txtNos),
                    new FlowPane(new Label("Hmotnost:"), txtHmo));
                    break;
                case TRAKTOR:
                    vbox.getChildren().addAll(new FlowPane(new Label("SPZ:"), txtSpz),
                    new FlowPane(new Label("Tah:"), txtTah),
                    new FlowPane(new Label("Hmotnost:"), txtHmo));
                    break;
                case DODAVKA:
                    Arrays.asList(DodavkaTyp.values()).stream().forEach(cmbDod.getItems()::add);
                    cmbDod.getSelectionModel().select(0);
                    vbox.getChildren().addAll(new FlowPane(new Label("SPZ:"), txtSpz),
                    new FlowPane(new Label("Typ dodavky:"), cmbDod),
                    new FlowPane(new Label("Hmotnost:"), txtHmo));
                    break;
                default:
                    break;
            }
        
            vbox.getChildren().addAll(new FlowPane(btnOk, btnCan));
            vbox.setPadding(new Insets(10, 10, 10, 10));
            vbox.setSpacing(5);
            
            btnCan.setOnAction(eh -> {
                stage.close();
            });
            
            btnOk.setOnAction(eh -> {
                wrong = false;
                try{    
                    if(txtSpz.getText().isEmpty() == true){
                            throw new Exception();
                        }
                    switch(pt){
                        case OSOBNI_AUTOMOBIL:
                            Float.parseFloat(txtHmo.getText());
                            Integer.parseInt(txtSed.getText());
                            break;
                        case NAKLADNI_AUTMOBIL:
                            Float.parseFloat(txtHmo.getText());
                            Long.parseLong(txtNos.getText());
                            Integer.parseInt(txtNap.getText());
                            break;
                        case DODAVKA:
                            Float.parseFloat(txtHmo.getText());
                            break;
                        case TRAKTOR:
                            Float.parseFloat(txtHmo.getText());
                            Integer.parseInt(txtTah.getText());
                            break;
                        default:
                            break; 
                }}catch(Exception ex){
                tmpSpravce.nastavErrorLog(msg -> {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Chyba!");
                a.setContentText(msg);
                a.showAndWait();
                wrong = true;
                });}
                if(wrong == false){
                switch(pt){
                        case OSOBNI_AUTOMOBIL:
                            tmpSpravce.vlozPolozku(new OsobniAutomobil(Integer.parseInt(txtSed.getText()), cmbBar.getValue(), Float.parseFloat(txtHmo.getText()), txtSpz.getText()));
                            stage.close();
                            break;
                        case NAKLADNI_AUTMOBIL:
                            tmpSpravce.vlozPolozku(new NakladniAutomobil(Integer.parseInt(txtNap.getText()), Long.parseLong(txtNos.getText()), Float.parseFloat(txtHmo.getText()), txtSpz.getText()));
                            stage.close();
                            break;
                        case DODAVKA:
                            tmpSpravce.vlozPolozku(new Dodavka(cmbDod.getValue(), Float.parseFloat(txtHmo.getText()), txtSpz.getText()));
                            stage.close();
                            break;
                        case TRAKTOR:
                            tmpSpravce.vlozPolozku(new Traktor(Integer.parseInt(txtTah.getText()), Float.parseFloat(txtHmo.getText()), txtSpz.getText()));
                            stage.close();
                            break;
                        default:
                            break; 
                }
                }
            });
            
        stage.setScene(new Scene(vbox));
        stage.setTitle("Novy prostredek");
        stage.showAndWait();
    }
    }
   
