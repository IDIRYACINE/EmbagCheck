package idir.embag.Models.CheckExporter;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ExportView {
    VBox panel;
    public ExportView(StackPane mainPanel){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ExportPanel.fxml"));     
        loader.load();
        panel = loader.getRoot();
        ExportController exportController = loader.getController();
        exportController.setUpDialogs(mainPanel);
    }
    catch(Exception e){

    }
}

    public VBox getPanel(){
        return panel;
    }

}
