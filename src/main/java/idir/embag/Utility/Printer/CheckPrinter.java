package idir.embag.Utility.Printer;


import java.io.File;
import java.io.FileInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

import javafx.print.PrinterJob;
import javafx.scene.Node;
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

    private void PrintDoc(){
        try {
            File file = new File("path/to/pdf");
            DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;
            PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            attr.add(MediaSizeName.ISO_A4);
            FileInputStream fis = new FileInputStream(file);
            Doc doc = new SimpleDoc(fis, flavor, null);
            DocPrintJob job = PrintServiceLookup.lookupDefaultPrintService().createPrintJob();
            job.print(doc, attr);
            fis.close();
        }
    
            catch(Exception e){
                System.out.println(e);
            }
    }

    /*
    

    */
    
}

