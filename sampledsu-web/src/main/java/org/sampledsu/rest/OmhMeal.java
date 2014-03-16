package org.sampledsu.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.sampledsu.common.dao.mongo.MealService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
@Path("/omh/v1/omh:sampledsu:meal/1")
@Produces(MediaType.APPLICATION_JSON)
public class OmhMeal {
	private static Logger logger = LoggerFactory.getLogger( OmhMeal.class );
	
	@Autowired
    private MealService mealService;
	
	@GET
	@Path( "/data" )
	public List<ConcordiaData<ConcordiaMeta,Meal>> get() {
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
