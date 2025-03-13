/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1_ipc2_2025.resources.controller;

import com.mycompany.proyecto1_ipc2_2025.resources.conexiondb.ConexionDB;
import com.mycompany.proyecto1_ipc2_2025.resources.encriptacion.EncriptarMD5;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author brandon
 */
@WebServlet(name = "EnsamblePiezaAdmin", urlPatterns = {"/EnsamblePiezaAdmin"})
public class EnsamblePiezaAdmin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nombreComputadora = request.getParameter("tipoComputadora");
        String cantidad = request.getParameter("cantidad");
        String tipoPieza = request.getParameter("tipoPieza");
        System.out.println("Nombre computadora: " + nombreComputadora);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Tipo pieza: " + tipoPieza);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        String nombreComputadora = request.getParameter("tipoComputadora");
        String cantidad = request.getParameter("cantidad");
        String tipoPieza = request.getParameter("tipoPieza");
        String nombreUsuario = (String) sesion.getAttribute("user");
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Nombre computadora: " + nombreComputadora);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Tipo pieza: " + tipoPieza);

        ConexionDB conexionDB = new ConexionDB();
        Statement statement = null;
        try {
            statement = conexionDB.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO Ensamble (tipo_pieza, cantidad, tipo_computadora_fk1) VALUES('" + tipoPieza + "', '" + cantidad + "', '" + nombreComputadora + "');");
            statement.executeUpdate("INSERT INTO Ensamblaje (fecha_ensamblaje, nombre_usuario_fk, tipo_computadora_fk) VALUES(NOW(), '" + nombreUsuario + "', '" + nombreComputadora + "');");
//                request.getRequestDispatcher("index.jsp").forward(request, response);
            statement.close();
            response.sendRedirect("Vista/ensamblarComputadora.jsp");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
//"INSERT INTO Ensamblaje (fecha_ensamblaje, nombre_usuario_fk, tipo_computadora_fk) VALUES('" + fecha + "', '" + nombreUsuario + "', '" + nombreComputadora + "');"