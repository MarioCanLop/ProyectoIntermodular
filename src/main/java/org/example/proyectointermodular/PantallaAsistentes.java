package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Objetos.Asistentes;

import java.io.IOException;
import java.sql.Connection;

public class PantallaAsistentes {

    @FXML
    private TableView<Asistentes> tablaEstudiantes;
    @FXML
    private TableColumn<Asistentes, Integer> idTable;
    @FXML
    private TableColumn<Asistentes, String> nombreTable;
    @FXML
    private TableColumn<Asistentes, String> telefonoTable;
    @FXML
    private TableColumn<Asistentes, String> emailTable;

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField telefonoTextField;
    @FXML
    private TextField emailTextField;

    @FXML
    private Button anyadirButton;
    @FXML
    private Button editarBotton;
    @FXML
    private Button eliminarBotton;
    @FXML
    private Button guardarButton;

    private Connection conexion;
    private int idSeleccionado;

    @FXML
    public void initialize() {
        conexion = MantenimientoAsistentes.conectar();

        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(MantenimientoAsistentes.obtenerId(data.getValue())));
        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        telefonoTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTelefono()));
        emailTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmail()));

        tablaEstudiantes.setItems(MantenimientoAsistentes.consultar(conexion));
    }

    @FXML
    public void onAnyadirButtonClick() {
        String nombre = nombreTextField.getText();
        String telefono = telefonoTextField.getText();
        String email = emailTextField.getText();

        Asistentes asistente = new Asistentes(nombre, telefono, email);
        MantenimientoAsistentes.insertar(conexion, asistente);

        limpiarCampos();
        tablaEstudiantes.setItems(MantenimientoAsistentes.consultar(conexion));
    }

    @FXML
    public void onEditarButtonClick() {
        Asistentes seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            anyadirButton.setDisable(true);
            guardarButton.setDisable(false);

            idSeleccionado = MantenimientoAsistentes.obtenerId(seleccionado);
            nombreTextField.setText(seleccionado.getNombre());
            telefonoTextField.setText(seleccionado.getTelefono());
            emailTextField.setText(seleccionado.getEmail());
        } else {
            System.out.println("No hay asistente seleccionado.");
        }
    }

    @FXML
    public void onGuardarButtonClick() {
        String nombre = nombreTextField.getText();
        String telefono = telefonoTextField.getText();
        String email = emailTextField.getText();

        Asistentes asistente = new Asistentes(nombre, telefono, email);
        MantenimientoAsistentes.modificar(conexion, asistente, idSeleccionado);

        limpiarCampos();
        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);
        tablaEstudiantes.setItems(MantenimientoAsistentes.consultar(conexion));
    }

    @FXML
    public void onEliminarButtonClick() {
        Asistentes seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            MantenimientoAsistentes.borrar(conexion, seleccionado);
            tablaEstudiantes.setItems(MantenimientoAsistentes.consultar(conexion));
        } else {
            System.out.println("No hay asistente seleccionado.");
        }
    }

    private void limpiarCampos() {
        nombreTextField.clear();
        telefonoTextField.clear();
        emailTextField.clear();
    }

    @FXML
    public void buttonInicio() throws IOException {

        HelloApplication.setRoot("hello-view");

    }
}
