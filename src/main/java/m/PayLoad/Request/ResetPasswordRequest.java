package m.PayLoad.Request;

public class ResetPasswordRequest {


    private String usersPassWord;

    private String newUsersPassWord;
    private String confirmPassWord;

    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(String usersPassWord, String newUsersPassWord, String confirmPassWord) {
        this.usersPassWord = usersPassWord;
        this.newUsersPassWord = newUsersPassWord;
        this.confirmPassWord = confirmPassWord;
    }



    public String getUsersPassWord() {
        return usersPassWord;
    }

    public void setUsersPassWord(String usersPassWord) {
        this.usersPassWord = usersPassWord;
    }

    public String getNewUsersPassWord() {
        return newUsersPassWord;
    }

    public void setNewUsersPassWord(String newUsersPassWord) {
        this.newUsersPassWord = newUsersPassWord;
    }

    public String getConfirmPassWord() {
        return confirmPassWord;
    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
    }
}
