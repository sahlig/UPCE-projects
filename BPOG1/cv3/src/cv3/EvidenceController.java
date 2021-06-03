/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv3;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author Radek
 */
public class EvidenceController implements Initializable {

    @FXML
    private ListView<Book> bookListView;
    @FXML
    private Button deleteBookButton;
    @FXML
    private Button addAuthButton;
    @FXML
    private Button deleteAuthButton;
    @FXML
    private Button addBookButton;
    @FXML
    private TextField nazevTF;
    @FXML
    private TextField cenaTF;
    @FXML
    private ComboBox<String> autorCombo;
    @FXML
    private Spinner<String> zanrSpin;
    @FXML
    private Spinner<Integer> pocetSpin;

    
    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    private ObservableList<String> authorList = FXCollections.observableArrayList("Karel May", "J.R.R. Tolkien", "Booboo Bennet");
    private ObservableList<String> genreList = FXCollections.observableArrayList("beletrie", "fantasy", "poezie", "naucna");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cenaTF.setText("100");
        autorCombo.setItems(authorList);
        autorCombo.getSelectionModel().selectFirst();
        
        SpinnerValueFactory factoryGenres = new SpinnerValueFactory.ListSpinnerValueFactory(genreList);
        zanrSpin.setValueFactory(factoryGenres);
        
        SpinnerValueFactory factoryPocet = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        pocetSpin.setValueFactory(factoryPocet);
        
        bookListView.setItems(bookList);
    }    

    @FXML
    private void deleteBook(ActionEvent event) {
        Book selectedItem = bookListView.getSelectionModel().getSelectedItem();
        bookList.remove(selectedItem);
        
    }

    @FXML
    private void addAuth(ActionEvent event) {
        TextInputDialog tin = new TextInputDialog("Jmeno Prijmeni");
        tin.setTitle("Pridejte autora");
        tin.setHeaderText("Pridani noveho autora");
        tin.setContentText("Prosim zadejte jmeno noveho autora:");
        
        Optional<String> name = tin.showAndWait();
        if(name.isPresent()){
            authorList.add(name.get());
        }
    }

    @FXML
    private void deleteAuth(ActionEvent event) {
        ChoiceDialog<String> cod = new ChoiceDialog<>(authorList.get(0), authorList);
        cod.setTitle("Smazte autora");
        cod.setHeaderText("Odebirani autora");
        cod.setContentText("Prosim vyberte, ktereho autora chcete smazat:");
        
        Optional<String> name = cod.showAndWait();
        if(name.isPresent()){
            authorList.remove(cod.getSelectedItem());
        }
    }

    @FXML
    private void addBook(ActionEvent event) {
        String name = nazevTF.getText().trim();
        if(name.isEmpty()){
            return;
        }
        
        String auth = autorCombo.getValue();
        String genre = zanrSpin.getValue();
        Integer price = -1;
        try {
            price = Integer.parseInt(cenaTF.getText());
        } catch (Exception e){
            return;
        }
        
        Integer count = pocetSpin.getValue();
        
        bookList.add(new Book(name, auth, genre, price, count));
    }
    
}
