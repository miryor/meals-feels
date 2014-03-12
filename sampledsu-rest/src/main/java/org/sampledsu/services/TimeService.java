package org.sampledsu.services;

import org.sampledsu.common.models.Time;
import org.sampledsu.common.transaction.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/time")
@Produces(MediaType.APPLICATION_JSON)
public class TimeService {

	@Autowired
	TransactionBo transactionBo;

    @GET
    public Time get() {
        return new Time();
    }
    
    @GET
    @Path("/whatever")
    public Response whateverget() {
    	//String result = "whatever";
    	String result = transactionBo.save();
		return Response.status(200).entity(result).build();
    }

}
