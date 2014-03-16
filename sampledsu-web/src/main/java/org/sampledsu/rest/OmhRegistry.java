package org.sampledsu.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sampledsu.common.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * REST calls to return our DSU registry.
 * Would be better to use reflection to auto-generate the ConcordiaFields.
 * Would also be nice to figure out a strategy for versioning.
 * @author royrim
 *
 */
@Component
@Path("/omh/v1")
@Produces(MediaType.APPLICATION_JSON)
public class OmhRegistry {

    @GET
    public String[] get() {
        return new String[] {
        	"omh:sampledsu:meal",
        	"omh:sampledsu:pain"
        };
    }

}
