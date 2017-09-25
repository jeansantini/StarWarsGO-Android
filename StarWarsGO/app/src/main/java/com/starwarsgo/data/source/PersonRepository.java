package com.starwarsgo.data.source;

import android.content.Context;
import android.support.annotation.NonNull;

import com.starwarsgo.model.Person;

import java.util.List;

/**
 * Created by jsantini on 25/09/17.
 */

public class PersonRepository extends GenericRepository<Person> implements PersonDataSource {

    public PersonRepository(Context context) {
        super(context, Person.class);
    }

    @Override
    public void savePerson(@NonNull Person person, @NonNull SavePersonCallback callback) {
        try {
            List<Person> results = dao.queryBuilder().where().eq("url", person.getUrl()).query();
            if(results != null && results.size() > 0) {
                callback.onPersonAlreadyExists();
                return;
            }
            insert(person);
            callback.onPersonSaved();
        } catch (Exception e) {
            callback.onPersonSaveError();
        }
    }

    @Override
    public void loadPersons(@NonNull LoadPersonsCallback callback) {
        List<Person> persons = getAll();
        callback.onPersonsLoaded(persons);
    }
}
