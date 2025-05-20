package org.example.proyectointermodular;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodular.Matenimiento.MantenimientoAsistentes;
import org.example.proyectointermodular.Matenimiento.MantenimientoObras;
import org.example.proyectointermodular.Matenimiento.MantenimientoObras;
import org.example.proyectointermodular.Objetos.ObrasDeArte;

import java.io.IOException;
import java.sql.Connection;

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

    private String nomSelect;
    private Connection conexion;

    @FXML
    public void initialize() {
        conexion = MantenimientoAsistentes.conectar(conexion);


        tituloTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getTitulo()));
        descripcionTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDescripcion()));
        disponibleTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getDisponible()));
        precioTable.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrecio()));

        // Cargar datos en la tabla
        tablaEstudiantes.setItems(MantenimientoObras.consultar(conexion));
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