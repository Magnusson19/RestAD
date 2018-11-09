/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author nilmc
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    
    /**
     * Retrieves representation of an instance of asd.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }

    /**
 * POST method to register a new image
 * @param title
 * @param description
 * @param keywords
 * @param author
 * @param crea_date
 * @return
 */
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage (@FormParam("title") String title,
                                 @FormParam("description") String description,
                                 @FormParam("keywords") String keywords,
                                 @FormParam("author") String author,
                                 @FormParam("creation") String crea_date) {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            //connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\myPC\\Desktop\\LAB4.db");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db");
            
            PreparedStatement statement = connection.prepareStatement("select max(id_imagen) from imagenes");
            ResultSet rs = statement.executeQuery();
            int id = 0;
            if (rs.next()) id = rs.getInt(1);
            else return "<html><head/><body><h1>DB it not inicialized</h1></body></html>";
            statement = connection.prepareStatement("insert into imagenes values (?,?,?,?,?,?)");
            statement.setInt(1, id+1);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, keywords);
            statement.setString(5, author);
            statement.setString(6, crea_date);
            if (statement.executeUpdate() == 1){
                return "<html><head/><body><h1>Registre realitzat!</h1></body></html>";
            }
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
        }
        finally {
            if(connection != null)
              try {
                  connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "<html><head/><body><h1>Registre no realitzat</h1></body></html>";
    }
    
    /**
 * POST method to register a new image
 * @param title
 * @param description
 * @param keywords
 * @param author
 * @param crea_date
 * @return
 */
    @Path("modify")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)         //COM BUSQUEM? TOTS ELS VALORS ES PODEN MODIFICAR MENYS ID
    @Produces(MediaType.TEXT_HTML)
    public String modifyImage (@FormParam("title") String title,
                                @FormParam("description") String description,
                                @FormParam("keywords") String keywords,
                                @FormParam("author") String author,
                                @FormParam("creation") String crea_date) {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            //connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\myPC\\Desktop\\LAB4.db");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db");
            
            
            PreparedStatement statement = connection.prepareStatement("update imagenes set titulo = ?, descripcion = ?,"
                                                                    + "palabras_clave = ?, autor = ?,fecha_creacion = ?"
                                                                    + "where id_imagen = 1");
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, keywords);
            statement.setString(4, author);
            statement.setString(5, crea_date);
            if (statement.executeUpdate() == 1){
                return "<html><head/><body><h1>Modificació realitzada!</h1></body></html>";
            }
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
        }
        finally {
            if(connection != null)
              try {
                  connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "<html><head/><body><h1>Modifiació no realitzada</h1></body></html>";
    }
    
    /**
 * GET method to list images
 * @return
 */
    @Path("list")
    @GET
    @Produces(MediaType.TEXT_HTML)         //CLIENT JAVA? CLIENT JSP?
    public String listImages () {
        Connection connection = null;
        String resposta = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            //connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\myPC\\Desktop\\LAB4.db");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imagenes");
            ResultSet rs = statement.executeQuery();
            resposta = "<html><head/><body><h1>Llistar</h1><table border = \"1\" width = \"50%\">\n" +
"                                                                   <tr>\n" +
"                                                                       <th>ID</th>\n" +
"                                                                       <th>Titulo</th>\n" +
"                                                                       <th>Descripcion</th>\n" +
                                                                        "<th>Palabras Clave</th>\n" +
                                                                        "<th>Autor</th>\n" +
                                                                        "<th>Fecha Creacion</th>\n" +
"                                                                   </tr>";
            while (rs.next()) {
               resposta = resposta +"<tr>";
               resposta  = resposta + "<td>" + String.valueOf(rs.getInt("id_imagen")) + "</td>";
               resposta = resposta + "<td>" + rs.getString("titulo") + "</td>";
               resposta = resposta + "<td>" + rs.getString("Descripcion") + "</td>";
               resposta = resposta + "<td>" + rs.getString("palabras_clave") + "</td>";
               resposta = resposta + "<td>" + rs.getString("autor") + "</td>";
               resposta = resposta + "<td>" + rs.getString("fecha_creacion") + "</td>";
               resposta = resposta + "</tr>";
            }
            resposta = resposta + "</table></body></html>";
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
        }
        finally {
            if(connection != null)
              try {
                  connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resposta;
    }
    
    /**
 * GET method to search images by id
 * @param id
 * @return
 */
    @Path("searchID/{id}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByID (@PathParam("id") int id) {
        Connection connection = null;
        String resposta = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            //connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\myPC\\Desktop\\LAB4.db");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imagenes where id_imagen = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            resposta = "<html><head/><body><h1>Llistar</h1><table border = \"1\" width = \"50%\">\n" +
"                                                                   <tr>\n" +
"                                                                       <th>ID</th>\n" +
"                                                                       <th>Titulo</th>\n" +
"                                                                       <th>Descripcion</th>\n" +
                                                                        "<th>Palabras Clave</th>\n" +
                                                                        "<th>Autor</th>\n" +
                                                                        "<th>Fecha Creacion</th>\n" +
"                                                                   </tr>";
            while (rs.next()) {
               resposta = resposta +"<tr>";
               resposta  = resposta + "<td>" + String.valueOf(rs.getInt("id_imagen")) + "</td>";
               resposta = resposta + "<td>" + rs.getString("titulo") + "</td>";
               resposta = resposta + "<td>" + rs.getString("Descripcion") + "</td>";
               resposta = resposta + "<td>" + rs.getString("palabras_clave") + "</td>";
               resposta = resposta + "<td>" + rs.getString("autor") + "</td>";
               resposta = resposta + "<td>" + rs.getString("fecha_creacion") + "</td>";
               resposta = resposta + "</tr>";
            }
            resposta = resposta + "</table></body></html>";
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
        }
        finally {
            if(connection != null)
              try {
                  connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resposta;
    }
    
 /**
 * GET method to search images by title
 * @param title
 * @return
 */
    @Path("searchTitle/{title}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByTitle (@PathParam("title") String title) {
        Connection connection = null;
        String resposta = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            //connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\myPC\\Desktop\\LAB4.db");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imagenes where titulo like ?");
            statement.setString(1, "%" + title + "%");
            ResultSet rs = statement.executeQuery();
            resposta = "<html><head/><body><h1>Llistar</h1><table border = \"1\" width = \"50%\">\n" +
"                                                                   <tr>\n" +
"                                                                       <th>ID</th>\n" +
"                                                                       <th>Titulo</th>\n" +
"                                                                       <th>Descripcion</th>\n" +
                                                                        "<th>Palabras Clave</th>\n" +
                                                                        "<th>Autor</th>\n" +
                                                                        "<th>Fecha Creacion</th>\n" +
"                                                                   </tr>";
            while (rs.next()) {
               resposta = resposta +"<tr>";
               resposta  = resposta + "<td>" + String.valueOf(rs.getInt("id_imagen")) + "</td>";
               resposta = resposta + "<td>" + rs.getString("titulo") + "</td>";
               resposta = resposta + "<td>" + rs.getString("Descripcion") + "</td>";
               resposta = resposta + "<td>" + rs.getString("palabras_clave") + "</td>";
               resposta = resposta + "<td>" + rs.getString("autor") + "</td>";
               resposta = resposta + "<td>" + rs.getString("fecha_creacion") + "</td>";
               resposta = resposta + "</tr>";
            }
            resposta = resposta + "</table></body></html>";
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
        }
        finally {
            if(connection != null)
              try {
                  connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resposta;
    }
    
 /**
 * GET method to search images by creation date
 * @param creaDate
 * @return
 */
    @Path("searchCreationDate/{date}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByCreationDate (@PathParam("date") String date) {
        Connection connection = null;
        String resposta = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            //connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\myPC\\Desktop\\LAB4.db");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imagenes where fecha_creacion = ?");
            statement.setString(1, date);
            ResultSet rs = statement.executeQuery();
            resposta = "<html><head/><body><h1>Llistar</h1><table border = \"1\" width = \"50%\">\n" +
"                                                                   <tr>\n" +
"                                                                       <th>ID</th>\n" +
"                                                                       <th>Titulo</th>\n" +
"                                                                       <th>Descripcion</th>\n" +
                                                                        "<th>Palabras Clave</th>\n" +
                                                                        "<th>Autor</th>\n" +
                                                                        "<th>Fecha Creacion</th>\n" +
"                                                                   </tr>";
            while (rs.next()) {
               resposta = resposta +"<tr>";
               resposta  = resposta + "<td>" + String.valueOf(rs.getInt("id_imagen")) + "</td>";
               resposta = resposta + "<td>" + rs.getString("titulo") + "</td>";
               resposta = resposta + "<td>" + rs.getString("Descripcion") + "</td>";
               resposta = resposta + "<td>" + rs.getString("palabras_clave") + "</td>";
               resposta = resposta + "<td>" + rs.getString("autor") + "</td>";
               resposta = resposta + "<td>" + rs.getString("fecha_creacion") + "</td>";
               resposta = resposta + "</tr>";
            }
            resposta = resposta + "</table></body></html>";
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
        }
        finally {
            if(connection != null)
              try {
                  connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resposta;
    }
    
    /**
 * GET method to search images by author
 * @param author
 * @return
 */
    @Path("searchAuthor/{author}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByAuthor (@PathParam("author") String author) {
        Connection connection = null;
        String resposta = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            //connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\myPC\\Desktop\\LAB4.db");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imagenes where autor like ?");
            statement.setString(1, "%" + author + "%");
            ResultSet rs = statement.executeQuery();
            resposta = "<html><head/><body><h1>Llistar</h1><table border = \"1\" width = \"50%\">\n" +
"                                                                   <tr>\n" +
"                                                                       <th>ID</th>\n" +
"                                                                       <th>Titulo</th>\n" +
"                                                                       <th>Descripcion</th>\n" +
                                                                        "<th>Palabras Clave</th>\n" +
                                                                        "<th>Autor</th>\n" +
                                                                        "<th>Fecha Creacion</th>\n" +
"                                                                   </tr>";
            while (rs.next()) {
               resposta = resposta +"<tr>";
               resposta  = resposta + "<td>" + String.valueOf(rs.getInt("id_imagen")) + "</td>";
               resposta = resposta + "<td>" + rs.getString("titulo") + "</td>";
               resposta = resposta + "<td>" + rs.getString("Descripcion") + "</td>";
               resposta = resposta + "<td>" + rs.getString("palabras_clave") + "</td>";
               resposta = resposta + "<td>" + rs.getString("autor") + "</td>";
               resposta = resposta + "<td>" + rs.getString("fecha_creacion") + "</td>";
               resposta = resposta + "</tr>";
            }
            resposta = resposta + "</table></body></html>";
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
        }
        finally {
            if(connection != null)
              try {
                  connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resposta;
    }
 /**
 * GET method to search images by keyword
 * @param keywords
 * @return
 */
    @Path("searchKeywords/{keywords}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByKeywords (@PathParam("keywords") String keywords) {
        Connection connection = null;
        String resposta = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            //connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\myPC\\Desktop\\LAB4.db");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imagenes where palabras_clave like ?");
            statement.setString(1, "%" + keywords + "%");
            ResultSet rs = statement.executeQuery();
            resposta = "<html><head/><body><h1>Llistar</h1><table border = \"1\" width = \"50%\">\n" +
"                                                                   <tr>\n" +
"                                                                       <th>ID</th>\n" +
"                                                                       <th>Titulo</th>\n" +
"                                                                       <th>Descripcion</th>\n" +
                                                                        "<th>Palabras Clave</th>\n" +
                                                                        "<th>Autor</th>\n" +
                                                                        "<th>Fecha Creacion</th>\n" +
"                                                                   </tr>";
            while (rs.next()) {
               resposta = resposta +"<tr>";
               resposta  = resposta + "<td>" + String.valueOf(rs.getInt("id_imagen")) + "</td>";
               resposta = resposta + "<td>" + rs.getString("titulo") + "</td>";
               resposta = resposta + "<td>" + rs.getString("Descripcion") + "</td>";
               resposta = resposta + "<td>" + rs.getString("palabras_clave") + "</td>";
               resposta = resposta + "<td>" + rs.getString("autor") + "</td>";
               resposta = resposta + "<td>" + rs.getString("fecha_creacion") + "</td>";
               resposta = resposta + "</tr>";
            }
            resposta = resposta + "</table></body></html>";
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
        }
        finally {
            if(connection != null)
              try {
                  connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resposta;
    }
    
    @Path("searchTitleAuthor/{title},{author}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByTitleAuthor (@PathParam("title") String title, @PathParam("author") String author) {
        Connection connection = null;
        String resposta = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            //connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\myPC\\Desktop\\LAB4.db");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nilmc\\Desktop\\LAB4.db");
            PreparedStatement statement = connection.prepareStatement("select * from imagenes where titulo like ? and autor like ?");
            statement.setString(1, "%" + title + "%");
            statement.setString(2, "%" + author + "%");
            ResultSet rs = statement.executeQuery();
            resposta = "<html><head/><body><h1>Llistar</h1><table border = \"1\" width = \"50%\">\n" +
"                                                                   <tr>\n" +
"                                                                       <th>ID</th>\n" +
"                                                                       <th>Titulo</th>\n" +
"                                                                       <th>Descripcion</th>\n" +
                                                                        "<th>Palabras Clave</th>\n" +
                                                                        "<th>Autor</th>\n" +
                                                                        "<th>Fecha Creacion</th>\n" +
"                                                                   </tr>";
            while (rs.next()) {
               resposta = resposta +"<tr>";
               resposta  = resposta + "<td>" + String.valueOf(rs.getInt("id_imagen")) + "</td>";
               resposta = resposta + "<td>" + rs.getString("titulo") + "</td>";
               resposta = resposta + "<td>" + rs.getString("Descripcion") + "</td>";
               resposta = resposta + "<td>" + rs.getString("palabras_clave") + "</td>";
               resposta = resposta + "<td>" + rs.getString("autor") + "</td>";
               resposta = resposta + "<td>" + rs.getString("fecha_creacion") + "</td>";
               resposta = resposta + "</tr>";
            }
            resposta = resposta + "</table></body></html>";
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
        }
        finally {
            if(connection != null)
              try {
                  connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resposta;
    }

}
