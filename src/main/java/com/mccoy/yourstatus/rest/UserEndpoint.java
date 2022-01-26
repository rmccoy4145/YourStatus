package com.mccoy.yourstatus.rest;

import javax.ws.rs.*;

@Path("/user")
public class UserEndpoint {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "User Endpoint!";
    }
}