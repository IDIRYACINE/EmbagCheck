package idir.embag.Models.CheckExporter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import idir.embag.App;
import idir.embag.Models.CheckDataModel.CheckModel;
import idir.embag.Utility.Exporter.Excel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Window;

public class ExportController implements Initializable{

    private Excel excelManager;

    @FXML
    Button exports ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        excelManager = new Excel();
    }

    @FXML
    void export (ActionEvent event){
        ArrayList<CheckModel> checks =  App.dHelper.loadData();
        excelManager.setUpCellsData(checks);
        Window window =  ((Node)exports).getScene().getWindow();
        excelManager.saveSheet(window);
    }

    
}
