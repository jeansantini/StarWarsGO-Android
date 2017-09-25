package com.starwarsgo.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.starwarsgo.data.source.GenericRepository;
import com.starwarsgo.data.source.PersonDataSource;
import com.starwarsgo.data.source.domain.dto.ResponseGetPersonDTO;
import com.starwarsgo.data.source.domain.model.Person;
import com.starwarsgo.data.source.domain.model.PersonFilm;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jsantini on 25/09/17.
 */

public class PersonLocal extends GenericRepository<Person> {

    private final Context mContext;
    private Dao<PersonFilm, ?> daoFilm;

    public PersonLocal(@NonNull final Context context) {
        super(context, Person.class);
        this.mContext = context;
    }

    public void savePerson(@NonNull ResponseGetPersonDTO dto, @NonNull PersonDataSource.SavePersonCallback callback) {
        try {
            Person person = Person.converter(dto);
            insert(person);
            for(String film:dto.getFilms()) {
                PersonFilm personFilm = new PersonFilm();
                personFilm.setFilm(film);
                personFilm.setPerson(person);
                insertPersonFilm(personFilm);
            }
            callback.onPersonSaved(getById(person.getId()));
        } catch (Exception e) {
            callback.onPersonSaveError();
        }
    }

    public boolean verifyPersonAlreadyExists(@NonNull final String url) throws SQLException {
        List<Person> results = dao.queryBuilder().where().eq("url", url.trim()).query();
        if(results != null && results.size() > 0) {
            return true;
        }
        return false;
    }

    public void loadPersons(@NonNull PersonDataSource.LoadPersonsCallback callback) {
        List<Person> persons = getAll();
        callback.onPersonsLoaded(persons);
    }

    private void insertPersonFilm(@NonNull final PersonFilm personFilm) throws Exception {
        if(daoFilm == null) {
            daoFilm = DaoManager.createDao(getConnectionSource(), PersonFilm.class);
        }
        daoFilm.create(personFilm);
    }

    public Person getPersonByURL(String url) throws SQLException {
        List<Person> results = dao.queryBuilder().where().eq("url", url).query();
        if(results != null && results.size() > 0) {
            return results.get(0);
        }
        return null;
    }
}
