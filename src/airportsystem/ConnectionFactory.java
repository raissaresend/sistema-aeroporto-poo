/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airportsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author RAISSA
 */
public class ConnectionFactory {

    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "America/Sao_Paulo");
            properties.setProperty("allowPublicKeyRetrieval", "true");

            String con = "jdbc:mysql://localhost/aeroporto";
            return DriverManager.getConnection(con, properties);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection conn = factory.getConnection()) {
            System.out.println("✓ Conectado ao banco de dados com sucesso!");
        } catch (Exception e) {
            System.err.println("✗ Erro ao conectar: " + e.getMessage());
        }
    }
}
