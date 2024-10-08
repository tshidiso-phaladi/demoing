package za.co.study.casetshidiso.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.study.casetshidiso.demoing.domain.model.ErrorResponse;
import za.co.study.casetshidiso.demoing.domain.model.user.User;
import za.co.study.casetshidiso.demoing.domain.model.user.UserRepository;
import za.co.study.casetshidiso.demoing.exceptions.UserNotFoundException;
import za.co.study.casetshidiso.demoing.exceptions.UserWithEmailAlreadyExistsException;
import za.co.study.casetshidiso.service.UserService;

import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private static final String ERROR_CODE_USER_NOT_FOUND = "9997";
    private static final String ERROR_MESSAGE_USER_NOT_FOUND = "User not found";
    private static final String ERROR_CODE_USER_WITH_EMAIL_EXISTS = "9996";
    private static final String ERROR_MESSAGE_USER_WITH_EMAIL_EXISTS = "User with email already exists";

    @Inject
    private UserRepository userRepository;


    @Override
    public Response findAllUsers() {
        List<User> allUsers = userRepository.findAllUsers();
        return Response.ok(allUsers).build();
    }

    @Override
    public Response findUser(long id) {
        User user;
        try {
            user = userRepository.findUser(id);
        } catch (UserNotFoundException e) {
            return userNotFoundResponse();
        }
        return Response.ok(user).build();
    }

    @Override
    public Response createUser(User user) {
        User createdUser;
        try {
            createdUser = userRepository.createUser(user);
        }catch (UserWithEmailAlreadyExistsException ueae){
            ErrorResponse errorResponse =
                    new ErrorResponse(ERROR_CODE_USER_WITH_EMAIL_EXISTS, ERROR_MESSAGE_USER_WITH_EMAIL_EXISTS);

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.ok(createdUser).build();
    }

    @Override
    public Response updateUser(User user) {
        User modifiedUser;
        try {
            modifiedUser = userRepository.updateUser(user);
        }catch (UserNotFoundException ueae){
            return userNotFoundResponse();
        } catch (UserWithEmailAlreadyExistsException e) {
            ErrorResponse errorResponse =
                    new ErrorResponse(ERROR_CODE_USER_WITH_EMAIL_EXISTS, ERROR_MESSAGE_USER_WITH_EMAIL_EXISTS);

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response.ok(modifiedUser).build();
    }

    @Override
    public Response deleteUser(long id) {
        try {
            userRepository.deleteUser(id);
        }catch (UserNotFoundException ueae){
            return userNotFoundResponse();
        }
        return Response.ok().build();
    }

    @Override
    public boolean userExists(String name, String emailAddress) {
        try {
            return userRepository.userExists(name, emailAddress);
        } catch (UserNotFoundException e) {
            return false;
        }
    }

    private static Response userNotFoundResponse() {
        ErrorResponse errorResponse =
                new ErrorResponse(ERROR_CODE_USER_NOT_FOUND, ERROR_MESSAGE_USER_NOT_FOUND);

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
