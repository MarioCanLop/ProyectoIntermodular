package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.MantenimientoFerias;
import org.example.proyectointermodular.Objetos.Ventas;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

public class PantallaVentas {

    @FXML
    private TableView<Ventas> tablaEstudiantes;

    @FXML
    private TableColumn<Ventas, Integer> idTable;
    @FXML
    private TableColumn<Ventas, Integer> precioTable;
    @FXML
    private TableColumn<Ventas, LocalDate> fechaTable;

    @FXML
    private TextField precioTextField;
    @FXML
    private DatePicker fechaDatePicker;

    @FXML
    private Button anyadirButton;
    @FXML
    private Button guardarButton;

    private Connection conexion;

    @FXML
    public void initialize() {

        conexion = org.example.proyectointermodular.Mantenimiento.MantenimientoVentas.conectar(conexion);

        precioTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrecio()));
        fechaTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFecha()));

        // Cargar datos a la tabla
        tablaEstudiantes.setItems(org.example.proyectointermodular.Mantenimiento.MantenimientoVentas.consultar(conexion));
    }

    @FXML
    protected void buttonInicio() throws IOException {
        HelloApplication.setRoot("hello-view");
        System.out.println("Volviendo al inicio...");
    }


    public void onAnyadirButtonClick(ActionEvent actionEvent) {
    }

    public void onGuardarButtonClick(ActionEvent actionEvent) {
    }

    public void onEliminarButtonClick(ActionEvent actionEvent) {
    }

    public void onEditarButtonClick(ActionEvent actionEvent) {

    }
}







