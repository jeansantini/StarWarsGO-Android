package com.starwarsgo.ui.personDetail;

import android.support.annotation.NonNull;

import com.starwarsgo.data.source.PersonDataSource;
import com.starwarsgo.data.source.domain.model.Person;

/**
 * Created by jsantini on 25/09/17.
 */

public class PersonDetailPresenter implements PersonDetailContract.Presenter {

    private final PersonDataSource mPersonDataSource;
    private final String url;
    private PersonDetailContract.View mPersonDetailView;

    public PersonDetailPresenter(@NonNull final PersonDataSource personDataSource,
                                 @NonNull final String url) {
        this.mPersonDataSource = personDataSource;
        this.url = url;
    }
    
    @Override
    public void setView(PersonDetailContract.View view) {
        this.mPersonDetailView = view;
        getPersonByURL();
    }

    @Override
    public void dropView() {
        this.mPersonDetailView = null;
    }

    private void getPersonByURL() {
        mPersonDataSource.getPerson(url, new PersonDataSource.LoadPersonCallback() {
            @Override
            public void onPersonLoaded(Person person) {
                mPersonDetailView.showPerson(person);
            }

            @Override
            public void onDataNotAvailable() {

            }

            @Override
            public void onConnectionError() {

            }
        });
    }

}
