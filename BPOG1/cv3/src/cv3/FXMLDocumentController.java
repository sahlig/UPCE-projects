/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author Radek
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private ListView<String> listThis;
    @FXML
    private Button addButton;
    @FXML
    private Button removeAllButton;
    @FXML
    private Button removeOneButton;
    @FXML
    private TextField addTextField;
    
    private ObservableList<String> listMem = FXCollections.observableArrayList("Franta Siska", "Adam Pil", "Boris Vodka");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listThis.setItems(listMem);
        listThis.getSelectionModel().selectFirst();
    }    
    
    @FXML
    private void addThis(ActionEvent event) {
        String name = addTextField.getText();
        if (name.isEmpty()) {
            return;
        }
        
        listMem.add(name);
        addTextField.clear();
    }

    @FXML
    private void removeAll(ActionEvent event) {
        listMem.clear();
    }

    @FXML
    private void removeOne(ActionEvent event) {     
        listMem.remove(listThis.getSelectionModel().getSelectedItems());
    }
    
}
