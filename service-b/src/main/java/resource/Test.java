package resource;


import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Resource
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class Test {

    @GET
    @Path("/test")
    public Response test() {

        log.info("Hello world from ServiceB!");
        return Response.ok("Hello world from ServiceB!").build();
    }
}
