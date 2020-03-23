package dev.appsody.starter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/todo")
public class TodoController{

    @GET
    @Timeout(200L)
    @Fallback(fallbackMethod = "listLocal")
    public String list(){
        try{
            Thread.sleep(700L);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "This is todo list";
    }

    public String listLocal(){
        return "This is list from local";
    }
}