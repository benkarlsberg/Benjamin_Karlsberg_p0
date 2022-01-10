public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public void resetPassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Your password has been successfully changed");
    }

    public void createAccount() {

    }
}
