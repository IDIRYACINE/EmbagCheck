package idir.embag.Models.CheckCreator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import idir.embag.Models.CheckDataModel.CheckModel;
import idir.embag.Models.CheckDataModel.CheckStatus;
import idir.embag.Models.CheckListDisplay.ChecksController;
import idir.embag.Utility.NumToStringFormater.CheckFormater;
import idir.embag.Utility.Printer.CheckPrintModel;
import idir.embag.Utility.Printer.CheckPrinter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class ChekFormController implements Initializable{


    @FXML
    private Button BPrint, BCancel, BConfirm;
    @FXML
    private Label LDate,Receiver,Amount,stringAmountF,stringAmountS,ID,Location;
    @FXML
    private TextField ReceiverField, IDField,LocationField,AmountField;
    @FXML
    AnchorPane checkPane;

    private String sReceiver , sAmount , sStringAmountF,sStringAmountS,sLocation,sDate;
    private Label currentLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       ReceiverField.textProperty().addListener(changeListener);
       AmountField.textProperty().addListener(changeListener);
       IDField.textProperty().addListener(changeListener);
       LocationField.textProperty().addListener(changeListener);
       sDate = getTime();
       LDate.setText(sDate);
       currentLabel = stringAmountF;
    }

    @FXML
    private void Print(Event event) throws IOException{
        CheckPrinter checkPrinter = new CheckPrinter();
        CheckPrintModel printModel = new CheckPrintModel(sAmount,sStringAmountF,sStringAmountS,sReceiver,sLocation); 
        Scene scene = new Scene(printModel);
        checkPrinter.Print(printModel);
    }
    @FXML 
    private void CreateCheck(){
        Double amount = Double.parseDouble(sAmount);
        Integer id = Integer.parseInt(ID.getText());
        CheckModel checkModel  = new CheckModel(sReceiver, sDate, amount, id, CheckStatus.Attendu, sLocation);
        ChecksController.addCheck(checkModel);
    }
           
    private String getTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now) ;
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
        sReceiver = value;
        Receiver.setText(value);
    }
    private void updateAmount (String value){
       
        value = CheckFormater.spaceFormater(value);
        sAmount = value;
        Amount.setText(value);
        String[] formatedAmountString = CheckFormater.NumParser(value , currentLabel ) ;
        sStringAmountF = formatedAmountString[0];
        stringAmountF.setText(sStringAmountF);
        sStringAmountS = formatedAmountString[1];
        stringAmountS.setText(sStringAmountS);
    }
    private void updateID(String value){
        ID.setText(value);
    }
    private void updateLocation(String value){
        sLocation = value;
        Location.setText(value);
    }
    
}