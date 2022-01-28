package com.mccoy.yourstatus.rest;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.service.UserService;
import com.mccoy.yourstatus.service.UserStatusMessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/UserStatusMessage")
public class UserStatusMessageEndpoint {

    @Inject
    UserStatusMessageService usmService;

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/MainFeed")
    public Response getMainFeedFor(@QueryParam("userId") Long userId) {
        User user = userService.getUser(userId);
        if(user == null) return Response.serverError().entity("Unknown User").build();
        List<UserStatusMessage> messages = usmService.getMainMessageFeedFor(user);
        return Response.ok().entity(messages).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserSpecificFeed(@QueryParam("userId") Long userId) {
        User user = userService.getUser(userId);
        if(user == null) return Response.serverError().entity("Unknown User").build();
        List<UserStatusMessage> messages = usmService.getUserMessageFeed(user);
        return Response.ok().entity(messages).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserSpecificFeed(UserStatusMessage message) {
        usmService.addMessage(message);
        return Response.ok().entity("Status Message Added!").build();
    }


}