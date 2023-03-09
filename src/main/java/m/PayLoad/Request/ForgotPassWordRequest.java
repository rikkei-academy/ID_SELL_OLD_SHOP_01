package m.PayLoad.Request;

public class ForgotPassWordRequest {
    private String newUsersPassWord;

    public ForgotPassWordRequest() {
    }

    public ForgotPassWordRequest(String newUsersPassWord) {
        this.newUsersPassWord = newUsersPassWord;
    }

    public String getNewUsersPassWord() {
        return newUsersPassWord;
    }

    public void setNewUsersPassWord(String newUsersPassWord) {
        this.newUsersPassWord = newUsersPassWord;
    }
}
