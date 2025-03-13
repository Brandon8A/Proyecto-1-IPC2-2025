<%-- 
    Document   : seleccionarPieza
    Created on : 12 mar 2025, 13:06:56
    Author     : brandon
--%>

<%@page import="java.sql.*"%>
<%@page import="com.mycompany.proyecto1_ipc2_2025.resources.conexiondb.ConexionDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <title>Seleccionar Pieza</title>
    </head>
    <body class="flex h-screen bg-gray-100">
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("logueado") == null || sesion.getAttribute("logueado").equals("0")) {
                response.sendRedirect("../index.jsp");
            }
            ConexionDB conexion = new ConexionDB();
            Statement statement = null;
            ResultSet resultSet = null;
        %>
        <!-- Sidebar -->
        <aside class="w-64 bg-gray-800 text-white flex flex-col">
            <div class="p-5 text-xl font-bold border-b border-gray-700">Panel</div>
            <nav class="flex-1 p-4">
                <a href="ensamblarComputadora.jsp" class="block px-4 py-2 rounded-lg hover:bg-gray-700">Regresar</a>
            </nav>
            <div class="p-4 border-t border-gray-700">
                <a href="${pageContext.servletContext.contextPath}/CerrarSesion" class="block px-4 py-2 text-red-400 hover:bg-red-600 hover:text-white rounded-lg">
                    Cerrar sesión
                </a>
            </div>
        </aside>
        <!-- Contenido Principal -->
        <div class="flex-1 flex flex-col">
            <!-- Navbar -->
            <header class="bg-white shadow-md p-4 flex justify-between items-center">
                <h1 class="text-xl font-bold">Ensamblar Computadora: <%= request.getParameter("tipoComputadora")%></h1>
                <div class="flex items-center space-x-4">
                    <span class="text-gray-700">Admin</span>
                    <img src="https://via.placeholder.com/40" class="w-10 h-10 rounded-full border">
                </div>
            </header>
            <!-- Contenido -->
            <main class="flex-1 p-6">
                <div class="bg-white p-6 shadow rounded-lg">
                    <h2 class="text-2xl font-bold mb-4">Piezas en stock</h2>
                    <form>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col" class="text-center"></th>
                                </tr>
                                <tr>
                                    <th scope="col"">Tipo Pieza</th>
                                    <th scope="col">Cantidad</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    try {
                                        statement = conexion.getConnection().createStatement();
                                        resultSet = statement.executeQuery("SELECT nombre_pieza FROM Pieza;");
                                        while (resultSet.next()) {
                                %>
                                <tr>
                                    <th scope="row"><%= resultSet.getString(1)%></th>
                                    <td>
                                        <a href="cantidadPiezasEnsamble.jsp?tipoComputadora=<%= request.getParameter("tipoComputadora")%>&tipoPieza=<%= resultSet.getString(1)%>"><input type="button" class="btn btn-primary" value="Asignar Pieza" id="asignacion"></a>
                                    </td>
                                </tr>
                                <%
                                            //
                                        }
                                        statement.close();
                                    } catch (Exception e) {
                                        out.print(e);
                                    }
                                %>
                            </tbody>
                        </table>
                    </form>
                </div>
            </main>
            <!-- Footer -->
            <footer class="bg-white text-center text-gray-600 p-4 border-t">
                &copy; 2025 Admin Panel. Todos los derechos reservados.
            </footer>
        </div>
    </body>
</html>
