module ConversorONE {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;
    requires org.json.chargebee;

    opens ConversorOne to javafx.fxml;
    exports ConversorOne;
}