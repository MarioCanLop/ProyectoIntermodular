package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        conexion = MantenimientoEstantes.conectar();

        // Configurar columnas de la tabla
        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(MantenimientoEstantes.obtenerId(data.getValue())));
        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));

        // Cargar datos iniciales
        tablaEstudiantes.setItems(MantenimientoEstantes.consultar(conexion));
    }

    @FXML
    public void onAnyadirButtonClick() {
        String nombre = nombreTextField.getText().trim();
        if (nombre.isEmpty()) {
            mostrarAlerta("Error", "El nombre no puede estar vacío.");
            return;
        }

        Estantes estante = new Estantes(nombre);
        MantenimientoEstantes.insertar(conexion, estante);

        limpiarCampos();
        tablaEstudiantes.setItems(MantenimientoEstantes.consultar(conexion));
    }

    @FXML
    public void onEditarButtonClick() {
        Estantes seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            idSeleccionado = MantenimientoEstantes.obtenerId(seleccionado);
            nombreTextField.setText(seleccionado.getNombre());

            anyadirButton.setDisable(true);
            guardarButton.setDisable(false);
        }
    }

    @FXML
    public void onGuardarButtonClick() {
        String nombre = nombreTextField.getText().trim();
        if (nombre.isEmpty()) {
            mostrarAlerta("Error", "El nombre no puede estar vacío.");
            return;
        }

        Estantes estante = new Estantes(nombre);
        MantenimientoEstantes.modificar(conexion, estante, idSeleccionado);

        limpiarCampos();
        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);
        tablaEstudiantes.setItems(MantenimientoEstantes.consultar(conexion));
    }

    @FXML
    public void onEliminarButtonClick() {
        Estantes seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            MantenimientoEstantes.borrar(conexion, seleccionado);
            tablaEstudiantes.setItems(MantenimientoEstantes.consultar(conexion));
        }
    }

    @FXML
    public void buttonInicio() throws IOException {
        HelloApplication.setRoot("hello-view");
    }

    private void limpiarCampos() {
        nombreTextField.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}





