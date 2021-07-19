package idir.embag.Printer;

import java.io.FileInputStream;

import idir.embag.App;

import java.awt.print.*;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.print.Paper;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;
import javafx.stage.Window;

public class CheckPrinter {

    public void Print(Node node){
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){
        Window window = node.getScene().getWindow();
        job.showPrintDialog(window); 
        job.printPage(node);
        job.endJob();
 }
    }
    
}

