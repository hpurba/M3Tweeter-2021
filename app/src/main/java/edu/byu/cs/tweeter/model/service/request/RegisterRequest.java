package edu.byu.cs.tweeter.model.service.request;

public class RegisterRequest {

    private final String firstName;
    private final String lastName;
    private final String alias;
    private final String password;
    public byte[] byteArray;


    public RegisterRequest(String firstName, String lastName, String alias, String password, byte[] byteArray) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.password = password;
        this.byteArray = byteArray;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the username of the user to be logged in by this request.
     *
     * @return the username.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Returns the password of the user to be logged in by this request.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    public byte[] getByteArray() {
        return byteArray;
    }
}
