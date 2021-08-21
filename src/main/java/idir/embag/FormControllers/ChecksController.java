package idir.embag.FormControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import idir.embag.App;
import idir.embag.CustomViews.CheckObserverModel;
import idir.embag.CustomViews.SearchModel;
import idir.embag.Modules.CheckModel;
import idir.embag.Modules.CheckStatus;
import idir.embag.Utility.Database.Database;
import idir.embag.Utility.Database.DatabaseInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        DataTable.setItems(checks);
        setUpColumns();
    }

    @FXML
    private void newSearchDialog(Event e){
        searchModel.show();
    }

    @FXML
    private void refreshChecks(Event e){
        App.dHelper.loadData();
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
