package za.co.study.casetshidiso.demoing.domain.model.user;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import za.co.study.casetshidiso.demoing.exceptions.UserNotFoundException;
import za.co.study.casetshidiso.demoing.exceptions.UserWithEmailAlreadyExistsException;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserRepository {

    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAllUsers() {
        logger.info("Getting all users");
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    public User findUser(long id) throws UserNotFoundException {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        logger.info("Getting user by id: " + id);
        return entityManager.find(User.class, id);
    }

    public User createUser(User user) throws UserWithEmailAlreadyExistsException {
        logger.info("Creating user: " + user);
        List<User> usersWithSameEmail = getUsersWithSameEmail(user.getEmailAddress());

        if (!usersWithSameEmail.isEmpty()) {
            throw new UserWithEmailAlreadyExistsException(user.getEmailAddress());
        }
        entityManager.persist(user);

        return user;
    }

    public User updateUser(User user) throws UserNotFoundException, UserWithEmailAlreadyExistsException {
        User updatedUser = entityManager.find(User.class, user.getId());
        if (updatedUser == null) {
            throw new UserNotFoundException(user.getId());
        }

        List<User> usersWithSameEmail = getUsersWithSameEmail(user.getEmailAddress());
        if (!usersWithSameEmail.isEmpty()) {
            throw new UserWithEmailAlreadyExistsException(user.getEmailAddress());
        }

        logger.info("Updating user: " + user);
        return entityManager.merge(user);
    }

    public void deleteUser(long id) throws UserNotFoundException {
        User deletedUser = entityManager.find(User.class, id);
        if (deletedUser == null) {
            throw new UserNotFoundException(id);
        }
        logger.info("Deleting user: " + id);
        entityManager.remove(deletedUser);
    }

    private List<User> getUsersWithSameEmail(String emailAddress) {
        TypedQuery<User> userTypedQuery = entityManager.createNamedQuery("User.byEmail", User.class)
                .setParameter(1, emailAddress);
        return userTypedQuery.getResultList();
    }

    public boolean userExists(String name, String emailAddress) throws UserNotFoundException {
        List<User> usersWithSameEmail = getUsersWithSameEmail(emailAddress);
        if (!usersWithSameEmail.isEmpty() && usersWithSameEmail.get(0).getName().equals(name)) {
            //the user exists
            return true;
        }else{
            throw new UserNotFoundException(emailAddress);
        }
    }
}
