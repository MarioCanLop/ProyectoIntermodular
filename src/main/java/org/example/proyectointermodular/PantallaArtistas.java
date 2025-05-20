package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.Conexiones;
import org.example.proyectointermodular.Matenimiento.MantenimientoArtistas;
import org.example.proyectointermodular.Objetos.Artistas;

import java.io.IOException;
import java.sql.Connection;


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
        conexion = Conexiones.conectar();

        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));

        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getArtistaid()));
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

        Artistas artista;

        if (biografia == null) {
            artista = new Artistas(nombre, email, telefono);
        }else {
            artista = new Artistas(nombre, biografia, email, telefono);
        }

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

        Artistas artista;

        if (biografia == null) {
            artista = new Artistas(nombre, email, telefono);
        }else {
            artista = new Artistas(nombre, biografia, email, telefono);
        }

        MantenimientoArtistas.modificar(conexion, artista, idAnterior);

        limpiarCampos();
        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));
    }

    @FXML
    protected void onEliminarButtonClick() {
        Artistas artista = tablaArtistas.getSelectionModel().getSelectedItem();

        if (artista != null) {
            int id_borrar = artista.getArtistaid();
            MantenimientoArtistas.borrar(conexion, id_borrar);
        } else {
            System.out.println("No hay ningún artista seleccionado.");
        }

        tablaArtistas.setItems(MantenimientoArtistas.consultar(conexion));
    }

    @FXML
    protected void onEditarButtonClick() {

        Artistas seleccionado = tablaArtistas.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            anyadirButton.setDisable(true);
            guardarButton.setDisable(false);
            idAnterior = seleccionado.getArtistaid();

            nombreTextField.setText(seleccionado.getNombre());
            telefonoTextField.setText(seleccionado.getTelefono());
            emailTextField.setText(seleccionado.getEmail());
        } else {
            System.out.println("No hay ningún artista seleccionado.");
        }
    }



    private void limpiarCampos() {
        nombreTextField.clear();
        biografiaTextField.clear();
        telefonoTextField.clear();
        emailTextField.clear();
    }

    @FXML
    protected void buttonInicio() throws IOException {
        HelloApplication.setRoot("hello-view");
        System.out.println("Volviendo al inicio...");
    }
}
