/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1_ipc2_2025.resources.controller;

import com.mycompany.proyecto1_ipc2_2025.resources.conexiondb.ConexionDB;
import com.mycompany.proyecto1_ipc2_2025.resources.encriptacion.EncriptarMD5;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brandon
 */
@WebServlet(name = "RegistrarUsuario", urlPatterns = {"/RegistrarUsuario"})
public class RegistrarUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /*try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrarUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
        ConexionDB conexionDB = new ConexionDB();
        Statement statement = null;
        EncriptarMD5 encriptar = new EncriptarMD5();
        
        if (request.getParameter("enviar") != null) {
                String nombre = request.getParameter("user");
                String password = request.getParameter("password1");
                String rol = request.getParameter("rol");
                try {
                    
                    statement = conexionDB.getConnection().createStatement();
                    statement.executeUpdate("INSERT INTO Usuario (nombre_usuario, password, tipo_rol_fk) VALUES('" + nombre + "', '" + encriptar.getMD5(password) + "', '" + rol + "');");
                    switch (rol) {
                            case "1":
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                                break;
                            case "2":
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                                break;
                            case "3":
                                request.getRequestDispatcher("Vista/panelAdministracion.jsp").forward(request, response);
                                break;
                            default:
                                throw new AssertionError();
                        }
                } catch (Exception e) {
                    System.out.println(e);
                }
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
