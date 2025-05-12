package org.example.proyectointermodular;

import com.google.errorprone.annotations.FormatMethod;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import org.example.proyectointermodular.Objetos.Artistas;

import java.io.IOException;
import java.time.LocalDate;

public class PantallaArtistas {

    @FXML
    private TableColumn<Artistas, String> idTable;

    @FXML
    private TableColumn<Artistas,String> nombreTable;

    @FXML
    private TableColumn<Artistas, String > biografiaTable;

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
    private Button editarBotton;

    @FXML
    private Button eliminarBotton;

    @FXML
    private Button guardarButton;

    @FXML
    private Button anyadirButton;


    public void onEliminarButtonClick(ActionEvent actionEvent) {
    }

    public void onEditarButtonClick(ActionEvent actionEvent) {
    }

    public void onAnyadirButtonClick(ActionEvent actionEvent) {

        String nombre = nombreTextField.getText();
        String biografia = biografiaTextField.getText();
        String telefono = telefonoTextField.getText();
        String email = emailTextField.getText();

        Artistas artista = new Artistas(nombre,biografia,telefono,email);

        Mantenimiento.insertar(conexion,estudiante);

        nombreTextField.clear();
        biografiaTextField.clear();
        telefonoTextField.clear();
        tablaArtistas.setItems(Mantenimiento.consultar(conexion));


    }

    public void onGuardarButtonClick(ActionEvent actionEvent) {



    }

    public void buttonInicio(ActionEvent actionEvent) throws IOException {

        HelloApplication.setRoot("hello-view");

    }
}
