package idir.embag.Models.CheckCreator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import idir.embag.Utility.NumToStringFormater.CheckFormater;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChekFormController implements Initializable{


    @FXML
    private Button BPrint, BCancel, BConfirm;
    @FXML
    private Label LDate,Receiver,Amount,stringAmountF,stringAmountS,ID,Location;
    @FXML
    private TextField ReceiverField, IDField,LocationField,AmountField;
    private Label currentLabel;
    private CheckCreatorModel checkCreatorModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       checkCreatorModel = new CheckCreatorModel();
       ReceiverField.textProperty().addListener(changeListener);
       AmountField.textProperty().addListener(changeListener);
       IDField.textProperty().addListener(changeListener);
       LocationField.textProperty().addListener(changeListener);
       LDate.setText(checkCreatorModel.getDate());
       currentLabel = stringAmountF;
    }

    @FXML
    private void Print(Event event) throws IOException{
        checkCreatorModel.Print();

    }
    @FXML 
    private void CreateCheck(){
        checkCreatorModel.CreateCheck();
        
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
        checkCreatorModel.setReceiver(value);
        Receiver.setText(value);
    }

    private void updateAmount (String value){
        value = checkCreatorModel.filterAmount(value);
        checkCreatorModel.setNumAmount(value);
        Amount.setText(value);
        String[] formatedAmountString = CheckFormater.numToStringFormat(value , currentLabel ) ;
        checkCreatorModel.setFirstStrAmount(formatedAmountString[0]); 
        stringAmountF.setText(formatedAmountString[0]);
        checkCreatorModel.setSecondStrAmount(formatedAmountString[1]);
        stringAmountS.setText(formatedAmountString[1]);
    }
    private void updateID(String value){
        ID.setText(value);
    }
    private void updateLocation(String value){    
        checkCreatorModel.setLocation(value);;
        Location.setText(value);
    }

     
    
}