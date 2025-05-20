package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.MantenimientoEstantes;
import org.example.proyectointermodular.Objetos.Estantes;

import java.io.IOException;
import java.sql.Connection;

public class PantallaEstantes {

    @FXML
    private TableView<Estantes> tablaEstudiantes;
    @FXML
    private TableColumn<Estantes, Integer> idTable;
    @FXML
    private TableColumn<Estantes, String> nombreTable;

    @FXML
    private TextField nombreTextField;

    @FXML
    private Button anyadirButton;
    @FXML
    private Button guardarButton;

    private Connection conexion;
    private int idSeleccionado;

    @FXML
    public void initialize() {
        conexion = MantenimientoEstantes.conectar(conexion);

        // Configurar columnas de la tabla
        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));

        // Cargar datos iniciales
        tablaEstudiantes.setItems(MantenimientoEstantes.consultar(conexion));
    }
    @FXML
    protected void buttonInicio() throws IOException {
        HelloApplication.setRoot("hello-view");
        System.out.println("Volviendo al inicio...");
    }


    public void onEditarButtonClick(ActionEvent actionEvent) {
    }

    public void onEliminarButtonClick(ActionEvent actionEvent) {
    }

    public void onAnyadirButtonClick(ActionEvent actionEvent) {
    }

    public void onGuardarButtonClick(ActionEvent actionEvent) {

    }
}





