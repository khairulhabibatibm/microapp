package dev.appsody.starter;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/todo")
@Singleton
public class TodoController{

    @GET
    public String list(){
        return "This is todo list";
    }
}