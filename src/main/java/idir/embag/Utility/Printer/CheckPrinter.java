package idir.embag.Utility.Printer;



import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.transform.Scale;
import javafx.stage.Window;


public class CheckPrinter {

    public void Print(Node node){
    
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout =  printer.getDefaultPageLayout();//printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT,0.75,0.75,0.01,0);
        // Printable area
        double pWidth = pageLayout.getPrintableWidth();
        double pHeight = pageLayout.getPrintableHeight();
    
        // Node's (Image) dimensions
        double nWidth = node.getBoundsInParent().getWidth();
        double nHeight = node.getBoundsInParent().getHeight();
    
        // How much space is left? Or is the image to big?
        double widthLeft = pWidth - nWidth;
        double heightLeft = pHeight - nHeight;
    
        // scale the image to fit the page in width, height or both
        double scale;
    
        if (widthLeft < heightLeft) scale = pWidth / nWidth;
        else scale = pHeight / nHeight;
    
        // preserve ratio (both values are the same)
        node.getTransforms().add(new Scale(scale, scale));
        PrinterJob job =  PrinterJob.createPrinterJob();

        if (job != null) {
            Window winodw = node.getScene().getWindow();
          job.showPrintDialog(winodw);
          job.printPage(node);
          job.endJob();
            
        }

      
        
    }


  
    
}

