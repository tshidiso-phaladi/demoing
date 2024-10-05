package za.co.study.casetshidiso.demoing.repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

import za.co.study.casetshidiso.demoing.model.entity.User;

@Stateless
public class UserRepository {
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    //revert if not working
    @PersistenceContext/*(unitName = "user")*/
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        LOG.info("Getting all users");
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    public User getUserById(Long id) {
        LOG.info("Getting user by id: " + id);
        return entityManager.find(User.class, id);
    }

    public User createUser(User user) {
        LOG.info("Creating user: " + user);
        //validation check if email is provided
        entityManager.persist(user);

        return user;
    }

    public User updateUser(User user) {
        LOG.info("Updating user: " + user);
        User updatedUser = entityManager.find(User.class, user.getId());
        if (updatedUser != null) {
            return entityManager.merge(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        LOG.info("Deleting user: " + id);
        User deletedUser = entityManager.find(User.class, id);
        if (deletedUser != null) {
            entityManager.remove(deletedUser);
        }
    }
}
