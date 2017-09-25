package com.starwarsgo.data.source;

import android.support.annotation.NonNull;

import com.starwarsgo.model.Person;

import java.util.List;

/**
 * Created by jsantini on 25/09/17.
 */

public interface PersonDataSource {

    interface SavePersonCallback {
        void onPersonSaved();
        void onPersonAlreadyExists();
        void onPersonSaveError();
    }

    interface LoadPersonsCallback {

        void onPersonsLoaded(List<Person> persons);

        void onDataNotAvailable();
    }

    interface GetPersonCallback {

        void onPersonLoaded(Person person);

        void onDataNotAvailable();
    }

    void savePerson(@NonNull Person person, @NonNull SavePersonCallback callback);
    void loadPersons(@NonNull LoadPersonsCallback callback);
}
