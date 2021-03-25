package com.raj.ecommerce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLogin {

    @SerializedName("login")
    @Expose
    private List<userLogin> login = null;

    public List<userLogin> getLogin() {
        return login;
    }

    public void setLogin(List<userLogin> login) {
        this.login = login;
    }

}