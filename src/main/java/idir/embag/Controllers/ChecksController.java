package idir.embag.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import idir.embag.Database.Database;
import idir.embag.Database.DatabaseInterface;
import idir.embag.modules.CheckModel;
import idir.embag.modules.CheckStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChecksController implements Initializable{

    @FXML
    private Button NewCheck ;
    @FXML
    private TableView<CheckModel> DataTable;
    @FXML
    private TableColumn<CheckModel,String> Date ;
    @FXML
    private TableColumn<CheckModel,Integer> ID ;
    @FXML
    private TableColumn<CheckModel,String> Receiver ;
    @FXML
    private TableColumn<CheckModel,Integer> Amount ;
    @FXML
    private TableColumn<CheckModel,CheckStatus> Status ;


    private DatabaseInterface database ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpDatabase();
        DataTable.setItems(checks);
        setUpColumns();
       
        
    }

    @FXML
    private void CreateCheck(Event e){
        checks.add(new CheckModel("idir", "yacine", 100, 200, CheckStatus.Confirmed,""));
    }

    @FXML
    private void observeCheck(Event e ) throws IOException{
        MainController.dialogBox.show();  
        //DataTable.getSelectionModel().getSelectedItem();
    }

    public static void addCheck(CheckModel checkModel){
        checks.add(checkModel);
        System.out.println(checks);
    }

    private void setUpColumns(){
        Amount.setCellValueFactory(new PropertyValueFactory<CheckModel,Integer>("amount"));
        Date.setCellValueFactory(new PropertyValueFactory<CheckModel,String>("date"));
        Receiver.setCellValueFactory(new PropertyValueFactory<CheckModel,String>("receiver"));
        Status.setCellValueFactory(new PropertyValueFactory<CheckModel,CheckStatus>("status"));
        ID.setCellValueFactory(new PropertyValueFactory<CheckModel,Integer>("ID"));

    }

    private void setUpDatabase(){
        database = new Database();
        database.Connect();
    }

    private static ObservableList<CheckModel> checks = FXCollections.observableArrayList(
        
    );
    
}
