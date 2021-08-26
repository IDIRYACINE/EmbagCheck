package idir.embag.Models.CheckSearch;

import java.io.IOException;

import com.jfoenix.controls.JFXDialog;

import idir.embag.Models.CheckDataModel.CheckModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SearchModel {
    private JFXDialog dialog ; 
    private SearchController controller;

    public SearchModel(StackPane parentPanel) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Forms/CheckSearch.fxml"));
        loader.load();
        VBox dialogPanel = (VBox)loader.getRoot();
        dialog = new JFXDialog(parentPanel, dialogPanel, JFXDialog.DialogTransition.TOP);
        controller = loader.getController();
        controller.setDialogStage(dialog);
    }

    public void show(){
        dialog.show();
    }

    public void setTable(TableView<CheckModel> dataTable, ObservableList<CheckModel> checks) {
        controller.setUpTable(dataTable , checks);
    }

   

    
}
