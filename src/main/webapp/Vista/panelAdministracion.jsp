<%-- 
    Document   : panelAdministracion
    Created on : 8 mar 2025, 23:23:08
    Author     : brandon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel de Administración</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="flex h-screen bg-gray-100">
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("logueado") == null || sesion.getAttribute("logueado").equals("0")) {
                response.sendRedirect("../index.jsp");
            }
        %>
        <!-- Sidebar -->
        <aside class="w-64 bg-gray-800 text-white flex flex-col">
            <div class="p-5 text-xl font-bold border-b border-gray-700">Panel</div>
            <nav class="flex-1 p-4">
                <a href="editarUsuarios.jsp" class="block px-4 py-2 rounded-lg hover:bg-gray-700">Usuarios</a>
                <a href="cargarDatos.jsp" class="block px-4 py-2 rounded-lg hover:bg-gray-700">Carga de datos</a>
                <a href="ensamblarComputadora.jsp" class="block px-4 py-2 rounded-lg hover:bg-gray-700">Ensamble</a>
                <a href="Venta/venta.jsp" class="block px-4 py-2 rounded-lg hover:bg-gray-700">Ventas</a>
                <a href="reportes.jsp" class="block px-4 py-2 rounded-lg hover:bg-gray-700">Reportes</a>
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
                <h1 class="text-xl font-bold">Panel de Administracion</h1>
                <div class="flex items-center space-x-4">
                    <span class="text-gray-700">Admin</span>
                    <img src="https://via.placeholder.com/40" class="w-10 h-10 rounded-full border">
                </div>
            </header>
            <!-- Contenido -->
            <main class="flex-1 p-6">
                <div class="bg-white p-6 shadow rounded-lg">
                    <h2 class="text-2xl font-bold mb-4">Bienvenido al Panel de Administración</h2>
                    <p class="text-gray-600">Aquí puedes gestionar usuarios, reportes y configuraciones.</p>
                </div>
            </main>
            <!-- Footer -->
            <footer class="bg-white text-center text-gray-600 p-4 border-t">
                &copy; 2025 Admin Panel. Todos los derechos reservados.
            </footer>
        </div>
    </body>
</html>
