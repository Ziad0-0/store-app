package nubahome.databse;

public class User {
    private String name;
    private String password;
    private int type;


    User(String name, String password, int type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getType() {
        return type;
    }
}
