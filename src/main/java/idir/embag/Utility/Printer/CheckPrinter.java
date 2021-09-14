package idir.embag.Utility.Printer;



import idir.embag.Utility.Printer.PrinterSelection.PrinterSelectorModel;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;


public class CheckPrinter {
    private Node node ;
    private StackPane pane ;
    private PageLayout pageLayout;
    private Printer printer ;

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

    public void setPageLayout(PageLayout pLayout , Printer printer){
        pageLayout = pLayout;
        this.printer = printer ;
    }

    public void print(double xScale , double yScale){
        node.getTransforms().add(new Scale(xScale, yScale));
        PrinterJob job =  PrinterJob.createPrinterJob(printer);
        
        if (job != null) {
        
          job.printPage(pageLayout,node);
          job.endJob();
            
        }

      }
    


  
    
}

