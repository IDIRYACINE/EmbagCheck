package idir.embag.Utility.Printer;



import idir.embag.Utility.Printer.PrinterSelection.PrinterSelectorModel;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Window;


public class CheckPrinter {
    private Node node ;
    private StackPane pane ;
    private PageLayout pageLayout;

    public CheckPrinter(Node node , StackPane pane){
        this.node = node ;
        this.pane = pane;
    }

    public void printDialog(){
        if (Printer.getAllPrinters().size() > 0 ){
        PrinterSelectorModel printerSelectorModel = new PrinterSelectorModel(pane , this);
        printerSelectorModel.show();
        }
    }

    public void setPageLayout(PageLayout pLayout){
        pageLayout = pLayout;
    }

    public void print(){
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
          //  Window winodw = node.getScene().getWindow();
         // job.showPrintDialog(winodw);
          job.printPage(node);
          job.endJob();
            
        }

      }
    


  
    
}

