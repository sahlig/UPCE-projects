/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Time;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import prostredky.DopravniProstredek;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kolekce.LinSeznam;
import prostredky.DopravniProstredekKlic;
import prostredky.ProstredekTyp;
import sprava.Ovladani;
import sprava.SpravaProstredku;

/**
 *
 * @author Radek
 */
public class MainFx extends Application {

    private Ovladani spravce = new SpravaProstredku();
    boolean wrong = false;

    private final BorderPane pane = new BorderPane();
    private final HBox dolniBox = new HBox();
    private final VBox bocniBox = new VBox();
    private final ListView<DopravniProstredek> list = new ListView<>();
    private final Label labProch = new Label("Procházení");
    private final Button btnPrv = new Button("Prvni");
    private final Button btnDal = new Button("Dalsi");
    private final Button btnPos = new Button("Posledni");
    private final Label labPrik = new Label("Příkazy");
    private final Button btnEdi = new Button("Edituj");
    private final Button btnZob = new Button("Zobraz");
    private final Button btnVyj = new Button("Vyjmi");
    private final Button btnCle = new Button("Clear");
    private final Button btnGen = new Button("Generuj");
    private final Button btnUlo = new Button("Uloz");
    private final Button btnNac = new Button("Nacti");
    private final Label labNovy = new Label("Nový:");
    private final ComboBox<ProstredekTyp> cmbNovy = new ComboBox<>();
    private final Label labFilt = new Label("Filtr:");
    private final ComboBox<ProstredekTyp> cmbFilt = new ComboBox<>();
    private final ComboBox<String> cmbNajd = new ComboBox<>();
    private final Button btnZal = new Button("Zalohuj");
    private final Button btnObn = new Button("Obnov");
    private final Button btnZru = new Button("Zrus");

    @Override
    public void start(Stage stage) throws Exception {

        //--------------------------Funkce-----------------------
        btnPrv.setOnAction(eh -> {
            spravce.prejdiNaPrvniPolozku();
            list.getSelectionModel().selectFirst();
        });

        btnPos.setOnAction(eh -> {
            spravce.prejdiNaPosledniPolozku();
            list.getSelectionModel().selectLast();
        });

        btnDal.setOnAction(eh -> {
            spravce.prejdiNaDalsiPolozku();
            list.getSelectionModel().selectNext();
        });

        btnGen.setOnAction(eh -> {
            spravce.generujData(1);
            obnovList();
        });

        btnUlo.setOnAction(eh -> {
            spravce.ulozTextSoubor("prostredky.txt", perzistence.Perzistence.mapperOutput);
        });

        btnNac.setOnAction(eh -> {
            spravce.nactiTextSoubor("prostredky.txt", perzistence.Perzistence.mapperInput);
            obnovList();
        });

        btnZal.setOnAction(eh -> {
            spravce.ulozDoSouboru("zaloha.bin");
        });

        btnObn.setOnAction(eh -> {
            spravce.nactiZeSouboru("zaloha.bin");
            obnovList();
        });

        btnZru.setOnAction(eh -> {
            spravce.zrus();
            list.getItems().clear();
        });

        btnCle.setOnAction(eh -> {
            list.getItems().clear();
        });

        btnZob.setOnAction(eh -> {
            obnovList();
            spravce.nastavKomparator(Comparator.comparing(DopravniProstredek::getId)
                    .thenComparing(DopravniProstredek::getPozn));
            spravce.nastavAktualniPolozku(new DopravniProstredekKlic(spravce.dejKopiiAktualniPolozky().getId(), spravce.dejKopiiAktualniPolozky().getPozn()));
            list.getSelectionModel().select(spravce.najdiPolozku(new DopravniProstredekKlic(spravce.dejKopiiAktualniPolozky().getId(), spravce.dejKopiiAktualniPolozky().getPozn())));
        });

        btnVyj.setOnAction(eh -> {
            spravce.vyjmiAktualniPolozku();
            obnovList();
        });

        list.getSelectionModel().selectedItemProperty().addListener((ob, oV, nV) -> {
            spravce.nastavKomparator(Comparator.comparing(DopravniProstredek::getId)
                    .thenComparing(DopravniProstredek::getPozn));
            spravce.nastavAktualniPolozku(new DopravniProstredekKlic(nV.getId(), nV.getPozn()));
        });

        cmbFilt.setPromptText("Filtr");
        Arrays.asList(ProstredekTyp.values()).stream().filter(p -> p != ProstredekTyp.KLIC && p != ProstredekTyp.TEST).forEach(cmbFilt.getItems()::add);

        cmbFilt.getSelectionModel().selectedItemProperty().addListener((ob, oV, nV) -> {
            if (nV != ProstredekTyp.NON_FILTER) {
                list.getItems().clear();
                spravce.stream().filter(dp -> dp.getTyp() == nV).forEach(list.getItems()::add);
            } else {
                obnovList();
            }
        });

        cmbNovy.setPromptText("Novy");
        Arrays.asList(ProstredekTyp.values()).stream().filter(p -> p != ProstredekTyp.KLIC && p != ProstredekTyp.TEST && p != ProstredekTyp.NON_FILTER).forEach(cmbNovy.getItems()::add);
        
        cmbNovy.setOnAction(eh -> {
            if(cmbNovy.getSelectionModel().getSelectedItem() != null){
            new Editor(spravce, list, cmbNovy.getValue());
            cmbNovy.getSelectionModel().clearSelection();
            obnovList();
            }
        });

        btnEdi.setOnAction(eh -> {
            new Editor(spravce, list);
            obnovList();
        });

        cmbNajd.getItems().addAll("Najdi", "Podle ID", "Podle SPZ", "Podle ID a SPZ");
        cmbNajd.getSelectionModel().select(0);

        cmbNajd.getSelectionModel().selectedItemProperty().addListener((ob, oV, nV) -> {
            if(!nV.equals("Najdi")){
            hledej(nV);
            spravce.nastavKomparator(Comparator.comparing(DopravniProstredek::getId)
                    .thenComparing(DopravniProstredek::getPozn));
            if(!list.getItems().isEmpty()){
            list.getSelectionModel().select(spravce.najdiPolozku(new DopravniProstredekKlic(spravce.dejKopiiAktualniPolozky().getId(), spravce.dejKopiiAktualniPolozky().getPozn())));
            }else{
                spravce.nastavErrorLog(msg -> {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setHeaderText("Chyba!");
                        a.setContentText(msg);
                        a.showAndWait();
                    });
            }
            cmbNajd.getSelectionModel().select(0);
            }
            
        });

        //---------------------------Novy seznam-------------------------
        spravce.vytvorSeznam(LinSeznam::new);
        //--------------------------Zahajeni GUI---------------------
        dolniBox.getChildren().addAll(btnGen, btnUlo, btnNac, labNovy, cmbNovy, labFilt, cmbFilt, cmbNajd, btnZal, btnObn, btnZru);
        bocniBox.getChildren().addAll(labProch, btnPrv, btnDal, btnPos, labPrik, btnEdi, btnVyj, btnZob, btnCle);

        dolniBox.setPadding(new Insets(10, 10, 10, 10));
        dolniBox.setSpacing(5);
        bocniBox.setPadding(new Insets(10, 10, 10, 10));
        bocniBox.setSpacing(5);

        pane.setCenter(list);
        pane.setBottom(dolniBox);
        pane.setRight(bocniBox);

        list.setEditable(false);

        stage.setScene(new Scene(pane));
        stage.setTitle("Správa dopravních prostředků");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void obnovList() {
        list.getItems().clear();
        spravce.vypis((DopravniProstredek t) -> list.getItems().add(t));
    }

    private void hledej(String podle) {
        Pane root = new Pane();
        Stage window = new Stage();
        VBox v = new VBox();
        HBox h = new HBox();
        BorderPane bPane = new BorderPane();
        Button btnOk = new Button("OK");
        Button btnCan = new Button("Cancel");
        TextField spz = new TextField("SPZ");
        TextField id = new TextField("ID");

        switch (podle) {
            case "Podle ID":
                v.getChildren().add(id);
                bPane.setCenter(v);
                break;
            case "Podle SPZ":
                v.getChildren().add(spz);
                bPane.setCenter(v);
                break;
            case "Podle ID a SPZ":
                v.getChildren().addAll(id, spz);
                bPane.setCenter(v);
                break;
            default:
                break;
        }

        btnCan.setOnAction(eh -> {
            window.close();
        });

        btnOk.setOnAction(eh -> {
            wrong = false;
            switch (podle) {
                case "Podle ID":
                try {
                    Integer.parseInt(id.getText());
                } catch (NumberFormatException ex) {
                    wrong  = true;
                    spravce.nastavErrorLog(msg -> {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setHeaderText("Chyba!");
                        a.setContentText(msg);
                        a.showAndWait();
                    });
                }
                break;
                case "Podle SPZ":
                    if (spz.getText().isEmpty()) {
                        wrong = true;
                        spravce.nastavErrorLog(msg -> {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setHeaderText("Chyba!");
                            a.setContentText(msg);
                            a.showAndWait();
                        });
                    }
                    break;
                case "Podle ID a SPZ":
                try {
                    Integer.parseInt(id.getText());
                } catch (NumberFormatException ex) {
                    wrong = true;
                    spravce.nastavErrorLog(msg -> {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setHeaderText("Chyba!");
                        a.setContentText(msg);
                        a.showAndWait();
                    });
                }
                if (spz.getText().isEmpty()) {
                    wrong = true;
                    spravce.nastavErrorLog(msg -> {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setHeaderText("Chyba!");
                        a.setContentText(msg);
                        a.showAndWait();
                    });
                }
                break;
                default:
                    break;
            }
            if(wrong == false){
                switch(podle){
                case "Podle ID":
                spravce.nastavKomparator(Comparator.comparing(DopravniProstredek::getId));
                spravce.nastavAktualniPolozku(new DopravniProstredekKlic(Integer.parseInt(id.getText())));
                break;
            case "Podle SPZ":
                spravce.nastavKomparator(Comparator.comparing(DopravniProstredek::getPozn));
                spravce.nastavAktualniPolozku(new DopravniProstredekKlic(spz.getText()));
            case "Podle ID a SPZ":
                spravce.nastavKomparator(Comparator.comparing(DopravniProstredek::getId)
                    .thenComparing(DopravniProstredek::getPozn));
                spravce.nastavAktualniPolozku(new DopravniProstredekKlic(Integer.parseInt(id.getText()), spz.getText()));
                break;
            default:
                break;
            }
             window.close();
            }
        });

        h.getChildren().addAll(btnOk, btnCan);
        bPane.setBottom(h);
        root.getChildren().addAll(bPane);
        window.setScene(new Scene(root, 300, 175));
        window.showAndWait();
        }
    }


