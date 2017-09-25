package com.starwarsgo.data.source.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.starwarsgo.data.source.domain.dto.ResponseGetPersonDTO;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jsantini on 25/09/17.
 */

public class StarWarsAPI {

    private static StarWarsApiContract starWarsApi;
    private static String URL_BASE = "https://swapi.co/api/";

    public static StarWarsAPI.StarWarsApiContract getApi(final Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = chain.request().newBuilder();
                requestBuilder.header("Content-Type", "application/json");
                requestBuilder.header("Accept", "application/json");
                requestBuilder.method(original.method(), original.body());
                return chain.proceed(requestBuilder.build());
            }
        });

        OkHttpClient okClient = builder.build();
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy hh:mm:ss").create();
        Retrofit client = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        starWarsApi = client.create(StarWarsApiContract.class);
        return starWarsApi;
    }

    public interface StarWarsApiContract {

        @GET("people/{id}")
        Call<ResponseGetPersonDTO> getPerson(@Path("id") long id);
    }
}
