package nubahome.databse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class StoreDatabase extends Database {

    private HashMap<String, Integer> userTypes;

    public StoreDatabase(String driverURL, String databaseURL) {
        super(driverURL, databaseURL);
        userTypes = new HashMap<>();
        userTypes.put("NOT_REGISTERED",0);
        userTypes.put("CLERK",1);
        userTypes.put("MANAGER",2);

    }

    public Integer userType(String type) {
        int user_type  =  userTypes.get(type);
        return user_type;
    }
    public Integer login(String userName, String userPassword) {

        Integer user_type  = userTypes.get("NOT_REGISTERED");
        ResultSet resultSet = executeQuery("select * from users where user_name='"+userName+"';");

        try {
            if(resultSet.next())
            {
                String databasePassword = resultSet.getString("user_password");
                if(databasePassword.equals(userPassword))
                    user_type = resultSet.getInt("user_type");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_type;
    }
    


}
