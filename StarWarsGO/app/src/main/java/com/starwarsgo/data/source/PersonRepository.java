package com.starwarsgo.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import com.starwarsgo.data.source.local.PersonLocal;
import com.starwarsgo.data.source.remote.PersonRemote;
import com.starwarsgo.data.source.domain.dto.ResponseGetPersonDTO;
import com.starwarsgo.data.source.domain.model.Person;

import java.sql.SQLException;

/**
 * Created by jsantini on 25/09/17.
 */

public class PersonRepository implements PersonDataSource {

    private final PersonRemote mPersonRemote;
    private final PersonLocal mPersonLocal;
    private final Context mContext;

    public PersonRepository(@NonNull final Context context) {
        super();
        this.mContext = context;
        this.mPersonRemote = new PersonRemote(context);
        this.mPersonLocal = new PersonLocal(context);
    }

    @Override
    public void savePerson(@NonNull final Person person, @NonNull final SavePersonCallback callback) {
        try {
            person.setUrl(parseUrl(person.getUrl()));
            if (mPersonLocal.verifyPersonAlreadyExists(person.getUrl())) {
                callback.onPersonAlreadyExists();
                return;
            }
            mPersonRemote.getPerson(person.getUrl(), new GetPersonCallback() {
                @Override
                public void onPersonLoaded(ResponseGetPersonDTO dto) {
                    mPersonLocal.savePerson(dto, callback);
                }

                @Override
                public void onDataNotAvailable() {

                }

                @Override
                public void onConnectionError() {

                }
            });
        } catch (Exception e) {
            callback.onPersonSaveError();
        }
    }

    @Override
    public void loadPersons(@NonNull final LoadPersonsCallback callback) {
        mPersonLocal.loadPersons(callback);
    }

    @Override
    public void getPerson(@NonNull final String url, @NonNull final LoadPersonCallback callback) {
        try {
            Person person = mPersonLocal.getPersonByURL(url);
            if(person != null) {
                callback.onPersonLoaded(person);
                return;
            }
        } catch (SQLException e) {
            callback.onDataNotAvailable();
        }
        mPersonRemote.getPerson(url, new GetPersonCallback() {
            @Override
            public void onPersonLoaded(ResponseGetPersonDTO dto) {
                mPersonLocal.savePerson(dto, new SavePersonCallback() {
                    @Override
                    public void onPersonSaved(Person person) {
                        callback.onPersonLoaded(person);
                    }

                    @Override
                    public void onPersonAlreadyExists() {

                    }

                    @Override
                    public void onPersonSaveError() {

                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onConnectionError() {
                callback.onConnectionError();
            }
        });
    }

    private String parseUrl(String url) {
        String[] urlPeace = url.split("/");
        if(!urlPeace[urlPeace.length - 1].equalsIgnoreCase("/")) {
            return url + "/";
        }
        return url;
    }
}
