package nubahome.databse;

public class User {
    int userID;
    String userName;
    String userPassword;
    UserRole userRole;

    public User(String userName, String userPassword, UserRole userRole) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    public User(int userID, String userName, String userPassword, UserRole userRole) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
