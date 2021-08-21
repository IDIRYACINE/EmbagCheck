package idir.embag.Utility.Printer;

import java.io.FileInputStream;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

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
            System.out.println("job is not null");
            Window window = node.getScene().getWindow();
            job.showPrintDialog(window); 
            job.printPage(node);
            job.endJob();
        }
        else{
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            System.out.println("Number of print services: " + printServices.length);
        }
    }
    
}

