package models;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    // No-Arg Constructor
    public User() {
    }

    // Full-Arg Constructor
    public User(int userId, String firstName, String lastName, String userName, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    //ID-less Constructor
    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() { return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void resetPassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Your password has been successfully changed");
    }

    @Override
    public String toString() {
        return "User{" +
                "User id: " + userId +
                ", First Name: '" + firstName + '\'' +
                ", Last Name: '" + lastName + '\'' +
                ", Username: '" + userName + '\'' +
                ", Password: '" + password + '\'' +
                '}';
    }
}
