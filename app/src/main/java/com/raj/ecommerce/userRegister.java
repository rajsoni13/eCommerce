package com.raj.ecommerce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class userRegister {


    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("email")
    @Expose
    private String Email;

    @SerializedName("password")
    @Expose
    private String Password;

    public String getName(){return Name;}
    public String getEmail(){return Email;}
    public String getPassword(){return Password;}

    public void setName(String Name){this.Name=Name;}
    public void setEmail(String Email){this.Email=Email;}
    public void setPassword(String Password){this.Password=Password;}

}
