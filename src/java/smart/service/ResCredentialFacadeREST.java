/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import smart.ResCredential;
import smart.Resident;

/**
 *
 * @author hasisi
 */
@Stateless
@Path("smart.rescredential")
public class ResCredentialFacadeREST extends AbstractFacade<ResCredential> {

    @PersistenceContext(unitName = "FIT5046ASSIGNMENTP1PU")
    private EntityManager em;

    public ResCredentialFacadeREST() {
        super(ResCredential.class);
    }

    @POST
    @Path("create")
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ResCredential entity) {
        super.create(entity);
    }
    


    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, ResCredential entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ResCredential find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ResCredential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ResCredential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("findByUsername/{username}")
    @Produces({"application/json"})
    public List<ResCredential> findByUsername(@PathParam("username") String username) {
        Query query = em.createNamedQuery("ResCredential.findByUsername");
        query.setParameter("username", username);
        return query.getResultList();
    }
    
    @GET
    @Path("findPWByUsername/{username}")
    @Produces({"application/json"})
    public String findPWByUsername(@PathParam("username") String username) {
         String query = "";
        query = em.createQuery("SELECT r.hashpw FROM ResCredential r WHERE r.username = :user",
                    Object[].class).setParameter("user", username).getResultList().toString();
            return query;
    }
    
    @GET
    @Path("findByHashpw/{hashpw}")
    @Produces({"application/json"})
    public List<ResCredential>findByHashpw(@PathParam("hashpw") String hashpw) {
        Query query = em.createNamedQuery("ResCredential.findByHashpw");
        query.setParameter("hashpw", hashpw);
        return query.getResultList();
    }
    

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
