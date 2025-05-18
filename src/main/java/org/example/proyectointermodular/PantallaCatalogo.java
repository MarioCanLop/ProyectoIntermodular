

package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Objetos.Catalogo;

import java.awt.event.ActionEvent;
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
    private int idSeleccionado;

    @FXML
    public void initialize() {
        conexion = MantenimientoCatalogo.conectar();

        idTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(MantenimientoCatalogo.obtenerId(data.getValue())));
        nombreTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getNombre()));
        descripcionTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDescripcion()));

        tablaEstudiantes.setItems(MantenimientoCatalogo.consultar(conexion));
    }

    @FXML
    public void onAnyadirButtonClick() {
        String nombre = nombreTextField.getText();
        String descripcion = descripcionTextField.getText();

        Catalogo catalogo = new Catalogo(nombre, descripcion);
        MantenimientoCatalogo.insertar(conexion, catalogo);

        limpiarCampos();
        tablaEstudiantes.setItems(MantenimientoCatalogo.consultar(conexion));
    }

    @FXML
    public void onEditarButtonClick() {
        Catalogo seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            idSeleccionado = MantenimientoCatalogo.obtenerId(seleccionado);
            nombreTextField.setText(seleccionado.getNombre());
            descripcionTextField.setText(seleccionado.getDescripcion());

            anyadirButton.setDisable(true);
            guardarButton.setDisable(false);
        }
    }

    @FXML
    public void onGuardarButtonClick() {
        String nombre = nombreTextField.getText();
        String descripcion = descripcionTextField.getText();

        Catalogo catalogo = new Catalogo(nombre, descripcion);
        MantenimientoCatalogo.modificar(conexion, catalogo, idSeleccionado);

        limpiarCampos();
        anyadirButton.setDisable(false);
        guardarButton.setDisable(true);
        tablaEstudiantes.setItems(MantenimientoCatalogo.consultar(conexion));
    }

    @FXML
    public void onEliminarButtonClick() {
        Catalogo seleccionado = tablaEstudiantes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            MantenimientoCatalogo.borrar(conexion, seleccionado);
            tablaEstudiantes.setItems(MantenimientoCatalogo.consultar(conexion));
        }
    }

    @FXML
    public void buttonInicio(ActionEvent actionEvent) throws IOException {

        HelloApplication.setRoot("hello-view");

    }

    private void limpiarCampos() {
        nombreTextField.clear();
        descripcionTextField.clear();
    }
}





