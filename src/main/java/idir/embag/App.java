package idir.embag;



import java.io.IOException;

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
    @Override
    public void start(Stage stage) throws IOException{
        
        Parent root;
            root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
            Scene scene = new Scene(root);
           // stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Embag ");
            stage.setScene(scene);          
            //stage.getIcons().add(new Image(getClass().getResource("/images/iconApp.png"))));
            stage.show();       
    }

    public static void main(String[] args) {
        launch(args);
    }

   

}