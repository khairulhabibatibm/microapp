package dev.appsody.starter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/call")
public class GreetingController{

    @Inject
    XServiceClient serviceClient;

    @GET
    public String greeting(){
        Greeting g = serviceClient.greet();
        return g.getName() + " from " +  g.getCountry();
    }
}