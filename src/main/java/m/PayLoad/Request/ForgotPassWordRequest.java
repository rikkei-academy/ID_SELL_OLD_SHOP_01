package m.PayLoad.Request;

public class ForgotPassWordRequest {
    private String usersName;
    private String newUsersPassWord;

    public ForgotPassWordRequest() {
    }

    public ForgotPassWordRequest(String usersName, String newUsersPassWord) {
        this.usersName = usersName;
        this.newUsersPassWord = newUsersPassWord;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getNewUsersPassWord() {
        return newUsersPassWord;
    }

    public void setNewUsersPassWord(String newUsersPassWord) {
        this.newUsersPassWord = newUsersPassWord;
    }
}
