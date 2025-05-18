module org.example.proyectointermodular {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.google.errorprone.annotations;
    requires java.sql;
    requires java.desktop;

    opens org.example.proyectointermodular to javafx.fxml;
    exports org.example.proyectointermodular;
}