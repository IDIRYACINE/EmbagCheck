package idir.embag.Models.CheckObserver;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;

import idir.embag.App;
import idir.embag.Models.CheckDataModel.CheckModel;
import idir.embag.Models.CheckDataModel.CheckStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class ObservationController implements Initializable{

    @FXML
    private Button confirmButton , cancelButton;
    @FXML
    private JFXComboBox<CheckStatus> observationBox;
    
    private CheckModel checkModel;
    private CheckStatus newState ;
    private JFXDialog stage;
    private TableView<CheckModel> DataTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadObservationStates();
        initObervationBox();
    }

    private void loadObservationStates(){
        ObservableList<CheckStatus> observationStatus  =  FXCollections.observableArrayList();
        Arrays.stream(CheckStatus.values()).forEach(state -> observationStatus.add(state));


        observationBox.setItems(observationStatus);
    }
    private void initObervationBox(){
        observationBox.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> { newState = newValue;}
        );
    }

    @FXML
    private void cancelObservation(Event e){
        stage.close();
    }
    @FXML
    private void confirmObservation(Event e){
        checkModel.setStatus(newState);
        DataTable.refresh();
        App.dHelper.Update(checkModel);
        stage.close();
    }

    public void setCheckModel(CheckModel checkModel){
        this.checkModel = checkModel;
    }

    public void setDialogStage(JFXDialog dialog){
        stage = dialog;
    }

    public void setTable(TableView<CheckModel> table) {
        DataTable = table ;
    }
    
    


}
