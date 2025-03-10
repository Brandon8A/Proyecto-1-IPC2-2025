<%-- 
    Document   : editarRolUsuario
    Created on : 9 mar 2025, 21:19:27
    Author     : brandon
--%>

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
            <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Editar Rol Usuario</h2>
            <form action="${pageContext.servletContext.contextPath}/EditarRolUsuario?nombre=<%= request.getParameter("nombre")%>" method="post">
                <div class="flex justify-between items-center mb-4">
                    <label class="block text-gray-700 font-medium mb-2">Usuario: <%= request.getParameter("nombre")%></label>
                </div>
                <div class="flex justify-between items-center mb-4">
                    <label class="block text-gray-700 font-medium mb-2" for="password">Rol actual: <%= request.getParameter("rol")%></label>
                </div>
                <div class="flex justify-between items-center mb-4">
                    <label class="block text-gray-700 font-medium mb-2" for="password">Cambiar Rol</label>
                    <select class="form-select" name="rol" aria-label="Default select example">
                        <option value="1">Encargado de Ensamblaje</option>
                        <option value="2">Encargado de Ventas</option>
                        <option value="3">Administrador</option>
                    </select>
                </div>
                <button name="enviar" type="submit" class="w-full bg-purple-600 hover:bg-purple-700 text-white font-medium py-2 rounded-lg transition">Actualizar</button>
                <a><a href="editarUsuarios.jsp" class="btn btn-danger mt-2">Cancelar</a>
            </form>
        </div>
    </body>
</html>
