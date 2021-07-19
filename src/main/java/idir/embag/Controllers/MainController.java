package idir.embag.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDialog;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import idir.embag.App;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;

public class MainController implements Initializable{
    @FXML
    private StackPane rightPanel ;
    @FXML
    private FontAwesomeIconView checkIcon , dataIcon ;
    @FXML 
    private Label dataLabel , checkLabel ;
    @FXML
    private HBox dataBox , checkBox ;

    private Label activeLabel ;
    private FontAwesomeIconView activeIcon ;
    private HBox activeBox;

    private VBox dataPanel , checkPanel ;
    public static JFXDialog dialogBox ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
   
          try {
            loadPanels();
          } catch (IOException e) {
            e.printStackTrace();
          }
          setInitialScene();
           
    }
    

    
    @FXML
    private void dataClicked(Event event) throws IOException{
      setNode(dataPanel);
      setActiveStyle(dataBox,dataLabel, dataIcon);
    }
    
    @FXML
    private void checkCLicked(Event event) throws IOException{
      setNode(checkPanel);
      setActiveStyle(checkBox,checkLabel, checkIcon);
    }

    private void loadPanels() throws IOException{
      dataPanel = FXMLLoader.load(getClass().getResource("/views/Data.fxml"));
      checkPanel =  FXMLLoader.load(getClass().getResource("/views/Forms/checkForm.fxml"));
      VBox dialogPane = FXMLLoader.load(getClass().getResource("/views/Forms/CheckObservation.fxml"));
      dialogBox = new JFXDialog(rightPanel, dialogPane, JFXDialog.DialogTransition.TOP);
      
    }

    private void setNode(Node node){
      rightPanel.getChildren().clear();
      rightPanel.getChildren().add(node);

    }

    private void setActiveStyle(HBox hBox,Label label , FontAwesomeIconView icon){
    
     setPassiveStyle(activeBox,activeLabel, activeIcon);
      label.setStyle("-fx-text-fill:#3683dc;");
      icon.setStyle("-fx-fill:#3683dc;");
      hBox.setStyle("-fx-background-color: #eaf4fe;");
      activeIcon = icon;
      activeLabel = label;
      activeBox = hBox;
     
    }

    private void setPassiveStyle(HBox hBox,Label label , FontAwesomeIconView icon ){
      label.setStyle("-fx-text-fill:#818893;");
      icon.setStyle("-fx-fill:#818893;");
      hBox.setStyle("-fx-background-color: ffffff");
    }
    private void setInitialScene(){
      activeLabel = dataLabel;
      activeIcon = dataIcon;
      activeBox = dataBox;
      setActiveStyle(dataBox, dataLabel, dataIcon);
      setNode(dataPanel);
    };
    
}
