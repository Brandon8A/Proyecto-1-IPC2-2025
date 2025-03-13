<%-- 
    Document   : eliminarComponente
    Created on : 12 mar 2025, 19:15:41
    Author     : brandon
--%>

<%@page import="com.mycompany.proyecto1_ipc2_2025.resources.conexiondb.ConexionDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("logueado") == null || sesion.getAttribute("logueado").equals("0")) {
                response.sendRedirect("../index.jsp");
            }
            ConexionDB conexion = new ConexionDB();

            try {
                conexion.getStatement().executeUpdate("UPDATE Pieza SET eliminado = TRUE WHERE id_pieza= '" + request.getParameter("id") + "';");
                conexion.getStatement().close();
                request.getRequestDispatcher("crudComponentes.jsp").forward(request, response);
            } catch (Exception e) {
                out.print(e);
            }
        %>
    </body>
</html>
