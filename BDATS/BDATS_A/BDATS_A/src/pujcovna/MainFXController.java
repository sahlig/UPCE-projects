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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author Radek
 */
public class MainFXController implements Initializable {

    @FXML
    private ChoiceBox<EnumPozice> novPujcChoice;
    @FXML
    private Button novPujc;
    @FXML
    private Button prvPujc;
    @FXML
    private Button prePujc;
    @FXML
    private Button nasPujc;
    @FXML
    private Button posPujc;
    @FXML
    private ChoiceBox<EnumPozice> odsPujcChoice;
    @FXML
    private Button odsPujc;
    @FXML
    private ChoiceBox<EnumPozice> novAutoChoice;
    @FXML
    private Button novAuto;
    @FXML
    private Button prvAuto;
    @FXML
    private Button preAuto;
    @FXML
    private Button nasAuto;
    @FXML
    private Button posAuto;
    @FXML
    private ChoiceBox<EnumPozice> zapAutoChoice;
    @FXML
    private Button zapAuto;
    @FXML
    private ChoiceBox<EnumPozice> odsAutoChoice;
    @FXML
    private Button odsAuto;
    @FXML
    private Button prvZap;
    @FXML
    private Button preZap;
    @FXML
    private Button nasZap;
    @FXML
    private Button posZap;
    @FXML
    private ChoiceBox<EnumPozice> vraZapChoice;
    @FXML
    private Button vraZap;
    @FXML
    private ListView<IPobocka> pujcList;
    @FXML
    private ListView<Auto> autoList;
    @FXML
    private ListView<Auto> zapList;

    private IAutopujcovna seznam;
    @FXML
    private Button generateIt;
    @FXML
    private Button saveIt;
    @FXML
    private Button loadIt;
    @FXML
    private Button deleteCars;
    @FXML
    private Button deleteAll;
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        seznam = new Autopujcovna();
        novPujcChoice.getItems().addAll(EnumPozice.PRVNI, EnumPozice.PREDCHUDCE, EnumPozice.NASLEDNIK, EnumPozice.POSLEDNI);
        novPujcChoice.getSelectionModel().select(0);
        odsPujcChoice.getItems().addAll(EnumPozice.PRVNI, EnumPozice.PREDCHUDCE, EnumPozice.AKTUALNI, EnumPozice.NASLEDNIK, EnumPozice.POSLEDNI);
        odsPujcChoice.getSelectionModel().select(0);
        novAutoChoice.getItems().addAll(EnumPozice.PRVNI, EnumPozice.PREDCHUDCE, EnumPozice.NASLEDNIK, EnumPozice.POSLEDNI);
        novAutoChoice.getSelectionModel().select(0);
        zapAutoChoice.getItems().addAll(EnumPozice.PRVNI, EnumPozice.PREDCHUDCE, EnumPozice.AKTUALNI, EnumPozice.NASLEDNIK, EnumPozice.POSLEDNI);
        zapAutoChoice.getSelectionModel().select(0);
        odsAutoChoice.getItems().addAll(EnumPozice.PRVNI, EnumPozice.PREDCHUDCE, EnumPozice.AKTUALNI, EnumPozice.NASLEDNIK, EnumPozice.POSLEDNI);
        odsAutoChoice.getSelectionModel().select(0);
        vraZapChoice.getItems().addAll(EnumPozice.PRVNI, EnumPozice.PREDCHUDCE, EnumPozice.AKTUALNI, EnumPozice.NASLEDNIK, EnumPozice.POSLEDNI);
        vraZapChoice.getSelectionModel().select(0);
        
        
        //---------------- Tlacitka na pobocky-------------------
        novPujc.setOnAction((event) -> {
            try {
                seznam.vlozPobocku(new Pobocka(createPobocka()), novPujcChoice.getValue());
                obnovPujcovny();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        prvPujc.setOnAction((event) -> {
            try {
                seznam.zpristupniPobocku(EnumPozice.PRVNI);
                pujcList.getSelectionModel().select(seznam.zpristupniPobocku(EnumPozice.AKTUALNI));
                obnovAuta();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        prePujc.setOnAction((event) -> {
            try {
                seznam.zpristupniPobocku(EnumPozice.PREDCHUDCE);
                pujcList.getSelectionModel().select(seznam.zpristupniPobocku(EnumPozice.AKTUALNI));
                obnovAuta();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        nasPujc.setOnAction((event) -> {
            try {
                seznam.zpristupniPobocku(EnumPozice.NASLEDNIK);
                pujcList.getSelectionModel().select(seznam.zpristupniPobocku(EnumPozice.AKTUALNI));
                obnovAuta();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        posPujc.setOnAction((event) -> {
            try {
                seznam.zpristupniPobocku(EnumPozice.POSLEDNI);
                pujcList.getSelectionModel().select(seznam.zpristupniPobocku(EnumPozice.AKTUALNI));
                obnovAuta();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        odsPujc.setOnAction((event) -> {
            try {
                seznam.odeberPobocku(odsPujcChoice.getValue());
                obnovPujcovny();
                obnovAuta();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        //---------- Tlacitka na auta------------------
        
        novAuto.setOnAction((event) -> {
            createAuto();
        });
        
        prvAuto.setOnAction((event) -> {
            try {
                seznam.zpristupniAuto(EnumPozice.PRVNI);
                autoList.getSelectionModel().select(seznam.zpristupniAuto(EnumPozice.AKTUALNI));
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        preAuto.setOnAction((event) -> {
            try {
                seznam.zpristupniAuto(EnumPozice.PREDCHUDCE);
                autoList.getSelectionModel().select(seznam.zpristupniAuto(EnumPozice.AKTUALNI));
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        nasAuto.setOnAction((event) -> {
            try {
                seznam.zpristupniAuto(EnumPozice.NASLEDNIK);
                autoList.getSelectionModel().select(seznam.zpristupniAuto(EnumPozice.AKTUALNI));
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        posAuto.setOnAction((event) -> {
            try {
                seznam.zpristupniAuto(EnumPozice.POSLEDNI);
                autoList.getSelectionModel().select(seznam.zpristupniAuto(EnumPozice.AKTUALNI));
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        zapAuto.setOnAction((event) -> {
            try {
                seznam.vypujcAuto(zapAutoChoice.getValue());
                obnovAuta();
                obnovZapujcene();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        odsAuto.setOnAction((event) -> {
            try {
                seznam.odeberAuto(odsAutoChoice.getValue());
                obnovAuta();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        //------------------ Tlacitka na vypujcena auta----------------------
        
        prvZap.setOnAction((event) -> {
            try {
                seznam.zpristupniVypujceneAuto(EnumPozice.PRVNI);
                zapList.getSelectionModel().select(seznam.zpristupniVypujceneAuto(EnumPozice.AKTUALNI));
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        preZap.setOnAction((event) -> {
            try {
                seznam.zpristupniVypujceneAuto(EnumPozice.PREDCHUDCE);
                zapList.getSelectionModel().select(seznam.zpristupniVypujceneAuto(EnumPozice.AKTUALNI));
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        nasZap.setOnAction((event) -> {
            try {
                seznam.zpristupniVypujceneAuto(EnumPozice.NASLEDNIK);
                zapList.getSelectionModel().select(seznam.zpristupniVypujceneAuto(EnumPozice.AKTUALNI));
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        posZap.setOnAction((event) -> {
            try {
                seznam.zpristupniVypujceneAuto(EnumPozice.POSLEDNI);
                zapList.getSelectionModel().select(seznam.zpristupniVypujceneAuto(EnumPozice.AKTUALNI));
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        vraZap.setOnAction((event) -> {
            try {
                seznam.vratAuto(vraZapChoice.getValue());
                obnovAuta();
                obnovZapujcene();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        saveIt.setOnAction((event) -> {
            try {
                zalohujTo("zaloha.bin", seznam);
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        loadIt.setOnAction((event) -> {
            try {
                obnovTo("zaloha.bin", seznam);
                obnovAuta();
                obnovPujcovny();
                obnovZapujcene();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        //--------- Tlacitka na zbytek funkci---------------------
        
        generateIt.setOnAction((event) -> {
            try {
                seznam.vlozPobocku(new Pobocka("AAA"), EnumPozice.PRVNI);
                seznam.vlozPobocku(new Pobocka("ESSA"), EnumPozice.PRVNI);
                seznam.vlozPobocku(new Pobocka("Kanada"), EnumPozice.PRVNI);
                seznam.vlozPobocku(new Pobocka("NotAScam"), EnumPozice.PRVNI);
                seznam.zpristupniPobocku(EnumPozice.PRVNI);
                seznam.vlozAuto(new OsobniAuto("4H34728", 180000, 0, Barva.CERNA), EnumPozice.PRVNI);
                seznam.vlozAuto(new OsobniAuto("5W12233", 180040, 0, Barva.BILA), EnumPozice.PRVNI);
                seznam.vlozAuto(new OsobniAuto("9C46665", 1805600, 0, Barva.ZELENA), EnumPozice.PRVNI);
                seznam.zpristupniPobocku(EnumPozice.NASLEDNIK);
                seznam.vlozAuto(new OsobniAuto("8S46666", 200000, 0, Barva.BILA), EnumPozice.PRVNI);
                seznam.vlozAuto(new UzitkoveAuto("4S21321321", 1000, 0, 100), EnumPozice.PRVNI);
                seznam.zpristupniPobocku(EnumPozice.NASLEDNIK);
                seznam.vlozAuto(new OsobniAuto("8L456123", 200333, 0, Barva.BILA), EnumPozice.PRVNI);
                seznam.vlozAuto(new OsobniAuto("8L456dfg23", 2213, 0, Barva.CERNA), EnumPozice.PRVNI);
                seznam.vlozAuto(new OsobniAuto("45sa6d4", 4563142, 0, Barva.BILA), EnumPozice.PRVNI);
                seznam.vlozAuto(new OsobniAuto("as87d4s2f", 456785, 0, Barva.MODRA), EnumPozice.PRVNI);
                seznam.zpristupniPobocku(EnumPozice.NASLEDNIK);
                seznam.vlozAuto(new OsobniAuto("AS6546546D", 456785, 0, Barva.MODRA), EnumPozice.PRVNI);
                seznam.vlozAuto(new OsobniAuto("wads12132", 456785, 0, Barva.MODRA), EnumPozice.PRVNI);
                seznam.vlozAuto(new UzitkoveAuto("ww8888w8w", 1000, 0, 100), EnumPozice.PRVNI);
                seznam.vlozAuto(new UzitkoveAuto("666", 666, 0, 666), EnumPozice.PRVNI);
                seznam.vlozAuto(new UzitkoveAuto("SSSddddaaa", 1000, 0, 100), EnumPozice.PRVNI);
                obnovAuta();
                obnovPujcovny();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        deleteCars.setOnAction((event) -> {
            try {
                seznam.zrusPobocku();
                obnovAuta();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        deleteAll.setOnAction((event) -> {
            seznam.zrus();
            obnovAuta();
            obnovPujcovny();
            obnovZapujcene();
        });
        
    }

    public void obnovPujcovny(){
        try {
            pujcList.getItems().clear();
            Iterator<Pobocka> ite = seznam.iterator(eTyp.POBOCKA);
            while(ite.hasNext()){
                pujcList.getItems().add(ite.next());
            }
            try{
            pujcList.getSelectionModel().select(seznam.zpristupniPobocku(EnumPozice.AKTUALNI));
            }catch(Exception exc){
                
            }
        } catch (Exception ex) {
            myAlert();
        }
        
    }
    public void obnovAuta(){
        try {
            autoList.getItems().clear();
            Iterator<Auto> ite = seznam.iterator(eTyp.AUTOMOBIL);
            while(ite.hasNext()){
                autoList.getItems().add(ite.next());
            }
            try{
            autoList.getSelectionModel().select(seznam.zpristupniAuto(EnumPozice.AKTUALNI));
            }catch(Exception exc){
                
            }
        } catch (Exception ex) {
            myAlert();
        }
    }
    public void obnovZapujcene(){
        try {
            zapList.getItems().clear();
            Iterator<Auto> ite = seznam.iterator(eTyp.VYPUJCENY);
            while(ite.hasNext()){
                zapList.getItems().add(ite.next());
            }
            try{
            zapList.getSelectionModel().select(seznam.zpristupniAuto(EnumPozice.AKTUALNI));
            }catch(Exception exc){
                
            }
        } catch (Exception ex) {
            myAlert();
        }
    }
    
    private void myAlert(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("Chyba!");
        a.setHeaderText("Chyba!");
        a.showAndWait();
    }
    private String createPobocka(){
        TextInputDialog dialog = new TextInputDialog("Název");
        dialog.setTitle("Nová pobočka");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        return result.get();
        }else{
            return "Unknown";
        }
    }
    
    private void createAuto(){
        ChoiceDialog<String> choice = new ChoiceDialog<>();
        choice.getItems().addAll("Osobní auto", "Užitkové auto");
        
        choice.setTitle("Typ auta");
        Optional<String> result = choice.showAndWait();
        if(result.isPresent()){
            if(result.get().equals("Osobní auto")){
                try {
                    new NewCar(seznam.zpristupniPobocku(EnumPozice.AKTUALNI), 0, novAutoChoice.getValue(), this);
                } catch (Exception ex) {
                    myAlert();
                }
            }else if(result.get().equals("Užitkové auto")){
                try {
                    new NewCar(seznam.zpristupniPobocku(EnumPozice.AKTUALNI), 1, novAutoChoice.getValue(), this);
                } catch (Exception ex) {
                    myAlert();
                }
            }
        }
    }
    
    public <T> void zalohujTo(String jmenoSouboru, IAutopujcovna autopujcovna)
            throws Exception {
        try {
            Objects.requireNonNull(autopujcovna);
            ObjectOutputStream vystup
                    = new ObjectOutputStream(
                            new FileOutputStream(jmenoSouboru));
            vystup.writeObject(autopujcovna);
            vystup.close();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
    
    public <T> void obnovTo(String jmenoSouboru, IAutopujcovna autopujcovna)
            throws Exception {
        try {
            Objects.requireNonNull(autopujcovna);
            ObjectInputStream vstup
                    = new ObjectInputStream(
                            new FileInputStream(jmenoSouboru));
            autopujcovna.zrus();
            autopujcovna = (IAutopujcovna) vstup.readObject();

            vstup.close();
            
            seznam = autopujcovna;
        } catch (Exception ex) {
            throw new Exception(ex);
    }}
    
    
}

