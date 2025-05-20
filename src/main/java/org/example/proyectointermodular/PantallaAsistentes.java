package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.MantenimientoAsistentes;
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
        conexion = MantenimientoAsistentes.conectar(conexion);

        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        telefonoTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTelefono()));
        emailTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmail()));

        tablaEstudiantes.setItems(MantenimientoAsistentes.consultar(conexion));
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
