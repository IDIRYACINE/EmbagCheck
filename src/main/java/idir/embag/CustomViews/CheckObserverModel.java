package idir.embag.CustomViews;

import java.io.IOException;

import com.jfoenix.controls.JFXDialog;

import idir.embag.FormControllers.ObservationController;
import idir.embag.Modules.CheckModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CheckObserverModel {
    private JFXDialog dialog ; 
    private ObservationController controller ;

    public CheckObserverModel(StackPane parentPanel  ) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Forms/CheckObservation.fxml"));
        loader.load();
        VBox dialogPanel = (VBox)loader.getRoot();
        controller = loader.getController();
        dialog = new JFXDialog(parentPanel, dialogPanel, JFXDialog.DialogTransition.TOP);
        setDialogStage();
    }

    public void setCheckModel(CheckModel check){
        controller.setCheckModel(check); 
    }
    
    private void setDialogStage(){
        controller.setDialogStage(dialog);
    }

    public void show(){
        dialog.show();
    }

    public void close (){
        dialog.close();
    }
    
}