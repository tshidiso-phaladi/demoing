package za.co.study.casetshidiso.demoing.persistence.jpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import za.co.study.casetshidiso.demoing.domain.model.user.User;
import za.co.study.casetshidiso.demoing.domain.model.user.UserRepository;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class JpaUserRepository implements UserRepository {
    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext/*(unitName = "user")*/
    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        logger.info("Getting all users");
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public User findUser(Long id) {
        logger.info("Getting user by id: " + id);
        return entityManager.find(User.class, id);
    }

    @Override
    public User createUser(User user) {
        logger.info("Creating user: " + user);
        //validation check if email is provided
        entityManager.persist(user);

        return user;
    }

    @Override
    public User updateUser(User user) {
        logger.info("Updating user: " + user);
        User updatedUser = entityManager.find(User.class, user.getId());
        if (updatedUser != null) {
            return entityManager.merge(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        logger.info("Deleting user: " + id);
        User deletedUser = entityManager.find(User.class, id);
        if (deletedUser != null) {
            entityManager.remove(deletedUser);
        }
    }
}
