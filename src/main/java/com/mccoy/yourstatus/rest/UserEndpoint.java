package com.mccoy.yourstatus.rest;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/User")
public class UserEndpoint {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public Response getUser(@PathParam("userId") Long userId) {
        User user = userService.getUser(userId);
        return Response.ok().entity(user).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        userService.addUser(user);
        return Response.ok().entity("User added!").build();
    }

}