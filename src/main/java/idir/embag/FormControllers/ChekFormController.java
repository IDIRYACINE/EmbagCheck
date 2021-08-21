package idir.embag.FormControllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import idir.embag.Modules.CheckModel;
import idir.embag.Modules.CheckStatus;
import idir.embag.Utility.Formater.CheckFormater;
import idir.embag.Utility.Printer.CheckPrinter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ChekFormController implements Initializable{


    @FXML
    private Button BPrint;
    @FXML
    private Button BCancel;
    @FXML
    private Button BConfirm;
    @FXML
    private Label Date;
    @FXML
    private Label Receiver;
    @FXML
    private Label Amount;
    @FXML
    private Label stringAmount;
    @FXML
    private Label ID;
    @FXML
    private Label Location;
    @FXML
    private TextField ReceiverField;
    @FXML
    private TextField IDField;
    @FXML
    private TextField LocationField;
    @FXML
    private TextField AmountField;
    @FXML
    private AnchorPane checkPane;

    private CheckModel checkModel ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       ReceiverField.textProperty().addListener(changeListener);
       AmountField.textProperty().addListener(changeListener);
       IDField.textProperty().addListener(changeListener);
       LocationField.textProperty().addListener(changeListener);
       checkModel =  new CheckModel("idir", "yacine", 0, 0, CheckStatus.Confirmed,"");

    }

    @FXML
    private void Print(Event event){
        CheckPrinter checkPrinter = new CheckPrinter();
        checkPrinter.Print(checkPane);
    }
    @FXML 
    private void CreateCheck(){
        ChecksController.addCheck(checkModel);
    }
           
    

    private ChangeListener<String> changeListener = new ChangeListener<String>(){
        Pattern pattern = Pattern.compile("(?<=id=).+?(?=,)");
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            Matcher matcher = pattern.matcher(observable.toString());
            String fieldName = "";
            while(matcher.find()){
                fieldName = matcher.group().toString();
            }
            switch (fieldName){
                case "ReceiverField" : updateReceiver(newValue);
                break;
                case "AmountField" : updateAmount(newValue);
                break;
                case "LocationField" : updateLocation(newValue);
                break;
                default : updateID(newValue);

            } 
        }
    };

    private void updateReceiver(String value){
        Receiver.setText(value);
    }
    private void updateAmount (String value){
        Amount.setText(value);
        stringAmount.setText(CheckFormater.NumParser(value));
        checkModel.setAmount(Integer.parseInt(value));
       

    }
    private void updateID(String value){
        ID.setText(value);
        checkModel.setID(Integer.parseInt(value));
    }
    private void updateLocation(String value){
        Location.setText(value);
        checkModel.setLocation(value);
    }
    

}


    

