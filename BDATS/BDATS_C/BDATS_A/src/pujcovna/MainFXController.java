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
import generator.Generator;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import seznam.AbstrDoubleList;
import seznam.IAbstrDoubleList;

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
    private Button novAuto;
    @FXML
    private Button zapAuto;
    @FXML
    private Button odsAuto;
    @FXML
    private Button vraZap;
    @FXML
    private ListView<Auto> pujcList;
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
    @FXML
    private ChoiceBox<eTypProhl> typIteratoru;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnNajit;
    @FXML
    private Button btnNajitVsude;
    @FXML
    private Button btnOdstranVsude;
    
    private IAbstrDoubleList<Auto> backup;
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        seznam = new Autopujcovna();
        typIteratoru.getItems().addAll(eTypProhl.HLOUBKA, eTypProhl.SIRKA);
        typIteratoru.getSelectionModel().select(0);
        
        
        
        btnRefresh.setOnAction((event) -> {
            obnovAuta();
            obnovPujcovny();
            obnovZapujcene();
        });
        
        //---------------- Tlacitka na pobocky-------------------



        
        //---------- Tlacitka na auta------------------
        
        novAuto.setOnAction((event) -> {
            createAuto();
            obnovPujcovny();
        });
        
        
        zapAuto.setOnAction((event) -> {
            try {
                seznam.vypujcAuto(autoList.getSelectionModel().getSelectedItem());
                obnovAuta();
                obnovZapujcene();
                obnovPujcovny();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        odsAuto.setOnAction((event) -> {
            try {
                seznam.odeberAuto(autoList.getSelectionModel().getSelectedItem());
                obnovAuta();
                obnovPujcovny();
            } catch (Exception ex) {
                myAlert();
            }
        });
        
        
        //------------------ Tlacitka na vypujcena auta----------------------
        
        
        vraZap.setOnAction((event) -> {
            try {
                seznam.vratAuto(zapList.getSelectionModel().getSelectedItem());
                obnovAuta();
                obnovZapujcene();
                obnovPujcovny();
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
                backup = new AbstrDoubleList<Auto>();
                int kolik = createGen();
                for (int i = 0; i < kolik; i++) {
                    backup.vlozPrvni(Generator.generujJeden());
                }
                seznam.zrus();
                seznam = new Autopujcovna(kolik);
                long startDate = System.nanoTime();
                Iterator ite = backup.iterator();
                while(ite.hasNext()){
                    seznam.vlozAuto((Auto) ite.next());
                }
                long endDate = System.nanoTime();
                System.out.println("Generování pomocí metody vlož");
                System.out.println((endDate - startDate) + " ms");
                seznam.zrus();
                seznam = new Autopujcovna(kolik);
                seznam.budovani(backup);
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
            Iterator<Auto> ite = seznam.iterator(eTyp.AUTOMOBIL, typIteratoru.getValue(), 0);
            while(ite.hasNext()){
                pujcList.getItems().add(ite.next());
            }
        } catch (Exception ex) {
            myAlert();
        }
        
    }
    public void obnovAuta(){
        try {
            autoList.getItems().clear();
            Iterator<Auto> ite = seznam.iterator(eTyp.AUTOMOBIL, typIteratoru.getValue(), 1);
            while(ite.hasNext()){
                autoList.getItems().add(ite.next());
            }
        } catch (Exception ex) {
            myAlert();
        }
    }
    public void obnovZapujcene(){
        try {
            zapList.getItems().clear();
            Iterator<Auto> ite = seznam.iterator(eTyp.VYPUJCENY, typIteratoru.getValue(), 0);
            while(ite.hasNext()){
                zapList.getItems().add(ite.next());
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
    
    private int createGen(){
        TextInputDialog dialog = new TextInputDialog("Kolik aut");
        dialog.setTitle("Počet aut ke generování");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        return Integer.parseInt(result.get());
        }else{
            return 0;
        }
    }
    
    private Auto createKlic(){
        TextInputDialog dialog = new TextInputDialog("SPZ");
        dialog.setTitle("Hledání v pobočce");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        return new OsobniAuto(result.get(), 0, 0, Barva.BILA);
        }else{
            return null;
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
                    new NewCar(seznam.getAuta(), 0, this);
                } catch (Exception ex) {
                    myAlert();
                }
            }else if(result.get().equals("Užitkové auto")){
                try {
                    new NewCar(seznam.getAuta(), 1, this);
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

