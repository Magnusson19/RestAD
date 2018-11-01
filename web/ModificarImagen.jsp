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
    
        <sql:setDataSource var = "snapshot" driver = "org.sqlite.JDBC"
         url = "jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db"/>
        
                
        <sql:query dataSource = "${snapshot}" var = "result">
            SELECT * from imagenes where id_imagen=1
        </sql:query>
        
                  
        <h1>Modificar Imagen</h1>
        <form method="POST" action="http://localhost:8080/RestAD/webresources/generic/modify/">
            <label for="title"><b>Título</b></label>
            <input type="text" name="title" value="${result.rows[0].titulo}">
            <br><br>
            <label for="description"><b>Descripción</b></label>
            <textarea name="description" rows="5" cols="25" >${result.rows[0].descripcion}</textarea>
            <br><br>
            <label for="keywords"><b>Palabras clave</b></label>
            <input type="text" name="keywords" value="${result.rows[0].palabras_clave}">
            <br><br>
            <label for="author"><b>Autor</b></label>
            <input type="text" name="author" value="${result.rows[0].autor}">
            <br><br>
            <label for="creation"><b>Fecha de creación</b></label>
            <input type="date" name="creation" value="${result.rows[0].fecha_creacion}">
            <br><br>
            <input type="submit" value="Submit" class="botonMenu">
        </form>
    
</html>
