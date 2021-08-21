package idir.embag;



import java.io.IOException;

import idir.embag.FormControllers.MainController;
import idir.embag.Utility.Database.DatabaseAcessHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


/**
 * JavaFX App
 */
public class App extends Application {
    public static DatabaseAcessHelper dHelper ;
    @Override
    public void start(Stage stage) throws IOException{
        dHelper = new DatabaseAcessHelper();
        Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml")); 
        Scene scene = new Scene(root);
        stage.setTitle("Embag ");
        stage.setScene(scene);          
        stage.show();       
    }

    public static void main(String[] args) {
        launch(args);
    }

   

}