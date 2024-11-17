package main.java.sigep.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexion {

    private static Connection connection;

    // Método para conectarse a la base de datos MySQL
    public static Connection conectar() {
        String url = "jdbc:mysql://localhost:3306/etiquetas"; // URL de conexión
        String user = "root"; // Usuario de la base de datos
        String password = "tijeras22"; // Contraseña de la base de datos

        try {
            // Registrar el controlador de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos.");
            return connection;
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: No se encontró el driver de MySQL.");
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la base de datos: " + ex.getMessage());
            
        }
        return null;
    }

    // Método para desconectar la base de datos
    public static void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexión: " + ex.getMessage());
        }
    }
}
