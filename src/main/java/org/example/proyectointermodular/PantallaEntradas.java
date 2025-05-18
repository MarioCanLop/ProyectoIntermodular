package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private int idSeleccionado;

    @FXML
    public void initialize() {
        conexion = MantenimientoEntradas.conectar();

        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(MantenimientoEntradas.obtenerId(data.getValue())));
        fechaTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFecha()));
        precioTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrecio()));

        tablaEstudiantes.setItems(MantenimientoEntradas.consultar(conexion));
    }

    @FXML
    public void onAnyadirButtonClick() {
        LocalDate fecha = fechaDatePicker.getValue();
        int precio;

        try {
            precio = Integer.parseInt(PrecioTextField.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Precio inválido", "Introduce un número válido para el precio.");
            return;
        }

        Entradas entrada = new Entradas(fecha, precio);
        MantenimientoEntradas.insertar(conexion, entrada);

        limpiarCampos();
        tablaEstudiantes.setItems(MantenimientoEntradas.consultar(conexion));
    }

    @FXML
    public void onEditarButtonClick() {
        Entradas seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            idSeleccionado = MantenimientoEntradas.obtenerId(seleccionado);
            fechaDatePicker.setValue(seleccionado.getFecha());
            PrecioTextField.setText(String.valueOf(seleccionado.getPrecio()));

            anyadirButton.setDisable(true);
            guardarButton.setDisable(false);
        }
    }

    @FXML
    public void onGuardarButtonClick() {
        LocalDate fecha = fechaDatePicker.getValue();
        int precio;

        try {
            precio = Integer.parseInt(PrecioTextField.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Precio inválido", "Introduce un número válido para el precio.");
            return;
        }

        Entradas entrada = new Entradas(fecha, precio);
        MantenimientoEntradas.modificar(conexion, entrada, idSeleccionado);

        limpiarCampos();
        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);
        tablaEstudiantes.setItems(MantenimientoEntradas.consultar(conexion));
    }

    @FXML
    public void onEliminarButtonClick() {
        Entradas seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            MantenimientoEntradas.borrar(conexion, seleccionado);
            tablaEstudiantes.setItems(MantenimientoEntradas.consultar(conexion));
        }
    }

    @FXML
    public void buttonInicio() throws IOException {

        HelloApplication.setRoot("hello-view");

    }


    private void limpiarCampos() {
        fechaDatePicker.setValue(null);
        PrecioTextField.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}






