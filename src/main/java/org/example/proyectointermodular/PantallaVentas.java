package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.MantenimientoVentas;
import org.example.proyectointermodular.Objetos.Ventas;

import java.io.IOException;
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

    private int idSeleccionado = -1;

    @FXML
    public void initialize() {
        // Configurar las columnas, aquí se asume que hay una clase MantenimientoVentas para CRUD
        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(MantenimientoVentas.obtenerId(data.getValue())));
        precioTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrecio()));
        fechaTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getFecha()));

        // Cargar datos a la tabla
        tablaEstudiantes.setItems(MantenimientoVentas.consultar());
    }

    @FXML
    public void onAnyadirButtonClick() {
        String precioStr = precioTextField.getText().trim();
        LocalDate fecha = fechaDatePicker.getValue();

        if (precioStr.isEmpty() || fecha == null) {
            mostrarAlerta("Error", "Debe introducir precio y fecha.");
            return;
        }

        int precio;
        try {
            precio = Integer.parseInt(precioStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio debe ser un número entero.");
            return;
        }

        Ventas venta = new Ventas(precio, fecha);
        MantenimientoVentas.insertar(venta);

        limpiarCampos();
        tablaEstudiantes.setItems(MantenimientoVentas.consultar());
    }

    @FXML
    public void onEditarButtonClick() {
        Ventas seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            idSeleccionado = MantenimientoVentas.obtenerId(seleccionado);

            precioTextField.setText(String.valueOf(seleccionado.getPrecio()));
            fechaDatePicker.setValue(seleccionado.getFecha());

            anyadirButton.setDisable(true);
            guardarButton.setDisable(false);
        }
    }

    @FXML
    public void onGuardarButtonClick() {
        String precioStr = precioTextField.getText().trim();
        LocalDate fecha = fechaDatePicker.getValue();

        if (precioStr.isEmpty() || fecha == null) {
            mostrarAlerta("Error", "Debe introducir precio y fecha.");
            return;
        }

        int precio;
        try {
            precio = Integer.parseInt(precioStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio debe ser un número entero.");
            return;
        }

        Ventas venta = new Ventas(precio, fecha);
        MantenimientoVentas.modificar(venta, idSeleccionado);

        limpiarCampos();
        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);
        tablaEstudiantes.setItems(MantenimientoVentas.consultar());
    }

    @FXML
    public void onEliminarButtonClick() {
        Ventas seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            MantenimientoVentas.borrar(seleccionado);
            tablaEstudiantes.setItems(MantenimientoVentas.consultar());
        }
    }

    @FXML
    public void buttonInicio() throws IOException {
        HelloApplication.setRoot("hello-view");    }

    private void limpiarCampos() {
        precioTextField.clear();
        fechaDatePicker.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}







