package idir.embag.Models.CheckObserver;

import java.io.IOException;

import com.jfoenix.controls.JFXDialog;

import idir.embag.Models.CheckDataModel.CheckModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CheckObserverModel {
    private JFXDialog dialog ; 
    private ObservationController controller ;

    public CheckObserverModel(StackPane parentPanel , TableView<CheckModel> table ) throws IOException {
        loadFxml(parentPanel);
        setUpController(table);
    }

    public void setCheckModel(CheckModel check){
        controller.setCheckModel(check); 
    }
    
    private void setUpController(TableView<CheckModel> table){
        controller.setDialogStage(dialog);
        controller.setTable(table);
    }

    public void show(){
        dialog.show();
    }

    public void close (){
        dialog.close();
    }

    private void loadFxml(StackPane parentPanel) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Forms/CheckObservation.fxml"));
        loader.load();
        VBox dialogPanel = (VBox)loader.getRoot();
        controller = loader.getController();
        dialog = new JFXDialog(parentPanel, dialogPanel, JFXDialog.DialogTransition.TOP);

    }

    
}
