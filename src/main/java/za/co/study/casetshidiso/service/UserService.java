package za.co.study.casetshidiso.service;

import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.core.Response;
import za.co.study.casetshidiso.demoing.domain.model.user.User;

public interface UserService {

    Response findAllUsers();
    Response findUser(long id);
    Response createUser(User user);
    Response updateUser(User user);
    Response deleteUser(long id);
    boolean userExists(@NotEmpty(message = "Name must be provided") String name,
                       @NotEmpty(message = "Email Address must be provided") String emailAddress);
}
