package com.marinho.api_desafio_next.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiService {

    private static Retrofit retrofit;

    public static Retrofit iniciar() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}



