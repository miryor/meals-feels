package org.sampledsu.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.sampledsu.common.dao.mongo.PainService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sampledsu.common.models.Concordia;
import org.sampledsu.common.models.ConcordiaData;
import org.sampledsu.common.models.ConcordiaField;
import org.sampledsu.common.models.ConcordiaMeta;
import org.sampledsu.common.models.Pain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST Component to return our Pain information in DSU
 * @author royrim
 *
 */
@Component
@Path("/omh/v1/omh:sampledsu:pain")
@Produces(MediaType.APPLICATION_JSON)
public class OmhPain {
	private static Logger logger = LoggerFactory.getLogger( OmhPain.class );
	
	@Autowired
    private PainService painService;
	
    
    @GET
    public int[] getPainVersions() {
    	return new int[] { 1 };
    }
    
    @GET
    @Path( "/{version: [0-9]+}" )
    public Concordia getPainConcordia( @PathParam("version") int version ) {
    	return new Concordia( Concordia.TYPE_OBJECT, "describes a pain entry", new ConcordiaField[] {
    		new ConcordiaField( "user", Concordia.TYPE_STRING, false, "name of user" )	,
    		new ConcordiaField( "entryDate", Concordia.TYPE_STRING, false, "date of entry" ) ,
    		new ConcordiaField( "gas", Concordia.TYPE_STRING, false, "amount of gas pain" ) ,
    		new ConcordiaField( "bloating", Concordia.TYPE_STRING, false, "amount of bloating" ) ,
    		new ConcordiaField( "abdominal", Concordia.TYPE_STRING, false, "amount of abdominal pain" ) ,
    		new ConcordiaField( "burning", Concordia.TYPE_STRING, false, "amount of burning pain" ) ,
    		new ConcordiaField( "headache", Concordia.TYPE_STRING, false, "amount of headache" ) ,
    		new ConcordiaField( "lethargy", Concordia.TYPE_STRING, false, "amount of lethargy" ) ,
    		new ConcordiaField( "joints", Concordia.TYPE_STRING, false, "amount of joint pain" )
    	});
    }
    
	@GET
	@Path( "/{version: [0-9]+}/data" )
	public List<ConcordiaData<ConcordiaMeta,Pain>> get( @PathParam("version") int version ) {
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ" );
		String name = SecurityContextHolder.getContext().getAuthentication().getName(); 
		List<Pain> painList = painService.userPainList( name );
		List<ConcordiaData<ConcordiaMeta,Pain>> list = new ArrayList<ConcordiaData<ConcordiaMeta,Pain>>();
		for ( Pain pain : painList ) {
			list.add( new ConcordiaData<ConcordiaMeta,Pain>( 
					new ConcordiaMeta( pain.getId(), format.format( pain.getEntryDate().toDate() ) ),
					pain
					)
			);
		}
		return list;
	}
}
