package com.yhpark.githubex.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YongHyeon on 2017-04-06.
 */

public class RetrofitClient<T> {
    private T service;
    private final String baseUrl = "http://api.github.com";

    public T getClient(Class<? extends T> type) {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(type);
        }
        return service;
    }
}
