package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.MantenimientoObras;
import org.example.proyectointermodular.Matenimiento.MantenimientoObras;
import org.example.proyectointermodular.Objetos.ObrasDeArte;

import java.io.IOException;

public class PantallaObrasDeArte {

    @FXML
    private TableView<ObrasDeArte> tablaEstudiantes;

    @FXML
    private TableColumn<ObrasDeArte, Integer> idTable;
    @FXML
    private TableColumn<ObrasDeArte, String> tituloTable;
    @FXML
    private TableColumn<ObrasDeArte, String> descripcionTable;
    @FXML
    private TableColumn<ObrasDeArte, String> disponibleTable;
    @FXML
    private TableColumn<ObrasDeArte, Integer> precioTable;

    @FXML
    private TextField TituloTextField;
    @FXML
    private TextField descripcionTextField;
    @FXML
    private TextField DisponibleTextField;
    @FXML
    private TextField precioTextField;

    @FXML
    private Button anyadirButton;
    @FXML
    private Button guardarButton;

    private int idSeleccionado = -1;

    @FXML
    public void initialize() {
        // Asume que tienes una clase MantenimientoObras con métodos para CRUD

        // Configurar columnas
        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(MantenimientoObras.obtenerId(data.getValue())));
        tituloTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTitulo()));
        descripcionTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDescripcion()));
        disponibleTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDisponible()));
        precioTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrecio()));

        // Cargar datos en la tabla
        tablaEstudiantes.setItems(MantenimientoObras.consultar());
    }

    @FXML
    public void onAnyadirButtonClick() {
        String titulo = TituloTextField.getText().trim();
        String descripcion = descripcionTextField.getText().trim();
        String disponible = DisponibleTextField.getText().trim();
        String precioStr = precioTextField.getText().trim();

        if (titulo.isEmpty() || descripcion.isEmpty() || disponible.isEmpty() || precioStr.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben estar completos.");
            return;
        }

        int precio;
        try {
            precio = Integer.parseInt(precioStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio debe ser un número entero.");
            return;
        }

        ObrasDeArte obra = new ObrasDeArte(titulo, descripcion, disponible, precio);
        MantenimientoObras.insertar(obra);

        limpiarCampos();
        tablaEstudiantes.setItems(MantenimientoObras.consultar());
    }

    @FXML
    public void onEditarButtonClick() {
        ObrasDeArte seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            idSeleccionado = MantenimientoObras.obtenerId(seleccionado);

            TituloTextField.setText(seleccionado.getTitulo());
            descripcionTextField.setText(seleccionado.getDescripcion());
            DisponibleTextField.setText(seleccionado.getDisponible());
            precioTextField.setText(String.valueOf(seleccionado.getPrecio()));

            anyadirButton.setDisable(true);
            guardarButton.setDisable(false);
        }
    }

    @FXML
    public void onGuardarButtonClick() {
        String titulo = TituloTextField.getText().trim();
        String descripcion = descripcionTextField.getText().trim();
        String disponible = DisponibleTextField.getText().trim();
        String precioStr = precioTextField.getText().trim();

        if (titulo.isEmpty() || descripcion.isEmpty() || disponible.isEmpty() || precioStr.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben estar completos.");
            return;
        }

        int precio;
        try {
            precio = Integer.parseInt(precioStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio debe ser un número entero.");
            return;
        }

        ObrasDeArte obra = new ObrasDeArte(titulo, descripcion, disponible, precio);
        MantenimientoObras.modificar(obra, idSeleccionado);

        limpiarCampos();
        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);
        tablaEstudiantes.setItems(MantenimientoObras.consultar());
    }

    @FXML
    public void onEliminarButtonClick() {
        ObrasDeArte seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            MantenimientoObras.borrar(seleccionado);
            tablaEstudiantes.setItems(MantenimientoObras.consultar());
        }
    }

    @FXML
    public void buttonInicio() throws IOException {
        HelloApplication.setRoot("hello-view");    }

    private void limpiarCampos() {
        TituloTextField.clear();
        descripcionTextField.clear();
        DisponibleTextField.clear();
        precioTextField.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

