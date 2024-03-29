package idir.embag.Models.CheckSearch;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXDialog;

import idir.embag.Models.CheckDataModel.CheckModel;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SearchView {
    private JFXDialog dialog ; 
    private SearchController controller;

    public SearchView(StackPane parentPanel) throws IOException {
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


    public void listenToSearchResult(ListChangeListener<ArrayList<CheckModel>> listener){
        controller.subscribeToSearchResult(listener);
    }
     
    
    
}
