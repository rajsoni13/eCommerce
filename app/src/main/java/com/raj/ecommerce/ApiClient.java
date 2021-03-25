package com.raj.ecommerce;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASEURL ="https://geostationary-desir.000webhostapp.com/";

    public static Retrofit getclient()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
