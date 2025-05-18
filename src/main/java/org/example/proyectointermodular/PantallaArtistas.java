package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Objetos.Artistas;

import java.io.IOException;


public class PantallaArtistas {

    @FXML
    private TableView<Artistas> tablaArtistas;

    @FXML
    private TableColumn<Artistas, Integer> idTable;

    @FXML
    private TableColumn<Artistas, String> nombreTable;

    @FXML
    private TableColumn<Artistas, String> biografiaTable;

    @FXML
    private TableColumn<Artistas, String> telefonoTable;

    @FXML
    private TableColumn<Artistas, String> emailTable;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField biografiaTextField;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button anyadirButton;

    @FXML
    private Button guardarButton;

    @FXML
    private Button editarBotton;

    @FXML
    private Button eliminarBotton;

    @FXML
    private Button buttonInicio;

    @FXML
    private Label welcomeText;

    private Connection conexion;
    private int idAnterior;

    @FXML
    public void initialize() {
        conexion = org.example.practica2_javafx.Mantenimiento.conectar();

        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));

        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getId()));
        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        biografiaTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getBiografia()));
        telefonoTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTelefono()));
        emailTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getEmail()));
    }

    @FXML
    protected void onAnyadirButtonClick() {
        String nombre = nombreTextField.getText();
        String biografia = biografiaTextField.getText();
        String telefono = telefonoTextField.getText();
        String email = emailTextField.getText();

        Artistas artista = new Artistas(nombre, biografia, telefono, email);

        MantenimientoArtistas.insertar(conexion, artista);

        limpiarCampos();
        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));
    }

    @FXML
    protected void onGuardarButtonClick() {
        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);

        String nombre = nombreTextField.getText();
        String biografia = biografiaTextField.getText();
        String telefono = telefonoTextField.getText();
        String email = emailTextField.getText();

        Artistas artista = new Artistas(nombre, biografia, telefono, email);

        MantenimientoArtistas.modificar(conexion, artista, idAnterior);

        limpiarCampos();
        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));
    }

    @FXML
    protected void onEliminarButtonClick() {
        Artistas seleccionado = tablaArtistas.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            MantenimientoArtistas.borrar(conexion, seleccionado);
            tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));
        } else {
            System.out.println("No hay ningún artista seleccionado.");
        }
    }

    @FXML
    protected void onEditarButtonClick() {
        Artistas seleccionado = tablaArtistas.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            anyadirButton.setDisable(true);
            guardarButton.setDisable(false);
            idAnterior = seleccionado.getId();

            nombreTextField.setText(seleccionado.getNombre());
            biografiaTextField.setText(seleccionado.getBiografia());
            telefonoTextField.setText(seleccionado.getTelefono());
            emailTextField.setText(seleccionado.getEmail());
        } else {
            System.out.println("No hay ningún artista seleccionado.");
        }
    }

    @FXML
    protected void buttonInicio() throws IOException {
        HelloApplication.setRoot("hello-view");
        System.out.println("Volviendo al inicio...");
    }

    private void limpiarCampos() {
        nombreTextField.clear();
        biografiaTextField.clear();
        telefonoTextField.clear();
        emailTextField.clear();
    }
}
