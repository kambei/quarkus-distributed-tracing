package externalservice;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RegisterRestClient(configKey = "service-b")
public interface ServiceB {

    @GET
    @Path("/test")
    String test();
}
