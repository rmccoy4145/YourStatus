package com.mccoy.yourstatus.web.rest;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.service.Impl.UserServiceImpl;
import com.mccoy.yourstatus.web.util.ServiceUtil;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/User")
public class UserEndpoint {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{userId}")
    public Response getUser(@PathParam("userId") Long userId) {
        try {
            User user = ServiceUtil.getUserService().get(userId);
            return Response.ok().entity(user).build();
        } catch (EntityNotFoundException ex) {
            return Response.serverError().entity("Invalid User").build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = ServiceUtil.getUserService().getAll();
        return Response.ok().entity(users).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        ServiceUtil.getUserService().add(user);
        return Response.ok().entity("User added!").build();
    }

}
