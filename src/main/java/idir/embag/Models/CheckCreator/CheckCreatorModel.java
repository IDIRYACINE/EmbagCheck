package idir.embag.Models.CheckCreator;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import idir.embag.App;
import idir.embag.Models.CheckDataModel.CheckModel;
import idir.embag.Models.CheckDataModel.CheckStatus;
import idir.embag.Models.CheckListDisplay.ChecksController;
import idir.embag.Utility.NumToStringFormater.CheckFormater;
import idir.embag.Utility.Printer.CheckPrintModel;
import idir.embag.Utility.Printer.CheckPrinter;
import javafx.scene.Scene;

public class CheckCreatorModel {

    private String date;
    private String numAmount , firstStrAmount , secondStrAmount , receiver , location , strId;

    CheckCreatorModel(){
        date = getTime();
    }
    
    public void Print() throws IOException{
        CheckPrintModel printModel = new CheckPrintModel(numAmount,firstStrAmount,secondStrAmount,receiver,location); 
        new Scene(printModel);
        CheckPrinter checkPrinter = new CheckPrinter(printModel,App.stackPane);
        checkPrinter.printDialog();
    }

    public void CreateCheck(){
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(numAmount.replace(" ", "")));
        CheckModel checkModel  = new CheckModel(receiver, date, amount , strId, CheckStatus.Attendu, location);
        ChecksController.addCheck(checkModel);
    }
           
    private  String getTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now) ;
    }

    public String getDate(){
        return date;
    }
    public void setId(String id){
        strId = id;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setReceiver(String receiver){
        this.receiver = receiver;
    }
    public void setFirstStrAmount(String amount){
        firstStrAmount = amount ;
    }
    public void setSecondStrAmount(String amount){
        secondStrAmount = amount;
    }
      
    public void setNumAmount (String value){
        numAmount = value;
     }
 

    public String filterAmount(String original){
        if (!original.matches("[0-9\\.]*")) {
            original = original.replaceAll("[^0-9\\.]", "");
        }

        original = CheckFormater.spaceFormater(original);

        return original;
    }
  
 

}
