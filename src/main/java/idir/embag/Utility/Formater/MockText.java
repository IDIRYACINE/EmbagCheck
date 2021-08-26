package idir.embag.Utility.Formater;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MockText extends Text{
    
    public MockText(String value){
        Font defaultFont =  new Font("Serif Bold", 13f);
        setText(value);
        setFont(defaultFont);
        setTextAlignment(TextAlignment.LEFT);
    }

    public void update(String value){
        setText(value);
    }


}
