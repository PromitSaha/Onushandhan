package group10.onushandhan;

/**
 * Created by Promit on 9/26/2017.
 */

public class User {
    String userId, userName, userEmail, userPassword, userContactNumber, userAge, userGender;

    public User(){

    }

    public User(String userId, String userName, String userEmail, String userPassword, String userContactNumber, String userAge, String userGender) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userContactNumber = userContactNumber;
        this.userAge = userAge;
        this.userGender = userGender;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserContactNumber() {
        return userContactNumber;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserGender() {
        return userGender;
    }
}
