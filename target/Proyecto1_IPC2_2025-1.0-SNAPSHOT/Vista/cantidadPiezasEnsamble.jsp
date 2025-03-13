<%-- 
    Document   : cantidadPiezasEnsamble
    Created on : 12 mar 2025, 17:19:12
    Author     : brandon
--%>

<%@page import="java.sql.*"%>
<%@page import="com.mycompany.proyecto1_ipc2_2025.resources.conexiondb.ConexionDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.tailwindcss.com"></script>
        <title>JSP Page</title>
    </head>
    <body class="flex items-center justify-center min-h-screen bg-gray-100">
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("logueado") == null || sesion.getAttribute("logueado").equals("0")) {
                response.sendRedirect("../index.jsp");
            }
        %>
        <div class="w-full max-w-md p-6 bg-white shadow-lg rounded-lg">
            <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Cantidad de piezas</h2>
            <form action="${pageContext.servletContext.contextPath}/EnsamblePiezaAdmin?tipoComputadora=<%= request.getParameter("tipoComputadora")%>&tipoPieza=<%= request.getParameter("tipoPieza") %>" method="post">
                <div class="flex justify-between items-center mb-4">
                    <label class="block text-gray-700 font-medium mb-2" for="password">Seleccionar cantidad de piezas</label>
                    <select class="form-select" name="cantidad" aria-label="Default select example">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <button name="enviar" type="submit" class="w-full bg-purple-600 hover:bg-purple-700 text-white font-medium py-2 rounded-lg transition">Asignar cantidad de piezas</button>
                <a><a href="seleccionarPieza.jsp" class="btn btn-danger mt-2">Cancelar</a>
            </form>
        </div>
    </body>
</html>
