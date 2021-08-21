package idir.embag.FormControllers;

import com.jfoenix.controls.JFXDialog;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SearchController {
    @FXML
    private CheckBox ID,Receiver,Amount,Location,BDate;
    @FXML
    private TextField IDField,ReceiverField,AmountMinField , AmountMaxField,
                      LocationField , DateStartField , DateEndField ;

    private JFXDialog dialogStage ;

    @FXML
    private void CancelSearch(){
        
        dialogStage.close();
    }
    @FXML
    private void ConfirmSearch(){
        dialogStage.close();
    }

    public void setDialogStage(JFXDialog dialog){
        dialogStage = dialog;
    }

    private String QueryMetaData(){
        String result = "";
        CheckBox[] Boxes = {ID ,Receiver , Location , Amount , BDate} ;
        int CHECKBOX_COUNT = 5 ;
        for (int i = 0 ; i < CHECKBOX_COUNT ; i++){
            if (Boxes[i].isSelected() == true){
                result += FieldData(i);
            }
        }
        return result ;
    }

    private String FieldData(int index){
        String[] fields = {"ID" , "Receiver" , "Location" , "Amount" , "Date"}; 
        String result = "";
        switch(index){
            case 0 : result += fields[index] +" : "+ IDField.getText() + " ;";
                break ;
            case 1 : result += fields[index] +" : "+ ReceiverField.getText() + " ;";
                break ; 
            case 2 : result += fields[index] +" : "+ LocationField.getText() + " ;";
                break ;
            case 3 : result += fields[index] +" : "+ AmountMinField.getText() + "," + AmountMaxField.getText() + " ;";
                break ;
            case 4 : result += fields[index] +" : "+ DateStartField.getText() + "," + DateEndField.getText() + " ;";
                break ;             
        }
        return result;
    } 




}
