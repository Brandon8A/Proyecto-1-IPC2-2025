<%-- 
    Document   : eliminarUsuario
    Created on : 10 mar 2025, 12:21:03
    Author     : brandon
--%>

<%@page import="com.mycompany.proyecto1_ipc2_2025.resources.conexiondb.ConexionDB"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ConexionDB connection = new ConexionDB();
            Statement statement = null;

            try {
                statement = connection.getConnection().createStatement();
                statement.executeUpdate("UPDATE Usuario SET eliminado = TRUE WHERE nombre_usuario = '"+request.getParameter("nombre")+"';");
                request.getRequestDispatcher("editarUsuarios.jsp").forward(request, response);
            } catch (Exception e) {
                out.print(e);
            }
        %>
    </body>
</html>
