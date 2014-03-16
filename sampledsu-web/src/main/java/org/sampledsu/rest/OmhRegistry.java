package org.sampledsu.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sampledsu.common.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/omh/v1")
@Produces(MediaType.APPLICATION_JSON)
public class OmhRegistry {

    @GET
    public String[] get() {
        return new String[] {
        	"omh:sampledsu:meal"	
        };
    }
    
    @GET
    @Path( "/omh:sampledsu:meal" )
    public int[] getMealVersions() {
    	return new int[] { 1 };
    }
    
    @GET
    @Path( "/omh:sampledsu:meal/1" )
    public Concordia getMealConcordia() {
    	return new Concordia( Concordia.TYPE_OBJECT, "describes a meal", new ConcordiaField[] {
    		new ConcordiaField( "user", Concordia.TYPE_STRING, false, "name of user" )	,
    		new ConcordiaField( "entryDate", Concordia.TYPE_STRING, false, "date of entry" ) ,
    		new ConcordiaField( "mealType", Concordia.TYPE_STRING, false, "type of meal" ) ,
    		new ConcordiaField( "mealName", Concordia.TYPE_STRING, false, "name of meal" ) ,
    		new ConcordiaField( "containsGluten", Concordia.TYPE_STRING, false, "contains gluten" ) ,
    		new ConcordiaField( "brand", Concordia.TYPE_STRING, false, "brand" )
    	});
    }

}
