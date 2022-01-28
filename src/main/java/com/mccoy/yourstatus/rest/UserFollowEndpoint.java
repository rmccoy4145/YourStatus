package com.mccoy.yourstatus.rest;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.service.UserFollowService;
import com.mccoy.yourstatus.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/UserFollow")
public class UserFollowEndpoint {

    @Inject
    UserService userService;

    @Inject
    UserFollowService userFollowService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/followedBy")
    public Response getFollowing(@QueryParam("userId") Long userId) {
        User user = userService.getUser(userId);
        if(user == null) return Response.serverError().entity("Unknown User").build();
        List<UserFollow> following = userFollowService.getFollowedBy(user);
        return Response.ok().entity(following).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/followers")
    public Response getFollowers(@QueryParam("userId") Long userId) {
        User user = userService.getUser(userId);
        if(user == null) return Response.serverError().entity("Unknown User").build();
        List<UserFollow> following = userFollowService.getFollowersOf(user);
        return Response.ok().entity(following).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFollow(@QueryParam("userId") Long userId, @QueryParam("followUserId") Long followUserId) {
        User user = userService.getUser(userId);
        if(user == null) return Response.serverError().entity("Unknown User").build();
        User followUser = userService.getUser(followUserId);
        if(followUser == null) return Response.serverError().entity("Unknown User").build();
        userFollowService.addFollower(user, followUser);
        return Response.ok().entity("Follow added!").build();
    }
}