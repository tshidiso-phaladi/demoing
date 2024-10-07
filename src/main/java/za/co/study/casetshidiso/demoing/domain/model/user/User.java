package za.co.study.casetshidiso.demoing.domain.model.user;


import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "shop")
@NamedQuery(name = "User.byEmail", query = "SELECT u FROM User u WHERE u.emailAddress= ?1")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String emailAddress;

    public User() {
    }

    public User(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
