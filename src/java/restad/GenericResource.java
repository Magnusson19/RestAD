/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
        return "H";
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
