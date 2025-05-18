package org.example.proyectointermodular;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        conexion = MantenimientoFerias.conectar();

        // Configurar las columnas
        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(MantenimientoFerias.obtenerId(data.getValue())));
        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        fechaInicioTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFehcaInicio()));
        fechaFinTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFechaFin()));
        UbicacionTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getUbicacion()));
        DescripcionTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDescripcion()));

        tablaEstudiantes.setItems(MantenimientoFerias.consultar(conexion));
    }

    @FXML
    public void onAnyadirButtonClick() {
        String nombre = nombreTextField.getText().trim();
        LocalDate fechaInicio = fechaIncioDatePicker.getValue();
        LocalDate fechaFin = fechaFinDatePicker.getValue();
        String ubicacion = ubicacionTextField.getText().trim();
        String descripcion = DescripciónTextField.getText().trim();

        if (nombre.isEmpty() || fechaInicio == null || fechaFin == null || ubicacion.isEmpty() || descripcion.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben estar completos.");
            return;
        }
        if (fechaFin.isBefore(fechaInicio)) {
            mostrarAlerta("Error", "La fecha fin no puede ser anterior a la fecha inicio.");
            return;
        }

        Ferias feria = new Ferias(nombre, fechaInicio, fechaFin, ubicacion, descripcion);
        MantenimientoFerias.insertar(conexion, feria);

        limpiarCampos();
        tablaEstudiantes.setItems(MantenimientoFerias.consultar(conexion));
    }

    @FXML
    public void onEditarButtonClick() {
        Ferias seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            idSeleccionado = MantenimientoFerias.obtenerId(seleccionado);

            nombreTextField.setText(seleccionado.getNombre());
            fechaIncioDatePicker.setValue(seleccionado.getFehcaInicio());
            fechaFinDatePicker.setValue(seleccionado.getFechaFin());
            ubicacionTextField.setText(seleccionado.getUbicacion());
            DescripciónTextField.setText(seleccionado.getDescripcion());

            anyadirButton.setDisable(true);
            guardarButton.setDisable(false);
        }
    }

    @FXML
    public void onGuardarButtonClick() {
        String nombre = nombreTextField.getText().trim();
        LocalDate fechaInicio = fechaIncioDatePicker.getValue();
        LocalDate fechaFin = fechaFinDatePicker.getValue();
        String ubicacion = ubicacionTextField.getText().trim();
        String descripcion = DescripciónTextField.getText().trim();

        if (nombre.isEmpty() || fechaInicio == null || fechaFin == null || ubicacion.isEmpty() || descripcion.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben estar completos.");
            return;
        }
        if (fechaFin.isBefore(fechaInicio)) {
            mostrarAlerta("Error", "La fecha fin no puede ser anterior a la fecha inicio.");
            return;
        }

        Ferias feria = new Ferias(nombre, fechaInicio, fechaFin, ubicacion, descripcion);
        MantenimientoFerias.modificar(conexion, feria, idSeleccionado);

        limpiarCampos();
        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);
        tablaEstudiantes.setItems(MantenimientoFerias.consultar(conexion));
    }

    @FXML
    public void onEliminarButtonClick() {
        Ferias seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            MantenimientoFerias.borrar(conexion, seleccionado);
            tablaEstudiantes.setItems(MantenimientoFerias.consultar(conexion));
        }
    }

    @FXML
    public void buttonInicio() throws IOException {
        HelloApplication.setRoot("hello-view");
    }

    private void limpiarCampos() {
        nombreTextField.clear();
        fechaIncioDatePicker.setValue(null);
        fechaFinDatePicker.setValue(null);
        ubicacionTextField.clear();
        DescripciónTextField.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}



