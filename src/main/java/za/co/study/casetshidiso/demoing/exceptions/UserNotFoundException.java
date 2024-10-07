package za.co.study.casetshidiso.demoing.exceptions;

public class UserNotFoundException extends Exception {
    private long userid;

    public UserNotFoundException(long userid) {
        this.userid = userid;
    }

    @Override
    public String getMessage() {
        return "User with id " + userid + " not found";
    }
}
