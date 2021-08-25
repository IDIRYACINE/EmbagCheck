package idir.embag.Utility.Printer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout.Alignment;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CheckPrintModel extends Pane{
    private static Font defaultFont =  new Font("System", 18f);
    private static Font smallFont = new Font("SansSerif",14f);

    public CheckPrintModel(String amount , String amountStringF ,String amountStringS , String receiver , String location ){
       setUpBounds();
       setUpLabels(amount,amountStringF,amountStringS,receiver,location);
    }

    private void setUpBounds(){
        double ccpCheckWidth = 642.3;
        double ccpCheckHeight = 264.6;
        setWidth(ccpCheckWidth);
        setHeight(ccpCheckHeight);
    }

    private Label createLabel(String value , double width ,TextAlignment alignment ,Font font, double posX , double posY){
        Label label = new Label(value);
        label.setMinWidth(width);
        label.setTextAlignment(alignment);
        label.setFont(font);
        label.setLayoutX(posX);
        label.setLayoutY(posY);
        return label;
    }

    private void setUpLabels(String amount , String amountStringF ,String amountStringS , String receiver , String location ){
        ObservableList<Node> children =  getChildren();
        children.add(createLabel(amount, 127, TextAlignment.LEFT, defaultFont, 500, 24));
        children.add(createLabel(amountStringF, 390, TextAlignment.LEFT, defaultFont, 172, 53));
        children.add(createLabel(amountStringS, 530, TextAlignment.LEFT, defaultFont, 31, 77));
        children.add(createLabel(receiver, 480, TextAlignment.CENTER, defaultFont, 110, 106));
        children.add(createLabel(location, 180, TextAlignment.RIGHT, smallFont, 315, 125));
        children.add(createLabel(getDate(), 92, TextAlignment.RIGHT, smallFont, 516, 125));
    }

    private String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now) ;
        
    }

}
