package za.co.study.casetshidiso.service;

import jakarta.ws.rs.core.Response;
import za.co.study.casetshidiso.demoing.domain.model.user.User;

public interface UserService {

    Response findAllUsers();
    Response findUser(long id);
    Response createUser(User user);
    Response updateUser(User user);
    Response deleteUser(long id);
}
