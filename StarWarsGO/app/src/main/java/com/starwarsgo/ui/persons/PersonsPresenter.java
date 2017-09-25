package com.starwarsgo.ui.persons;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.starwarsgo.data.source.PersonDataSource;
import com.starwarsgo.data.source.domain.model.Person;

import java.util.List;

/**
 * Created by jsantini on 25/09/17.
 */

public class PersonsPresenter implements PersonsContract.Presenter {

    private final PersonDataSource mPersonDataSource;
    public List<Person> personList;

    @Nullable
    private PersonsContract.View mPersonsView;

    PersonsPresenter(@NonNull final PersonDataSource personDataSource) {
        mPersonDataSource = personDataSource;
    }

    @Override
    public void setView(PersonsContract.View view) {
        mPersonsView = view;
        loadPersons();
    }

    @Override
    public void dropView() {
        mPersonsView = null;
    }

    private void loadPersons() {
        mPersonDataSource.loadPersons(new PersonDataSource.LoadPersonsCallback() {

            @Override
            public void onPersonsLoaded(List<Person> persons) {
                personList = persons;
                mPersonsView.showPersons(persons);
            }

            @Override
            public void onDataNotAvailable() {
                mPersonsView.showMsgErrorLoadPersons();
            }
        });
    }

    @Override
    public String getPersonJson(int position) {
        return new Gson().toJson(personList.get(position));
    }
}
