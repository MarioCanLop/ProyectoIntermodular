package org.example.proyectointermodular;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.MantenimientoFerias;
import org.example.proyectointermodular.Objetos.Ferias;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

public class PantallaFerias {

    @FXML
    private TableView<Ferias> tablaEstudiantes;
    @FXML
    private TableColumn<Ferias, Integer> idTable;
    @FXML
    private TableColumn<Ferias, String> nombreTable;
    @FXML
    private TableColumn<Ferias, LocalDate> fechaInicioTable;
    @FXML
    private TableColumn<Ferias, LocalDate> fechaFinTable;
    @FXML
    private TableColumn<Ferias, String> UbicacionTable;
    @FXML
    private TableColumn<Ferias, String> DescripcionTable;

    @FXML
    private TextField nombreTextField;
    @FXML
    private DatePicker fechaIncioDatePicker;
    @FXML
    private DatePicker fechaFinDatePicker;
    @FXML
    private TextField ubicacionTextField;
    @FXML
    private TextField DescripciónTextField;

    @FXML
    private Button anyadirButton;
    @FXML
    private Button guardarButton;

    private Connection conexion;
    private int idSeleccionado;

    @FXML
    public void initialize() {
        conexion = MantenimientoFerias.conectar(conexion);

        // Configurar las columnas
        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        fechaInicioTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFehcaInicio()));
        fechaFinTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFechaFin()));
        UbicacionTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getUbicacion()));
        DescripcionTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDescripcion()));

        tablaEstudiantes.setItems(MantenimientoFerias.consultar(conexion));
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



