package idir.embag.Models.CheckListDisplay;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import idir.embag.App;
import idir.embag.Models.CheckDataModel.*;
import idir.embag.Models.CheckObserver.CheckObserverModel;
import idir.embag.Models.CheckSearch.SearchModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

public class ChecksController implements Initializable{

    @FXML
    private TableView<CheckModel> DataTable;
    @FXML
    private TableColumn<CheckModel,String> Date,Receiver ;
    @FXML
    private TableColumn<CheckModel,Integer> ID ,Amount;
    @FXML
    private TableColumn<CheckModel,CheckStatus> Status ;


    private SearchModel searchModel;
    private CheckObserverModel observerModel ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpColumns();
        refreshChecks();
        DataTable.setItems(checks);

    }

    @FXML
    private void newSearchDialog(Event e){
        searchModel.show();
        searchModel.setTable(DataTable , checks);


    }

    @FXML
    private void refreshChecks( ){
        ArrayList<CheckModel> models = App.dHelper.loadData();
        checks.clear();
        for (CheckModel checkModel : models) {
            checks.add(checkModel);
        }
        
    }

    @FXML
    private void observeCheck(Event e ) throws IOException{
        CheckModel check = DataTable.getSelectionModel().getSelectedItem();
        observerModel.setCheckModel(check);
        observerModel.show();  
    }

    public static void addCheck(CheckModel checkModel){
        checks.add(checkModel);
        App.dHelper.add(checkModel);
    }

   

    private void setUpColumns(){
        Amount.setCellValueFactory(new PropertyValueFactory<CheckModel,Integer>("amount"));
        Date.setCellValueFactory(new PropertyValueFactory<CheckModel,String>("date"));
        Receiver.setCellValueFactory(new PropertyValueFactory<CheckModel,String>("receiver"));
        Status.setCellValueFactory(new PropertyValueFactory<CheckModel,CheckStatus>("status"));
        ID.setCellValueFactory(new PropertyValueFactory<CheckModel,Integer>("ID"));

    }

    public void setUpDialogs(StackPane rightPanel){
        try {
            searchModel = new SearchModel(rightPanel);
            observerModel = new CheckObserverModel(rightPanel,DataTable);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static ObservableList<CheckModel> checks = FXCollections.observableArrayList(
        
    );

    
}
