package org.sampledsu.rest;

import org.sampledsu.common.models.Time;

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

    @GET
    public Time get() {
        return new Time();
    }

}
