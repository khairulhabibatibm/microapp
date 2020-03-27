package dev.appsody.starter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://159.122.187.155:31150")
public interface XServiceClient{

    @GET
    @Path("/greet")
    Greeting greet();
}