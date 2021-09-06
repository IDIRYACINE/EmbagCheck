package idir.embag.Models;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import idir.embag.App;
import idir.embag.Models.CheckListDisplay.ChecksController;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.Event;

public class MainController implements Initializable{

    @FXML
    private StackPane rightPanel ;
    @FXML
    private VBox leftPanel ;
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



    @Override
    public void initialize(URL location, ResourceBundle resources) {
          try {
            loadPanels();
            App.stackPane = rightPanel;
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
      loadDataPanel();
      checkPanel =  FXMLLoader.load(getClass().getResource("/views/CheckCreationPanel.fxml"));      
    }
    
    private void loadDataPanel() throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CheckListDisplay.fxml"));    
      loader.load();
      dataPanel = loader.getRoot();
      ChecksController checksController = loader.getController();
      checksController.setUpDialogs(rightPanel);
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
      String css = getClass().getResource("/css/main.css").toExternalForm();
      leftPanel.getStylesheets().add(css);
      setActiveStyle(dataBox, dataLabel, dataIcon);
      setNode(dataPanel);

    };

  
    
}
