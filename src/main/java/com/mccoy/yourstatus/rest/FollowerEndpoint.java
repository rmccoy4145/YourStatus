package com.mccoy.yourstatus.rest;

import javax.ws.rs.*;

@Path("/follower")
public class FollowerEndpoint {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Followers endpoint!";
    }
}