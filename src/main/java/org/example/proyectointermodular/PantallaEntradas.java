package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.MantenimientoEntradas;
import org.example.proyectointermodular.Objetos.Entradas;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

public class PantallaEntradas {

    @FXML
    private TableView<Entradas> tablaEstudiantes;
    @FXML
    private TableColumn<Entradas, Integer> idTable;
    @FXML
    private TableColumn<Entradas, LocalDate> fechaTable;
    @FXML
    private TableColumn<Entradas, Integer> precioTable;

    @FXML
    private DatePicker fechaDatePicker;
    @FXML
    private TextField PrecioTextField;

    @FXML
    private Button anyadirButton;
    @FXML
    private Button guardarButton;

    private Connection conexion;


    @FXML
    public void initialize() {
        conexion = MantenimientoEntradas.conectar(conexion);

        fechaTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFecha()));
        precioTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrecio()));

        tablaEstudiantes.setItems(MantenimientoEntradas.consultar(conexion));
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






