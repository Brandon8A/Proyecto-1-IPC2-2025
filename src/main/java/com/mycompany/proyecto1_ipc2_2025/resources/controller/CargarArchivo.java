/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyecto1_ipc2_2025.resources.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author brandon
 */
@WebServlet(name = "CargarArchivo", urlPatterns = {"/CargarArchivo"})
public class CargarArchivo extends HttpServlet {

    private String contenido;

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
        // Asegúrate de que el servidor esté configurado para manejar archivos (ver más abajo)
//        request.setCharacterEncoding("UTF-8");
        
        // Obtener el archivo cargado a partir del formulario
        Part archivoPart = request.getPart("file"); // "archivo" es el nombre del campo <input> en el formulario
        String nombreArchivo = archivoPart.getSubmittedFileName();

        // Especificar el directorio donde se guardará el archivo en el servidor
        String rutaCarpeta = getServletContext().getRealPath("/") + "archivos_subidos/";
        File directorio = new File(rutaCarpeta);
        
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crear la carpeta si no existe
        }

        // Guardar el archivo cargado
        File archivoGuardado = new File(rutaCarpeta + nombreArchivo);
        try (InputStream inputStream = archivoPart.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(archivoGuardado)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;
            while ((bytesLeidos = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesLeidos);
            }
        }

        // Leer el archivo de texto y mostrar su contenido
        StringBuilder contenidoArchivo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoGuardado))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenidoArchivo.append(linea).append("<br>");
            }
        }

        // Enviar la respuesta con el contenido del archivo al navegador
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Contenido del archivo cargado:</h2>");
        out.println("<pre>" + contenidoArchivo.toString() + "</pre>");
        out.println("</body></html>");
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

    private String leerArchivo(InputStream inputStream) throws IOException {
        // Leer el contenido del archivo
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        return content.toString();
    }
}
