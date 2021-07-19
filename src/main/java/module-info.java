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
    opens idir.embag to javafx.fxml;
    opens idir.embag.Controllers to javafx.fxml;
    opens idir.embag.modules to javafx.base;
    exports idir.embag;
}
