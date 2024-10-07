package za.co.study.casetshidiso.demoing;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.study.casetshidiso.demoing.domain.model.user.User;
import za.co.study.casetshidiso.demoing.domain.model.user.UserRepository;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

@Path("/users")
public class UserResource {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Inject
    private UserRepository userRepository;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(@PathParam("id") Long id) {
        logger.info("Get User by id: " + id);
        return userRepository.findUser(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAllUsers() {
        logger.info("Get all users");
        return userRepository.findAllUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        logger.info("Create user: " + user.getName());
        try{
            return userRepository.createUser(user);
        }catch(PersistenceException pe){
            logger.info("Error creating user: " + user.getName());
            logger.info("Error message " + pe.getMessage());
            throw new WebApplicationException(Response.Status.BAD_GATEWAY);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User user) {
        logger.info("Update user: " + user.getName());
        try{
            return userRepository.updateUser(user);
        }catch(Exception pe){
            logger.info("Error updating user " + user.getName());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteUserById(@PathParam("id") Long id) {
        logger.info("Delete user by id: " + id);
        try {
            userRepository.deleteUser(id);
        }catch (PersistenceException pe){
            logger.info("Error deleting user: " + id);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
