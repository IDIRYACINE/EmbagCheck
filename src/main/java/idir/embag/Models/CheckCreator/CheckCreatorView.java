package idir.embag.Models.CheckCreator;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class CheckCreatorView {
    VBox panel ;
   public  CheckCreatorView( ){
        try{
         panel =  FXMLLoader.load(getClass().getResource("/views/CheckCreationPanel.fxml"));    
        }
        catch(Exception e){ }
    }

    public VBox getPanel(){
        return panel ;
    }
}
