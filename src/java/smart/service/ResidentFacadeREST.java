/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import smart.Resident;


/**
 *
 * @author hasisi
 */
@Stateless
@Path("smart.resident")
public class ResidentFacadeREST extends AbstractFacade<Resident> {

    @PersistenceContext(unitName = "FIT5046ASSIGNMENTP1PU")
    private EntityManager em;

    public ResidentFacadeREST() {
        super(Resident.class);
    }

    @POST
    //@Override
    @Path("register/")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Resident entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Resident entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Resident find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    
    @GET
    @Path("findRidByEmail/{email}")
    @Produces({"application/json"})
    public String findPWByUsername(@PathParam("email") String email) {
         String query = "";
        query = em.createQuery("SELECT r.resid FROM Resident r WHERE r.email = :em",
                    Object[].class).setParameter("em", email).getResultList().toString();
            return query;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resident> findAll() {
        return super.findAll();
    }
    

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Resident> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("findByFName/{fName}")
    @Produces({"application/json"})
    public List<Resident> findByFNAME(@PathParam("fName") String fName) {
        Query query = em.createNamedQuery("Resident.findByFName");
        query.setParameter("fName", fName);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySName/{sName}")
    @Produces({"application/json"})
    public List<Resident> findBySNAME(@PathParam("sName") String sName) {
        Query query = em.createNamedQuery("Resident.findBySName");
        query.setParameter("sName", sName);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDob/{dob}")
    @Produces({"application/json"})
    public List<Resident> findByDob(@PathParam("dob")  String dob) {
        Query query = em.createNamedQuery("Resident.findByDob");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dob);
            query.setParameter("dob",date);
        } catch (ParseException ex) {
            Logger.getLogger(ResidentFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return query.getResultList();
    }
    
    @GET
    @Path("findByAddress/{address}")
    @Produces({"application/json"})
    public List<Resident> findByAddress(@PathParam("address") String address) {
        Query query = em.createNamedQuery("Resident.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<Resident> findByPostcode(@PathParam("postcode") String postcode) {
        Query query = em.createNamedQuery("Resident.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    @GET
    @Path("findByEmail/{email}")
    @Produces({"application/json"})
    public List<Resident> findByEmail(@PathParam("email") String email) {
        Query query = em.createNamedQuery("Resident.findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }
    
    @GET
    @Path("findByMobile/{mobile}")
    @Produces({"application/json"})
    public List<Resident> findByMobile(@PathParam("mobile") String mobile) {
        Query query = em.createNamedQuery("Resident.findByMobile");
        query.setParameter("mobile", mobile);
        return query.getResultList();
    }
    
    @GET
    @Path("findByNoOfRes/{mobile}")
    @Produces({"application/json"})
    public List<Resident> findByNoOfRes(@PathParam("noOfRes") String noOfRes) {
        Query query = em.createNamedQuery("Resident.findByNoOfRes");
        query.setParameter("noOfRes", noOfRes);
        return query.getResultList();
    }
    
    @GET
    @Path("findByEnergyProvider/{energyProvider}")
    @Produces({"application/json"})
    public List<Resident> findByEnergyProvider(@PathParam("energyProvider") String energyProvider) {
        Query query = em.createNamedQuery("Resident.findByEnergyProvider");
        query.setParameter("energyProvider", energyProvider);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFnameANDEmail/{fName}/{email}")
    @Produces({"application/json"})
    public List<Resident> findByFnameANDEmail(@PathParam("fName") String fName, @PathParam("email") String email) {
        TypedQuery<Resident> q = em.createQuery(
        "SELECT r FROM Resident r WHERE r.fName = :fName AND r.email = :email", Resident.class);      
        q.setParameter("fName",fName).setParameter("email",email);
        return q.getResultList();
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
