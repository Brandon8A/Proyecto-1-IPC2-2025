/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_ipc2_2025.resources.database;

import com.mycompany.proyecto1_ipc2_2025.resources.conexiondb.ConexionDB;
import com.mycompany.proyecto1_ipc2_2025.resources.encriptacion.EncriptarMD5;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brandon
 */
public class InsertarDatosDB {

    List<String> instruccionesInvalidas = new ArrayList<>();

    String contenido;
    ConexionDB conexion;
    EncriptarMD5 encriptar;

    public InsertarDatosDB(ConexionDB conexion) {
        this.conexion = conexion;
        this.encriptar = new EncriptarMD5();
    }

    //Metodo que identifica las instrucciones 
    public void identificarInstrucciones(String instrucciones) {
        String[] instruccion = instrucciones.split("\\(");
        String nombreInstruccion = instruccion[0];
        String[] parametros = instruccion[1].split(",");
        System.out.println("Instrucción: " + nombreInstruccion);
        if (nombreInstruccion.equals("USUARIO")) {
            obtenerParametrosUsuario(parametros);
        } else if (nombreInstruccion.equals("PIEZA")) {
            obtenerParametrosPieza(parametros);
        } else if (nombreInstruccion.equals("COMPUTADORA")) {
            obtenerParametrosComputadora(parametros);
        } else if (nombreInstruccion.equals("ENSAMBLE_PIEZAS")) {
            obtenerParametrosEnsamblePiezas(parametros);
        } else if (nombreInstruccion.equals("ENSAMBLAR_COMPUTADORA")) {
            obtenerParametrosEnsamblarComputadora(parametros);
        } else {
            instruccionesInvalidas.add(instrucciones);
        }
    }

    //Metodo que obtiene los parametros de una la instruccion USUARIO
    private void obtenerParametrosUsuario(String[] parametros) {
        String nombreUsuario = parametros[0];
        nombreUsuario = limpiarCadena(nombreUsuario);
        String password = parametros[1];
        password = limpiarCadena(password);
        String tipoUsuario = parametros[2];
        tipoUsuario = limpiarParentesisCierre(tipoUsuario);
        try {
            conexion.getStatement().executeUpdate("INSERT INTO Usuario (nombre_usuario, password, tipo_rol_fk) VALUES('" + nombreUsuario + "', '" + encriptar.getMD5(password) + "', '" + tipoUsuario + "');");
        } catch (Exception e) {
        }
    }

    //Metodo que obtiene los parametros de una la instruccion PIEZA
    private void obtenerParametrosPieza(String[] parametros) {
        String nombrePieza = parametros[0];
        nombrePieza = limpiarCadena(nombrePieza);
        String precio = parametros[1];
        precio = limpiarParentesisCierre(precio);
        try {
            conexion.getStatement().executeUpdate("INSERT INTO Pieza (nombre_pieza, precio) VALUES('" + nombrePieza + "', '" + precio + "');");
        } catch (Exception e) {
        }
    }

    //Metodo que obtiene los parametros de una la instruccion COMPUTADORA
    private void obtenerParametrosComputadora(String[] parametros) {
        String nombreComputadora = parametros[0];
        nombreComputadora = limpiarCadena(nombreComputadora);
        String precio = parametros[1];
        precio = limpiarParentesisCierre(precio);
        try {
            conexion.getStatement().executeUpdate("INSERT INTO Tipo_Computadora (nombre_computadora, precio) VALUES('" + nombreComputadora + "', '" + precio + "');");
        } catch (Exception e) {
        }
    }

    //Metodo que obtiene los parametros de una la instruccion ENSAMBLE_PIEZAS
    private void obtenerParametrosEnsamblePiezas(String[] parametros) {
        String nombreComputadora = parametros[0];
        nombreComputadora = limpiarCadena(nombreComputadora);
        String nombrePieza = parametros[1];
        nombrePieza = limpiarCadena(nombrePieza);
        String cantidad = parametros[2];
        cantidad = limpiarParentesisCierre(cantidad);
        try {
            conexion.getStatement().executeUpdate("INSERT INTO Ensamble (tipo_pieza, cantidad, tipo_computadora_fk1) VALUES('" + nombrePieza + "', '" + cantidad + "', '" + nombreComputadora + "');");
        } catch (Exception e) {
        }
    }

    //Metodo que obtiene los parametros de una la instruccion ENSAMBLAR_COMPUTADORA
    private void obtenerParametrosEnsamblarComputadora(String[] parametros) {
        String nombreComputadora = parametros[0];
        nombreComputadora = limpiarCadena(nombreComputadora);
        String nombreUsuario = parametros[1];
        nombreUsuario = limpiarCadena(nombreUsuario);
        String fecha = parametros[2];
        fecha = limpiarParentesisCierre(fecha);
        fecha = limpiarCadena(fecha);
        fecha = cambiarFormatoFecha(fecha);
        try {
            conexion.getStatement().executeUpdate("INSERT INTO Ensamblaje (fecha_ensamblaje, nombre_usuario_fk, tipo_computadora_fk) VALUES('" + fecha + "', '" + nombreUsuario + "', '" + nombreComputadora + "');");
        } catch (Exception e) {
        }
    }

    //Metodo que elimina comillas y espacios al inicio y final de una cadena
    private String limpiarCadena(String cadena) {
        cadena = cadena.replace("\"", "");
        cadena = limpiarEspacios(cadena);
        return cadena;
    }

    //Metodo que elimina parentesis de cierre
    private String limpiarParentesisCierre(String cadena) {
        cadena = cadena.replace(")", "");
        cadena = limpiarEspacios(cadena);
        return cadena;
    }

    //Metodo que elimina espacios al inicio y final de una cadena
    private String limpiarEspacios(String cadena) {
        cadena = cadena.trim();
        return cadena;
    }

    //Metodo que valida que una cadena no exceda 150 caracteres
    private boolean validarLongitudCadena(String cadena) {
        if (cadena.length() > 150) {
            return false;
        }
        return true;
    }
    
    //Metodo que cambie la fecha de formato dd/mm/yyyy a yyyy-mm-dd
    public String cambiarFormatoFecha(String fecha){
        String[] fechaSeparada = fecha.split("/");
        String nuevaFecha = fechaSeparada[2] + "-" + fechaSeparada[1] + "-" + fechaSeparada[0];
        return nuevaFecha;
    }
}
