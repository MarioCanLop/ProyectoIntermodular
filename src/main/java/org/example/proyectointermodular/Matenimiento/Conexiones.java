package org.example.proyectointermodular.Matenimiento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexiones {

    public static Connection conectar(){
        Connection conexion;
        String host = "jdbc:mariadb://localhost:3306/";
        String user = "root";
        String pass = "";
        String bd = "proyecto";

        try {
            conexion = DriverManager.getConnection(host+bd, user, pass);
            System.out.println("Conexión realizada con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return conexion;

    }

    public static void desconectar(Connection conexion){
        try {
            conexion.close();
            System.out.println("Conexión finalizada con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
