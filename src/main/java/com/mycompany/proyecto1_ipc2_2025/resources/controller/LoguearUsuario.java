/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1_ipc2_2025.resources.controller;

import com.mycompany.proyecto1_ipc2_2025.resources.conexiondb.ConexionDB;
import com.mycompany.proyecto1_ipc2_2025.resources.encriptacion.EncriptarMD5;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.*;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "LoguearUsuario", urlPatterns = {"/LoguearUsuario"})
public class LoguearUsuario extends HttpServlet {

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
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoguearUsuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoguearUsuario at " + request.getContextPath() + "</h1>");
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
        ResultSet resultSet = null;
        EncriptarMD5 encriptar = new EncriptarMD5();

        if (request.getParameter("login") != null) {
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            HttpSession sesion = request.getSession();
            try {
                statement = conexionDB.getConnection().createStatement();
                resultSet = statement.executeQuery("SELECT * FROM Usuario WHERE nombre_usuario = '" + user + "' AND password = '" + encriptar.getMD5(password) + "';");
                while (resultSet.next()) {
                    sesion.setAttribute("logueado", "1");
                    sesion.setAttribute("user", resultSet.getString("nombre_usuario"));
//                    sesion.setAttribute("id", resultSet.getString("id"));
                    String rolUsuario = resultSet.getString("tipo_rol_fk");
                    System.out.println("Rol: " + rolUsuario);
                    if (rolUsuario.equals("3")) {
                        response.sendRedirect("Vista/panelAdministracion.jsp");
                    } else {
                        response.sendRedirect("index.jsp");
                    }
                }
                //out.print("<div class=\"alert alert-danger\" role=\"alert\">Usuario no valido</div>");
                request.setAttribute("errorMessage", "Usuario o contraseña incorrectos.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                out.print("error sql jajaaj:" + e);
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
