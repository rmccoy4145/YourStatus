package com.mccoy.yourstatus.rest;

import javax.ws.rs.*;

@Path("/")
public class RootEndpoint {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello! Welcome to the YourStatus API!";
    }
}