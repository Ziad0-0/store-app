package nubahome.databse;

public class UserRole {
    int roleID;
    String roleName;

    public UserRole(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getRoleName() {
        return roleName;
    }
}
