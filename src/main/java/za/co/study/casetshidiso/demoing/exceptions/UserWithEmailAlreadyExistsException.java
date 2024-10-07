package za.co.study.casetshidiso.demoing.exceptions;

public class UserWithEmailAlreadyExistsException extends Exception{
    private String email;

    public UserWithEmailAlreadyExistsException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "User with email " + email + " already exists";
    }

}
