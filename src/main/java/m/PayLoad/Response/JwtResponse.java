package m.PayLoad.Response;

import javax.persistence.Column;
import java.util.List;

public class JwtResponse {
    private String token;
    private String type="Bearer";
    private String usersName;
    private String userEmail;
    private String userPhone;
    private List<String> listRoles;



    public JwtResponse(String token, String usersName, String userEmail, String userPhone, List<String> listRoles) {
        this.token = token;
        this.usersName = usersName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.listRoles = listRoles;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<String> listRoles) {
        this.listRoles = listRoles;
    }
}
