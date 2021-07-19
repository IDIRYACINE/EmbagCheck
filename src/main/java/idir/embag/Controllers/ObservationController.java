package idir.embag.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;

import idir.embag.modules.CheckStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ObservationController implements Initializable{

    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private JFXComboBox observationBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadObservationStates();
        initObervationBox();
    }

    private void loadObservationStates(){
        ObservableList<String> observationStatus  =  FXCollections.observableArrayList();
        Arrays.stream(CheckStatus.values()).forEach(state -> observationStatus.add(state.toString()));


        observationBox.setItems(observationStatus);
    }
    private void initObervationBox(){
        observationBox.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> { 
  }
);
}

    @FXML
    private void cancelObservation(Event e){
        MainController.dialogBox.close();
    }
    @FXML
    private void confirmObservation(Event e){}
    


}
