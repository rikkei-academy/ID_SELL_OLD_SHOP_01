package m.PayLoad.Request;

import javax.persistence.Column;

public class LoginRequest {
    private String usersName;

    private String usersPassWord;

    public LoginRequest(String usersName, String usersPassWord) {
        this.usersName = usersName;
        this.usersPassWord = usersPassWord;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUsersPassWord() {
        return usersPassWord;
    }

    public void setUsersPassWord(String usersPassWord) {
        this.usersPassWord = usersPassWord;
    }
}
