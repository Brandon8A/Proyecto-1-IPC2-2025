/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_ipc2_2025.resources.database;

import com.mycompany.proyecto1_ipc2_2025.resources.conexiondb.ConexionDB;
import com.mycompany.proyecto1_ipc2_2025.resources.encriptacion.EncriptarMD5;
import java.sql.*;

/**
 *
 * @author brandon
 */
public class InsertarDatosDB {

    String contenido;
    ConexionDB conexion;
    Statement statement = null;
    EncriptarMD5 encriptar;

    public InsertarDatosDB(String contenido) {
        this.contenido = contenido;
        this.conexion = new ConexionDB();
        this.encriptar = new EncriptarMD5();
    }

    public void almacenarInstruccion() {
        String[] instrucciones;
        String[] datos;
        instrucciones = contenido.split("\n");
//        for (int i = 0; i < instrucciones.length; i++) {
//            datos = instrucciones[i].split("\\(");
//            System.out.println(datos[i]);
//        }
    }
}
