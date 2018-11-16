<%-- 
    Document   : ModificarImagen
    Created on : 31/10/2018, 13:40:27
    Author     : nilmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/plain; charset=UTF-8">
        <title>Modificar_Imatge</title>
        <link rel="stylesheet" type="text/css" href="style/estilos.css">
    </head>
    
      
        <h1>Modificar Imagen</h1>
        <form method="POST" action="http://localhost:8080/RestAD/webresources/generic/modify/" enctype="multipart/form-data">
            <label for="title"><b>Título</b></label>
            <input type="text" name="title" value=<%= request.getParameter("title")%> required>
            <br><br>
            <label for="description"><b>Descripción</b></label>
            <textarea name="description" rows="5" cols="25" required><%=request.getParameter("description")%></textarea>
            <br><br>
            <label for="keywords"><b>Palabras clave</b></label>
            <input type="text" name="keywords" value=<%= request.getParameter("keywords")%> required>
            <br><br>
            <label for="author"><b>Autor</b></label>
            <input type="text" name="author" value=<%= request.getParameter("author")%> required>
            <br><br>
            <label for="creation"><b>Fecha de creación</b></label>
            <input type="date" name="creation" value=<%= request.getParameter("creation")%> required>
            <br><br>
            <label for="imagen"><b>Inserta Imagen</b></label>
            <input type="file" name="imagen" accept="image/JPEG">
            <br><br>
            <input type="hidden" name="id" value=<%= request.getParameter("id")%>>
            <br>
            <input type="submit" value="Submit" class="botonMenu">
        </form>
    
</html>
