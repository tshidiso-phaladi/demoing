package za.co.study.casetshidiso.demoing.exceptions;

public class UserNotFoundException extends Exception {
    private long userid;
    private String emailAddress;

    public UserNotFoundException(long userid) {
        this.userid = userid;
    }
    public UserNotFoundException(long userid, String emailAddress) {
        this.userid = userid;
        this.emailAddress = emailAddress;
    }

    public UserNotFoundException(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String getMessage() {
        return "User with id " + userid + " not found";
    }

}
