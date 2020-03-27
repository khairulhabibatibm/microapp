package dev.appsody.starter;

import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("/heavy")
public class HeavyController{

    @Path("/retry")
    @GET
    @Timeout(500)
    @Retry(delay = 100,maxRetries = 5)
    @Fallback(fallbackMethod = "retryFallback")
    public String retryHeavy(){
        try {
            System.out.println("Inside retry heavy..");
            Thread.sleep(700L);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return "This is very heavy";
    }

    public String retryFallback(){
        return "This is fallback from heavy retries";
    }

    @Path("/bulkhead")
    @GET
    @Timeout(400)
    @Bulkhead(value = 2,waitingTaskQueue = 2)
    public String busyService(){
        try {
            Thread.sleep(300L);
        } catch (Exception e) {
            //TODO: handle exception
        }
        return "I'm too busy, only 2 calls allowed at a time!";
    }

    @Path("/cb")
    @GET
    @CircuitBreaker(requestVolumeThreshold = 2, failureRatio = 0.1, delay = 10000, successThreshold = 10)
    @Timeout(500)
    public String intermittenService(){
        Random rd = new Random();
        int flag = rd.nextInt(3) + 1;
        if(flag % 2==0){
            try {
                Thread.sleep(700L);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
        return "Sometimes i work, sometimes i don't";
    }

    public String cbfallback(){
        return "circuit now closed";
    }



}