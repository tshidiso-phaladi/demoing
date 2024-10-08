package za.co.study.casetshidiso.demoing.resource;

import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import za.co.study.casetshidiso.demoing.config.RequiresAuthentication;
import za.co.study.casetshidiso.demoing.domain.model.user.User;
import za.co.study.casetshidiso.service.AppStateService;
import za.co.study.casetshidiso.service.TokenGenerationService;
import za.co.study.casetshidiso.service.UserService;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

@Path("/users")
public class UserResource {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    public static final String BEARER_SPACE = "Bearer ";

    @Inject
    private UserService userService;

    @Inject
    private UriInfo uriInfo;

    @Inject
    private TokenGenerationService tokenGenerationService;

    @Inject
    private AppStateService appStateService;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("name") @NotEmpty(message = "Name must be provided") String name,
                          @FormParam("email") @NotEmpty(message = "Email Address must be provided") String emailAddress){

        if (!userService.userExists(name, emailAddress)){
            throw new SecurityException("User with email " + emailAddress + " and name " + name +" does not exist");
        }
        appStateService.setEmailAddress(emailAddress);
        String token = tokenGenerationService.generateToken(emailAddress, uriInfo);

        logger.info("JWT token: " + token);
        return Response.ok()
                .header(HttpHeaders.AUTHORIZATION, BEARER_SPACE + token).build();
    }

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
    @RequiresAuthentication
    public Response deleteUserById(@PathParam("id") long id) {
        logger.info("Delete user by id: " + id);
        return userService.deleteUser(id);
    }
}
