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
            <label for="titulo"><b>Título</b></label>
            <input type="text" name="titulo" required >
            <br><br>
            <label for="descripcion"><b>Descripción</b></label>
            <textarea name="descripcion" rows="5" cols="25" required></textarea>
            <br><br>
            <label for="palabras_clave"><b>Palabras clave</b></label>
            <input type="text" name="palabras_clave" required>
            <br><br>
            <label for="autor"><b>Autor</b></label>
            <input type="text" name="autor" required>
            <br><br>
            <label for="fecha_creacion"><b>Fecha de creación</b></label>
            <input type="date" name="fecha_creacion" required>
            <br><br>
            <input type="submit" value="Submit" class="botonMenu">
        </form>
    </body>
</html>
