/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_ipc2_2025.resources.conexiondb;

import java.sql.*;

/**
 *
 * @author brandon
 */
public class ConexionDB {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/Tech_Solutions_Hub";
    private static final String USER = "root";
    private static final String PASSWORD = "brandon031200";
    
    private Connection connection;
    private Statement statement;
    
    public ConexionDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Ingresando a base de datos correctamente...");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la Base de Datos.");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
    
    
}
