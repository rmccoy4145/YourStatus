package com.mccoy.yourstatus.rest;

import javax.ws.rs.*;

@Path("/status")
public class StatusEndpoint {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Status Endpoint!";
    }
}