package com.mccoy.yourstatus.web.rest;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.service.Impl.UserFollowServiceImpl;
import com.mccoy.yourstatus.service.Impl.UserServiceImpl;
import com.mccoy.yourstatus.web.util.ServiceUtil;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/UserFollow")
public class UserFollowEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/followedBy")
    public Response getFollowing(@QueryParam("userId") Long userId) {
        try {
            User user = ServiceUtil.getUserService().get(userId);
            List<UserFollow> following = ServiceUtil.getUserFollowService().getFollowersOf(user);
            return Response.ok().entity(following).build();
        } catch (
            EntityNotFoundException ex) {
            return Response.serverError().entity("Invalid User").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/followers")
    public Response getFollowers(@QueryParam("userId") Long userId) {
        try {
            User user = ServiceUtil.getUserService().get(userId);
            List<UserFollow> following = ServiceUtil.getUserFollowService().getFollowersOf(user);
            return Response.ok().entity(following).build();
        } catch (
                EntityNotFoundException ex) {
            return Response.serverError().entity("Invalid User").build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFollow(@QueryParam("userId") Long userId, @QueryParam("followUserId") Long followUserId) {
        try {
            User user = ServiceUtil.getUserService().get(userId);
            User followUser = ServiceUtil.getUserService().get(followUserId);
            ServiceUtil.getUserFollowService().addFollower(user, followUser);
            return Response.ok().entity("Follow added!").build();
        } catch (EntityNotFoundException ex) {
            return Response.serverError().entity("Invalid User").build();
        }
    }

}
