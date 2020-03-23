package dev.appsody.starter;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
@Singleton
public class HelloController{

    @GET
    public String hello(){
        return "Hello World Micro Profile";
    }
}