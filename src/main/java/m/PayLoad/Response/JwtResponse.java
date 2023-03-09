package m.PayLoad.Response;

import javax.persistence.Column;

public class JwtResponse {
    private String token;
    private String type="Bearer";
    private String usersName;
    private String userEmail;
    private int userPhone;
    private int permission;

    public JwtResponse(String token, String usersName, String userEmail, int userPhone, int permission) {
        this.token = token;
        this.usersName = usersName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.permission = permission;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
