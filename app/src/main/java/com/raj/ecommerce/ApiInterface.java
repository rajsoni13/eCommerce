package com.raj.ecommerce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("insertdata.php")
    Call<ResultLogin>insertdata(@Field("name") String first_name,
                                @Field("email_id") String email_id,
                                @Field("password") String password);


}
