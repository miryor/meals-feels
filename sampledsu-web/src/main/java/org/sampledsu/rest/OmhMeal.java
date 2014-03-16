package org.sampledsu.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.sampledsu.common.dao.mongo.MealService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sampledsu.common.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST Component to return our Meal information in DSU
 * @author royrim
 *
 */
@Component
@Path("/omh/v1/omh:sampledsu:meal")
@Produces(MediaType.APPLICATION_JSON)
public class OmhMeal {
	private static Logger logger = LoggerFactory.getLogger( OmhMeal.class );
	
	@Autowired
    private MealService mealService;
	
    @GET
    public int[] getMealVersions() {
    	return new int[] { 1 };
    }
    
    @GET
    @Path( "/{version: [0-9]+}" )
    public Concordia getMealConcordia( @PathParam("version") int version ) {
    	return new Concordia( Concordia.TYPE_OBJECT, "describes a meal", new ConcordiaField[] {
    		new ConcordiaField( "user", Concordia.TYPE_STRING, false, "name of user" )	,
    		new ConcordiaField( "entryDate", Concordia.TYPE_STRING, false, "date of entry" ) ,
    		new ConcordiaField( "mealType", Concordia.TYPE_STRING, false, "type of meal" ) ,
    		new ConcordiaField( "mealName", Concordia.TYPE_STRING, false, "name of meal" ) ,
    		new ConcordiaField( "containsGluten", Concordia.TYPE_STRING, false, "contains gluten" ) ,
    		new ConcordiaField( "brand", Concordia.TYPE_STRING, false, "brand" )
    	});
    }
    
	@GET
	@Path( "/{version: [0-9]+}/data" )
	public List<ConcordiaData<ConcordiaMeta,Meal>> get( @PathParam("version") int version ) {
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ" );
		String name = SecurityContextHolder.getContext().getAuthentication().getName(); 
		List<Meal> meals = mealService.userMealList( name );
		List<ConcordiaData<ConcordiaMeta,Meal>> list = new ArrayList<ConcordiaData<ConcordiaMeta,Meal>>();
		for ( Meal meal : meals ) {
			list.add( new ConcordiaData<ConcordiaMeta,Meal>( 
					new ConcordiaMeta( meal.getId(), format.format( meal.getEntryDate().toDate() ) ),
					meal
					)
			);
		}
		return list;
	}
}
