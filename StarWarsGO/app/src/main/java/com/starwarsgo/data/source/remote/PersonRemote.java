package com.starwarsgo.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.starwarsgo.data.source.PersonDataSource;
import com.starwarsgo.data.source.service.StarWarsAPI;
import com.starwarsgo.data.source.domain.dto.ResponseGetPersonDTO;
import com.starwarsgo.util.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jsantini on 25/09/17.
 */

public class PersonRemote {

    private final Context mContext;

    public PersonRemote(@NonNull final Context context) {
        this.mContext = context;
    }

    public void getPerson(@NonNull final String url,
                          @NonNull final PersonDataSource.GetPersonCallback callback) {

        if(NetworkUtil.isConnected()) {
            StarWarsAPI.StarWarsApiContract service = StarWarsAPI.getApi(mContext);
            long id = 0;
            try {
                id = extractId(url);
            } catch (Exception e) {
                callback.onDataNotAvailable();
            }
            Call<ResponseGetPersonDTO> call = service.getPerson(id);
            call.enqueue(new Callback<ResponseGetPersonDTO>() {
                @Override
                public void onResponse(Call<ResponseGetPersonDTO> call, Response<ResponseGetPersonDTO> response) {
                    if(response.isSuccessful()) {
                        callback.onPersonLoaded(response.body());
                    } else {
                        callback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetPersonDTO> call, Throwable t) {
                    callback.onDataNotAvailable();
                }
            });
        } else {
            callback.onConnectionError();
        }

    }

    private long extractId(String url) throws Exception {
        String[] urlPieces = url.split("/");
        return Long.parseLong(urlPieces[urlPieces.length - 1]);
    }
}
