package za.co.study.casetshidiso.demoing.domain.model.user;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();

    User findUser(Long id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);
}
