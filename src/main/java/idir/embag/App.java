package idir.embag;



import java.io.IOException;

import idir.embag.Utility.Database.DatabaseAcessHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    public static DatabaseAcessHelper dHelper ;
    @Override
    public void start(Stage stage) throws IOException{
        dHelper = new DatabaseAcessHelper();
        FXMLLoader loader = new FXMLLoader();
        loader.load(getClass().getResourceAsStream("/views/Main.fxml"));
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setTitle("Embag ");
        stage.setScene(scene);          
        stage.show();       
    }

    public static void main(String[] args) {
        launch(args);
    }

   

}