module com.aks.securefilemanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;

    opens com.aks.securefilemanager to javafx.fxml;
    exports com.aks.securefilemanager;
    exports com.aks.securefilemanager.Controller;
    opens com.aks.securefilemanager.Controller to javafx.fxml;
}