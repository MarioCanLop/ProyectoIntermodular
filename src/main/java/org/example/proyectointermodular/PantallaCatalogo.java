

package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.MantenimientoCatalogo;
import org.example.proyectointermodular.Objetos.Catalogo;

import java.io.IOException;
import java.sql.Connection;

public class PantallaCatalogo {

    @FXML
    private TableView<Catalogo> tablaEstudiantes;
    @FXML
    private TableColumn<Catalogo, Integer> idTable;
    @FXML
    private TableColumn<Catalogo, String> nombreTable;
    @FXML
    private TableColumn<Catalogo, String> descripcionTable;

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField descripcionTextField;

    @FXML
    private Button anyadirButton;
    @FXML
    private Button guardarButton;

    private Connection conexion;


    @FXML
    public void initialize() {
        conexion = MantenimientoCatalogo.conectar(conexion);

        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        descripcionTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDescripcion()));

        tablaEstudiantes.setItems(MantenimientoCatalogo.consultar(conexion));
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





