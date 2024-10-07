package za.co.study.casetshidiso.demoing.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.study.casetshidiso.demoing.domain.model.user.User;
import za.co.study.casetshidiso.service.UserService;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

@Path("/users")
public class UserResource {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Inject
    private UserService userService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUser(@PathParam("id") long id) {
        logger.info("Get User by id: " + id);
        return userService.findUser(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUsers() {
        logger.info("Get all users");
        return userService.findAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        logger.info("Create user: " + user.getName());
        return userService.createUser(user);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        logger.info("Update user: " + user.getName());
        return userService.updateUser(user);
    }

    @DELETE
    @Path("{id}")
    public Response deleteUserById(@PathParam("id") long id) {
        logger.info("Delete user by id: " + id);
        return userService.deleteUser(id);
    }
}
