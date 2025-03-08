<%-- 
    Document   : index.jsp
    Created on : 6 mar 2025, 23:34:40
    Author     : brandon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Agregar BOOTSTRAP -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!-- Agregar Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://cdn.tailwindcss.com"></script>
        <title>Login</title>
    </head>
    <body>
        <!-- Posicionando componente -->

    <body class="flex items-center justify-center min-h-screen bg-gray-100">
        <div class="w-full max-w-md p-6 bg-white shadow-lg rounded-lg">
            <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Iniciar Sesión</h2>
            <form action="index.jsp" method="post">
                <div class="mb-4">
                    <label class="block text-gray-700 font-medium mb-2" for="user">Usuario</label>
                    <input type="text" id="user" name="user" required class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500" placeholder="Usuario">
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700 font-medium mb-2" for="password">Contraseña</label>
                    <input type="password" id="password" name="password" required class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500" placeholder="Contraseña">
                </div>
                <div class="flex justify-between items-center mb-4">
                    <a href="Vista/RegistrarUsuario.jsp" class="text-sm text-purple-500 hover:underline">Registrarse</a>
                </div>
                <button type="submit" class="w-full bg-purple-600 hover:bg-purple-700 text-white font-medium py-2 rounded-lg transition">Iniciar Sesión</button>
            </form>
        </div>
    </body>
</body>
</html>
