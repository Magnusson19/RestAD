/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        try {
            Connection connection = null;
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
            if (statement.executeUpdate() == 1)return "<html><head/><body><h1>Registre realitzat!</h1></body></html>";
        } catch(Exception e)
        {
          System.err.println(e.getMessage());
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String modifyImage (@FormParam("title") String title,
                                @FormParam("description") String description,
                                @FormParam("keywords") String keywords,
                                @FormParam("author") String author,
                                @FormParam("creation") String crea_date) {
        return "H";
    }
    
    /**
 * GET method to list images
 * @return
 */
    @Path("list")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String listImages () {
        return "h";
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
        return "H";
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
        return "H";
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
        return "H";
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
        return "H";
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
        return "H";
    }

}
