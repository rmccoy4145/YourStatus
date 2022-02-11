package com.mccoy.yourstatus.web.rest;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.web.util.ServiceUtil;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/UserStatusMessage")
public class UserStatusMessageEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/MainFeed")
    public Response getMainFeedFor(@QueryParam("userId") Long userId) {
        try {
        User user = ServiceUtil.getUserService().get(userId);
        List<UserStatusMessage> messages = ServiceUtil.getUserStatusMessageService().getMainMessageFeedFor(user);
        return Response.ok().entity(messages).build();
        } catch (
        EntityNotFoundException ex) {
            return Response.serverError().entity("Invalid User").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserSpecificFeed(@QueryParam("userId") Long userId) {
        try {
        User user = ServiceUtil.getUserService().get(userId);
        List<UserStatusMessage> messages = ServiceUtil.getUserStatusMessageService().getAllByUser(user);
        return Response.ok().entity(messages).build();
        } catch (
                EntityNotFoundException ex) {
            return Response.serverError().entity("Invalid User").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserSpecificFeed(UserStatusMessage message) {
        ServiceUtil.getUserStatusMessageService().add(message);
        return Response.ok().entity("Status Message Added!").build();
    }


}
