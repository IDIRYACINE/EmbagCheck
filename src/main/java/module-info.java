module idir.embag {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;
    requires javafx.base;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml ;
    requires javafx.swing;
    opens idir.embag to javafx.fxml;
    opens idir.embag.Models.CheckCreator to javafx.base,javafx.fxml;
    opens idir.embag.Models.CheckListDisplay to javafx.base,javafx.fxml;
    opens idir.embag.Models.CheckObserver  to javafx.base,javafx.fxml;
    opens idir.embag.Models.CheckSearch to javafx.base,javafx.fxml;
    opens idir.embag.Models to javafx.base,javafx.fxml;
    opens idir.embag.Models.CheckDataModel to javafx.base,javafx.fxml;

    exports idir.embag;
}
