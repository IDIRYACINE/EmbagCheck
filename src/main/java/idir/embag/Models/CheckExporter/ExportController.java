package idir.embag.Models.CheckExporter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import idir.embag.App;
import idir.embag.Models.CheckDataModel.CheckModel;
import idir.embag.Models.CheckSearch.SearchView;
import idir.embag.Utility.Exporter.Excel;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;

public class ExportController implements Initializable{

    private Excel excelManager;
    private SearchView searchModel;

    @FXML
    Button exports ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        excelManager = new Excel();
    }

    @FXML
    void exportAll ( ){
        ArrayList<CheckModel> checks =  App.dHelper.loadData();
        exportToExcel(checks);
    }

    @FXML
    void exportSelected(){
        searchModel.show();
    }

    public void setUpDialogs(StackPane rightPanel){
        try {
            searchModel = new SearchView(rightPanel);
            searchModel.listenToSearchResult(searchListener);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ListChangeListener<ArrayList<CheckModel>> searchListener = new ListChangeListener<ArrayList<CheckModel>>(){
        @Override
        public void onChanged(Change<? extends ArrayList<CheckModel>> result) {
            while(result.next()){
                if (result.wasAdded()){
                exportToExcel(result.getAddedSubList().get(0));
            }
            }
        }
        
    };

    private void exportToExcel(ArrayList<CheckModel> checks)
    {
        excelManager.setUpCellsData(checks);
        Window window =  ((Node)exports).getScene().getWindow();
        excelManager.saveSheet(window);
    }
    
    
}
