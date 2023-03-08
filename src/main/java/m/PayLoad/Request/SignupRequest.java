package m.PayLoad.Request;

import javax.persistence.Column;

public class SignupRequest {
    private String usersName;

    private String usersPassWord;

    private String userEmail;

    private int userPhone;

    private String userCompany;

    private String billingAddress;

    private String shippingAdress;

    private int permission;

    private boolean userStatus;

    public SignupRequest(String usersName, String usersPassWord, String userEmail, int userPhone, String userCompany, String billingAddress, String shippingAdress, int permission, boolean userStatus) {
        this.usersName = usersName;
        this.usersPassWord = usersPassWord;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userCompany = userCompany;
        this.billingAddress = billingAddress;
        this.shippingAdress = shippingAdress;
        this.permission = permission;
        this.userStatus = userStatus;
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

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAdress() {
        return shippingAdress;
    }

    public void setShippingAdress(String shippingAdress) {
        this.shippingAdress = shippingAdress;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }
}
