package nubahome.login;

public class User {

    private String name;
    private String hashedPassword;
    private int priviliges;

    User(String name, String hashedPassword, int priviliges) {

        this.name = name;
        this.hashedPassword = hashedPassword;
        this.priviliges = priviliges;
    }

    public int getPriviliges() {
        return priviliges;
    }
}
