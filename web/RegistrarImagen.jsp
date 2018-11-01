<%-- 
    Document   : RegistrarImagen
    Created on : 27/10/2018, 11:12:29
    Author     : nilmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar_Imatge</title>
        <link rel="stylesheet" type="text/css" href="style/estilos.css">
    </head>
    <body>
        <h1>Registrar Imagen</h1>
        <form method="POST" action="http://localhost:8080/RestAD/webresources/generic/register/">
            <label for="title"><b>Título</b></label>
            <input type="text" name="title" required >
            <br><br>
            <label for="description"><b>Descripción</b></label>
            <textarea name="description" rows="5" cols="25" required></textarea>
            <br><br>
            <label for="keywords"><b>Palabras clave</b></label>
            <input type="text" name="keywords" required>
            <br><br>
            <label for="author"><b>Autor</b></label>
            <input type="text" name="author" required>
            <br><br>
            <label for="creation"><b>Fecha de creación</b></label>
            <input type="date" name="creation" required>
            <br><br>
            <input type="submit" value="Submit" class="botonMenu">
        </form>
    </body>
</html>
