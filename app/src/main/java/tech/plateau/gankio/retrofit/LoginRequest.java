package tech.plateau.gankio.retrofit;

import android.support.annotation.Nullable;

public class LoginRequest {

    public LoginRequest() {
    }

    public LoginRequest(String phone, @Nullable String password, @Nullable String loginSecretKey) {
        this.phone = phone;
        this.password = password;
        this.loginSecretKey = loginSecretKey;
    }

    /**
     * phone : 18818217393
     * password : 123123
     * loginSecretKey : 12312323423
     */

    private String phone;
    private String password;
    private String loginSecretKey;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginSecretKey() {
        return loginSecretKey;
    }

    public void setLoginSecretKey(String loginSecretKey) {
        this.loginSecretKey = loginSecretKey;
    }
}