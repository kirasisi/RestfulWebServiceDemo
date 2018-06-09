/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
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
import smart.ElecUsage;
import static smart.ElecUsage_.uDate;
import smart.ResCredential;
import smart.Resident;

/**
 *
 * @author hasisi
 */
@Stateless
@Path("smart.elecusage")
public class ElecUsageFacadeREST extends AbstractFacade<ElecUsage> {

    @PersistenceContext(unitName = "FIT5046ASSIGNMENTP1PU")
    private EntityManager em;

    public ElecUsageFacadeREST() {
        super(ElecUsage.class);
    }

    @POST
    @Path("create")
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ElecUsage entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ElecUsage entity) {
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
    public ElecUsage find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ElecUsage> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("findDateByRid/{resid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object findDateByRid(@PathParam("resid") Integer resid) {
           SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
           List<Object[]>  queryList = em.createQuery("SELECT e.resid.resid, e.uDate FROM ElecUsage e WHERE e.resid.resid = :resid",
                    Object[].class).setParameter("resid", resid).getResultList();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Object[] row : queryList) {
                row[1] = formatter.format(row[1]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("uDate", (String) row[1]).build();
                arrayBuilder.add(usageObject);
                
            }
 
            JsonArray jArray = arrayBuilder.build();
            
            return jArray;
            
        } 
    
    
    @GET
    @Path("findByUDate/{uDate}")
    @Produces({"application/json"})
    public List<ElecUsage> findByUsageid(@PathParam("uDate")  String uDate) {
        Query query = em.createNamedQuery("ElecUsage.findByUDate");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(uDate);
            query.setParameter("uDate",date);
        } catch (ParseException ex) {
            Logger.getLogger(ResidentFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return query.getResultList();
    }
    
    @GET
    @Path("findByUHour/{uHour}")
    @Produces({"application/json"})
    public List<ElecUsage>findByUHour(@PathParam("uHour") Integer uHour) {
        Query query = em.createNamedQuery("ElecUsage.findByUHour");
        query.setParameter("uHour", uHour);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFridgeUsage/{fridgeUsage}")
    @Produces({"application/json"})
    public List<ElecUsage>findByFridgeUsager(@PathParam("fridgeUsage") Double fridgeUsage) {
        Query query = em.createNamedQuery("ElecUsage.findByFridgeUsage");
        query.setParameter("fridgeUsage", fridgeUsage);
        return query.getResultList();
    }
    
    @GET
    @Path("findByAirconUsage/{airconUsage}")
    @Produces({"application/json"})
    public List<ElecUsage>findByAirconUsage(@PathParam("airconUsage") Double airconUsage) {
        Query query = em.createNamedQuery("ElecUsage.findByAirconUsage");
        query.setParameter("airconUsage", airconUsage);
        return query.getResultList();
    }
    
    @GET
    @Path("findByWashmaUsage/{washmaUsage}")
    @Produces({"application/json"})
    public List<ElecUsage>findByWashmaUsage(@PathParam("washmaUsage") Double washmaUsage) {
        Query query = em.createNamedQuery("ElecUsage.findByWashmaUsage");
        query.setParameter("washmaUsage", washmaUsage);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTemperature/{temperature}")
    @Produces({"application/json"})
    public List<ElecUsage>findByTemperature(@PathParam("temperature") Double temperature) {
        Query query = em.createNamedQuery("ElecUsage.findByTemperature");
        query.setParameter("temperature", temperature);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPostcodeANDSnameANDEnProvider/{postcode}/{sName}/{energyProvider}")
    @Produces({"application/json"})
    public List<ElecUsage> findByPostcodeANDSnameANDEnProvider(@PathParam("postcode") String postcode,@PathParam("sName") String sName,
                                                           @PathParam("energyProvider") String energyProvider) {
        TypedQuery<ElecUsage> q = em.createQuery(
        "SELECT e FROM ElecUsage e WHERE e.resid.postcode = :postcode AND "
                + "e.resid.sName = :sName "
                + "AND e.resid.energyProvider = :energyProvider", ElecUsage.class);
        q.setParameter("postcode", postcode).setParameter("sName",sName).setParameter("energyProvider",energyProvider);
        return q.getResultList();
    }
    
   
    @GET
    @Path("findByEmailANDFnameANDSname/{email}/{fName}/{sName}")
    @Produces({"application/json"})
    public List<ElecUsage>findByEmailANDFnameANDSname(@PathParam("email") String email,@PathParam("fName") String fName,
                                                           @PathParam("sName") String sName) {
        Query query = em.createNamedQuery("ElecUsage.findByEmailANDFnameANDSname");
        query.setParameter("email", email).setParameter("fName",fName).setParameter("sName",sName);
        return query.getResultList();
    }
    

//    @GET
//    @Path("test")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Object test() {
//        List<Object[]> queryList = em.createQuery("SELECT e.resid.resid,e.uHour FROM ElecUsage AS e",
//                          Object[].class).getResultList();
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//        for (Object[] row : queryList) {
//            JsonObject usageObject = Json.createObjectBuilder().
//                    add("resid", (Integer) row[0])
//                    .add("uHour", (Integer) row[1]).build();
//            arrayBuilder.add(usageObject);
//        }
//        JsonArray jArray = arrayBuilder.build();
//        return jArray;
//    }
//    
//    @GET
//    @Path("test2/{resid}/{uHour}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Object test2(@PathParam("resid") Integer resid,@PathParam("uHour") Integer uHour) {
//        List<Object[]> queryList = em.createQuery("SELECT e.resid.resid,e.uHour FROM ElecUsage e WHERE e.resid.resid = :resid AND e.uHour=:uHour",
//                          Object[].class).setParameter("resid", resid).setParameter("uHour",uHour).getResultList();
//        
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//        for (Object[] row : queryList) {
//            JsonObject usageObject = Json.createObjectBuilder().
//                    add("resid", (Integer) row[0])
//                    .add("uHour", (Integer) row[1]).build();
//            arrayBuilder.add(usageObject);
//        }
//        JsonArray jArray = arrayBuilder.build();
//        return jArray;
//    }
//    
//    @GET
//    @Path("test3/{resid}/{uDate}/{uHour}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Object test3(@PathParam("resid") Integer resid,@PathParam("uDate") String uDate, @PathParam("uHour") Integer uHour) {
//        
//        List<Object[]> queryList = em.createQuery("SELECT e.resid.resid,e.uDate,e.uHour FROM ElecUsage e WHERE e.resid.resid = :resid AND e.uDate = :uDate AND e.uHour=:uHour",
//                          Object[].class).setParameter("resid", resid).setParameter("uDate",uDate).setParameter("uHour",uHour).getResultList();
//    
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//        for (Object[] row : queryList) {
//            JsonObject usageObject = Json.createObjectBuilder().
//                    add("resid", (Integer) row[0])
//                    .add("uDate", (String) row[1])
//                    .add("uHour", (Integer) row[2]).build();
//            arrayBuilder.add(usageObject);
//        }
//        JsonArray jArray = arrayBuilder.build();
//        return jArray;
//    }
    
    @GET
    @Path("HourlyUsage1Appliance/{resid}/{uDate}/{uHour}/{appliance}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object HourlyUsage1Appliance(@PathParam("resid") Integer resid,@PathParam("uDate") String uDate, @PathParam("uHour") Integer uHour,@PathParam("appliance") String appliance) throws ParseException {
        
        List<Object[]> queryList = new ArrayList<>();
        
        if(appliance.equals("fridge")){
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
            Date date = formatter.parse(uDate);
            queryList = em.createQuery("SELECT e.resid.resid,e.uDate,e.uHour,e.fridgeUsage FROM ElecUsage e WHERE e.resid.resid = :resid AND e.uDate = :uDate AND e.uHour=:uHour",
                    Object[].class).setParameter("resid", resid).setParameter("uDate", date).setParameter("uHour", uHour).getResultList();
            
            
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object[] row : queryList) {
                row[1] = formatter.format(row[1]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("uDate", (String) row[1])
                        .add("uHour", (Integer) row[2])
                        .add("fridge", (Double) row[3]).build();
                arrayBuilder.add(usageObject);
            }
            JsonArray jArray = arrayBuilder.build();
            return jArray;
            
        } 
        
        if(appliance.equals("airconditioner")){
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
            Date date = formatter.parse(uDate);
            queryList = em.createQuery("SELECT e.resid.resid,e.uDate,e.uHour,e.airconUsage FROM ElecUsage e WHERE e.resid.resid = :resid AND e.uDate = :uDate AND e.uHour=:uHour",
                    Object[].class).setParameter("resid", resid).setParameter("uDate", date).setParameter("uHour", uHour).getResultList();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object[] row : queryList) {
                row[1] = formatter.format(row[1]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("uDate", (String) row[1])
                        .add("uHour", (Integer) row[2])
                        .add("airconditioner", (Double) row[3]).build();
                arrayBuilder.add(usageObject);
            }
            JsonArray jArray = arrayBuilder.build();
            return jArray;
            
        } 
        
        if(appliance.equals("washmachine")){
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
            Date date = formatter.parse(uDate);
            queryList = em.createQuery("SELECT e.resid.resid,e.uDate,e.uHour,e.washmaUsage FROM ElecUsage e WHERE e.resid.resid = :resid AND e.uDate = :uDate AND e.uHour=:uHour",
                    Object[].class).setParameter("resid", resid).setParameter("uDate", date).setParameter("uHour", uHour).getResultList();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object[] row : queryList) {
                row[1] = formatter.format(row[1]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("uDate", (String) row[1])
                        .add("uHour", (Integer) row[2])
                        .add("washmachiner", (Double) row[3]).build();
                arrayBuilder.add(usageObject);
            }
            JsonArray jArray = arrayBuilder.build();
            return jArray;
            
        } 
        
        return queryList ;
    
       
    }

   
    @GET
    @Path("HourlyUsage3Appliance/{resid}/{uDate}/{uHour}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object HourlyUsage3Appliance(@PathParam("resid") Integer resid,@PathParam("uDate") String uDate, @PathParam("uHour") Integer uHour) throws ParseException {
  
           SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
           Date date = formatter.parse(uDate); 
           List<Object[]>  queryList = em.createQuery("SELECT e.resid.resid,e.uDate,e.uHour,e.fridgeUsage + e.airconUsage + e.washmaUsage as totalUsage FROM ElecUsage e WHERE e.resid.resid = :resid AND e.uDate = :uDate AND e.uHour=:uHour",
                    Object[].class).setParameter("resid", resid).setParameter("uDate", date).setParameter("uHour", uHour).getResultList();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object[] row : queryList) {
                row[1] = formatter.format(row[1]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("uDate", (String) row[1])
                        .add("uHour", (Integer) row[2])
                        .add("totalUsage", (Double) row[3]).build();
                arrayBuilder.add(usageObject);
            }
            JsonArray jArray = arrayBuilder.build();
            return jArray;
            
        } 
    
    @GET
    @Path("HourlyUsageAll/{uDate}/{uHour}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object HourlyUsageAll(@PathParam("uDate") String uDate, @PathParam("uHour") Integer uHour) throws ParseException {
  
           SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
           Date date = formatter.parse(uDate); 
           List<Object[]>  queryList = em.createQuery("SELECT e.uDate,e.uHour,SUM(e.fridgeUsage) + SUM(e.airconUsage) + SUM(e.washmaUsage) as totalUsage "
                   + "FROM ElecUsage e WHERE e.uDate = :uDate AND e.uHour=:uHour GROUP BY e.uDate,e.uHour",
                    Object[].class).setParameter("uDate", date).setParameter("uHour", uHour).getResultList();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object[] row : queryList) {               
                row[0] = formatter.format(row[0]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("uDate", (String) row[0])
                        .add("uHour", (Integer) row[1])
                        .add("totalUsageAllRes", (Double) row[2]).build();
                arrayBuilder.add(usageObject);
            }
            
            
           List<Object[]>  queryList2 = em.createQuery("SELECT e.resid.resid,e.resid.address,e.resid.postcode,e.uDate,e.uHour,e.fridgeUsage + e.airconUsage + e.washmaUsage as totalPerRes FROM ElecUsage e "
                   + "WHERE e.uDate = :uDate AND e.uHour=:uHour",
                    Object[].class).setParameter("uDate", date).setParameter("uHour", uHour).getResultList();
            
            for (Object[] row : queryList2) {
                row[3] = formatter.format(row[3]);
                JsonObject usageObject2 = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("address", (String) row[1])
                        .add("postcode", (String) row[2])
                        .add("uDate", (String) row[3])
                        .add("uHour", (Integer) row[4])
                        .add("totalPerRes", (Double) row[5]).build();
                arrayBuilder.add(usageObject2);
            }
            JsonArray jArray = arrayBuilder.build();
                      
            return jArray;
            
        } 
    
    
    
     @GET
    @Path("highestHourlyPower/{resid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object highestHourlyPower(@PathParam("resid") Integer resid) {
           SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
           List<Object[]>  queryList = em.createQuery("SELECT e.resid.resid,e.uDate,e.uHour,e.fridgeUsage + e.airconUsage + e.washmaUsage as totalUsage FROM ElecUsage e,"
                   + "(SELECT e.resid.resid,MAX(e.fridgeUsage + e.airconUsage + e.washmaUsage) as totalUsage FROM ElecUsage e GROUP BY e.resid.resid) x"
                   + " WHERE e.resid.resid = :resid AND totalUsage = x.totalUsage ",
                    Object[].class).setParameter("resid", resid).getResultList();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Object[] row : queryList) {
                row[1] = formatter.format(row[1]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("uDate", (String) row[1])
                        .add("uHour", (Integer) row[2])
                        .add("HighestTotalUsage", (Double) row[3]).build();
                arrayBuilder.add(usageObject);
                
            }
 
            JsonArray jArray = arrayBuilder.build();
            
            return jArray;
            
        } 
    
    @GET
    @Path("DailyUsageOfAppliance/{resid}/{uDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object DailyUsageOfAppliance(@PathParam("resid") Integer resid,@PathParam("uDate") String uDate) throws ParseException {
  
           SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
           Date date = formatter.parse(uDate);   
           List<Object[]>  queryList = em.createQuery("SELECT e.resid.resid,e.uDate,SUM(e.fridgeUsage) as fridgeUsage,SUM(e.airconUsage) as airconUsage,SUM(e.washmaUsage) as washmaUsage "
                   + "FROM ElecUsage e WHERE e.resid.resid = :resid AND e.uDate = :uDate GROUP BY e.resid.resid,e.uDate",
                    Object[].class).setParameter("resid", resid).setParameter("uDate", date).getResultList();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object[] row : queryList) {
                row[1] = formatter.format(row[1]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("uDate", (String) row[1])
                        .add("fridgeUsage", (Double) row[2])
                        .add("airconUsage", (Double) row[3])
                        .add("washmaUsage", (Double) row[4]).build();
                arrayBuilder.add(usageObject);
            }
              
            JsonArray jArray = arrayBuilder.build();
                      
            return jArray;
            
        }

     @GET
    @Path("dailyHourlyUsage/{resid}/{uDate}/{view}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Object dailyHourlyUsage(@PathParam("resid") Integer resid,@PathParam("uDate") String uDate,@PathParam("view") String view) throws ParseException {
        
        List<Object[]> queryList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        Date date = formatter.parse(uDate); 
        if(view.equals("daily")){ 
            
            queryList = em.createQuery("SELECT e.resid.resid,e.uDate,SUM(e.fridgeUsage + e.airconUsage + e.washmaUsage) as totalUsage,(SUM(e.temperature)/24) as temperature FROM ElecUsage e "
                    + "WHERE e.resid.resid = :resid AND e.uDate = :uDate GROUP BY e.resid.resid,e.uDate",
                    Object[].class).setParameter("resid", resid).setParameter("uDate", date).getResultList();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object[] row : queryList) {
                row[1] = formatter.format(row[1]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("uDate", (String) row[1])
                        .add("totalUsage", (Double) row[2])
                        .add("temperature", (Long) row[3]).build();
                arrayBuilder.add(usageObject);
            }
            JsonArray jArray = arrayBuilder.build();
            return jArray;
            
        } 
        
        if(view.equals("hourly")){
            
            queryList = em.createQuery("SELECT e.resid.resid,e.uDate,e.uHour,e.fridgeUsage + e.airconUsage + e.washmaUsage as totalUsage,e.temperature FROM ElecUsage e WHERE e.resid.resid = :resid AND e.uDate = :uDate",
                    Object[].class).setParameter("resid", resid).setParameter("uDate", date).getResultList();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object[] row : queryList) {
                row[1] = formatter.format(row[1]);
                JsonObject usageObject = Json.createObjectBuilder().
                        add("resid", (Integer) row[0])
                        .add("uDate", (String) row[1])
                        .add("uHour", (Integer) row[2])
                        .add("totalUsage", (Double) row[3])
                        .add("temperature", (Integer) row[4]).build();
                arrayBuilder.add(usageObject);
            }
            JsonArray jArray = arrayBuilder.build();
            return jArray;
            
        } 
        
        
        
        return queryList ;
    
       
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ElecUsage> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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
