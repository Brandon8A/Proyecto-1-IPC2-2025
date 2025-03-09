<%-- 
    Document   : RegistrarUsuario
    Created on : 7 mar 2025, 15:56:38
    Author     : brandon
--%>

<%@page import="java.sql.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://cdn.tailwindcss.com"></script>
        <title>Registrar Usuario</title>
    </head>
    <body>
    <body class="flex items-center justify-center min-h-screen bg-gray-100">
        <%
            final String URL_MYSQL = "jdbc:mysql://localhost:3306/Tech_Solutions_Hub";
            final String USER = "root";
            final String PASSWORD = "brandon031200";

            //Agregando connection y statement solo para ingresar datos a la DB
            Connection connection = null;
            Statement statement = null;
        %>
        <div class="w-full max-w-md p-6 bg-white shadow-lg rounded-lg">
            <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Registrar Usuario</h2>
            <form action="RegistrarUsuario.jsp" method="post">
                <div class="mb-4">
                    <label class="block text-gray-700 font-medium mb-2" for="user">Nombre Usuario</label>
                    <input type="text" id="user" name="user" required class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500" placeholder="Usuario">
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700 font-medium mb-2" for="password">Contraseña</label>
                    <input type="password" id="password1" name="password1" required class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500" placeholder="Contraseña">
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700 font-medium mb-2" for="password">Confirmar Contraseña</label>
                    <input type="password" id="password2" name="password2" required class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500" placeholder="Repetir Contraseña">
                </div>
                <div class="flex justify-between items-center mb-4">
                    <label class="block text-gray-700 font-medium mb-2" for="password">Seleccionar Rol</label>
                    <select class="form-select" name="rol" aria-label="Default select example">
                        <option value="1">Encargado de Ensamblaje</option>
                        <option value="2">Encargado de Ventas</option>
                        <option value="3">Administrador</option>
                    </select>
                </div>
                <button name="enviar" type="submit" class="w-full bg-purple-600 hover:bg-purple-700 text-white font-medium py-2 rounded-lg transition">Registrar</button>
                <a><a href="../index.jsp" class="btn btn-danger mt-2">Cancelar</a>
            </form>
        </div>
        <%
            if (request.getParameter("enviar") != null) {
                String nombre = request.getParameter("user");
                String password = request.getParameter("password1");
                String rol = request.getParameter("rol");
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
                    statement = connection.createStatement();
                    statement.executeUpdate("INSERT INTO Usuario (nombre_usuario, password, tipo_rol_fk) VALUES('" + nombre + "', '" + password + "', '" + rol + "');");
                    
                    request.getRequestDispatcher("../index.jsp").forward(request, response);
                } catch (Exception e) {
                    out.print(e);
                }
            }
        %>
    </body>
</body>
</html>
