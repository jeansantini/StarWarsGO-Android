package com.starwarsgo.data.source;

import android.support.annotation.NonNull;

import com.starwarsgo.data.source.domain.dto.ResponseGetPersonDTO;
import com.starwarsgo.data.source.domain.model.Person;

import java.util.List;

/**
 * Created by jsantini on 25/09/17.
 */

public interface PersonDataSource {

    interface GenericRequestCallback {
        void onConnectionError();
    }

    interface SavePersonCallback {
        void onPersonSaved(Person person);
        void onPersonAlreadyExists();
        void onPersonSaveError();
    }

    interface LoadPersonsCallback {

        void onPersonsLoaded(List<Person> persons);

        void onDataNotAvailable();
    }

    interface LoadPersonCallback extends GenericRequestCallback {

        void onPersonLoaded(Person person);

        void onDataNotAvailable();
    }

    interface GetPersonCallback extends GenericRequestCallback {

        void onPersonLoaded(ResponseGetPersonDTO dto);

        void onDataNotAvailable();
    }

    void savePerson(@NonNull Person person, @NonNull SavePersonCallback callback);

    void loadPersons(@NonNull LoadPersonsCallback callback);

    void getPerson(@NonNull String url, @NonNull LoadPersonCallback callback);
}
