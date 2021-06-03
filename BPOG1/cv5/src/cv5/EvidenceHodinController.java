/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Radek
 */
public class EvidenceHodinController implements Initializable {

    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    @FXML
    private PieChart pieChart;
    
    private ObservableList<Worker> workers = FXCollections.observableArrayList();
    @FXML
    private TableView<Worker> tableViewWorkers;
    @FXML
    private TableColumn<Worker, String> tableViewColName;
    @FXML
    private TableColumn<Worker, Integer> tableViewColId;
    @FXML
    private TableColumn<Worker, String> tableViewColSurname;
    @FXML
    private TableColumn<Worker, Integer> tableViewColYear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewWorkers.setItems(workers);
        tableViewWorkers.setEditable(true);
        
        tableViewColId.setCellValueFactory(new PropertyValueFactory("id"));
        tableViewColName.setCellValueFactory(new PropertyValueFactory("name"));
        tableViewColSurname.setCellValueFactory(new PropertyValueFactory("surname"));
        tableViewColYear.setCellValueFactory(new PropertyValueFactory("year"));
        
        tableViewColId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableViewColName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewColSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewColYear.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        
        
        pieChart.setAnimated(false);
        pieChart.setLegendVisible(true);
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(10);
    }    

    @FXML
    private void onAdd(ActionEvent event) {
        Worker result = showDialog();
        if (result != null) {
            workers.add(result);
            updatePieChart();
        }
    }

    @FXML
    private void onDelete(ActionEvent event) {
        workers.clear();
        updatePieChart();
    }
    
    private void updatePieChart(){
        pieChart.getData().clear();
        int pocet;
        for(int i = 1; i < 6; i++){
            pocet = countOfYear(i);
            if(pocet != 0){
             pieChart.getData().add(new PieChart.Data(String.format("%d. rocnik [%d]", i, pocet), pocet));   
            }
        }
    }
    
    private Worker showDialog(){
        Dialog<Worker> dialog = new Dialog();
        dialog.setTitle("Zadej novy zaznam.");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField tfId = new TextField();
        TextField tfName = new TextField();
        TextField tfSurname = new TextField();
        TextField tfYear = new TextField();
        
        grid.add(new Label("ID: "), 0, 0);
        grid.add(tfId, 1, 0);
        grid.add(new Label("Jmeno: "), 0, 1);
        grid.add(tfName, 1, 1);
        grid.add(new Label("Prijmeni: "), 0, 2);
        grid.add(tfSurname, 1, 2);
        grid.add(new Label("Rocnik: "), 0, 3);
        grid.add(tfYear, 1, 3);
        
        Node btnOk = dialog.getDialogPane().lookupButton(ButtonType.OK);
        btnOk.setDisable(true);
        
        ChangeListener clTextValidation = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnOk.setDisable(!isInputValid(tfId.getText(), tfName.getText(), tfSurname.getText(), tfYear.getText()));
            }

        };
        
        tfId.textProperty().addListener(clTextValidation);
        tfName.textProperty().addListener(clTextValidation);
        tfSurname.textProperty().addListener(clTextValidation);
        tfYear.textProperty().addListener(clTextValidation);
        
        
        dialog.getDialogPane().setContent(grid);
        tfName.requestFocus();
        
        dialog.setResultConverter(new Callback<ButtonType, Worker>() {
            @Override
            public Worker call(ButtonType param) {
                if((isInputValid(tfId.getText(), tfName.getText(), tfSurname.getText(), tfYear.getText())) && (param == ButtonType.OK)){
                    return new Worker(Integer.parseInt(tfId.getText()), tfSurname.getText(), tfName.getText(), Integer.parseInt(tfYear.getText()));
                }else{
                    return null;
                }
            }
        });
        return dialog.showAndWait().get();
    }
    
    private boolean isInputValid(String id, String name, String surname, String year) {
                int numberYear;
                int numberId;
                try{
                numberYear = Integer.parseInt(year);     
                }catch (Exception ex){
                    numberYear = -1;
                }
                try{
                numberId = Integer.parseInt(id);     
                }catch (Exception ex){
                    numberId = -1;
                }
                
                return (name.trim().length() > 0) && (numberYear > 0) && (numberId > 0) && (surname.trim().length() > 0);
            }

    @FXML
    private void onActionColName(TableColumn.CellEditEvent<Worker, String> event) {
        Worker worker = event.getRowValue();
        worker.setName(event.getNewValue());
        updatePieChart();
    }

    @FXML
    private void onActionColId(TableColumn.CellEditEvent<Worker, Integer> event) {
        Worker worker = event.getRowValue();
        worker.setId(event.getNewValue());
        updatePieChart();
    }

    @FXML
    private void onActionColSurname(TableColumn.CellEditEvent<Worker, String> event) {
        Worker worker = event.getRowValue();
        worker.setSurname(event.getNewValue());
        updatePieChart();
    }

    @FXML
    private void onActionColYear(TableColumn.CellEditEvent<Worker, Integer> event) {
        Worker worker = event.getRowValue();
        worker.setYear(event.getNewValue());
        updatePieChart();
    }
    
    private int countOfYear(Integer y){
        int tmp = 0;
        for (Worker worker : workers) {
            if(worker.getYear().intValue() == y){
                tmp++;
            }
        }
        return tmp;
    }
    
}
