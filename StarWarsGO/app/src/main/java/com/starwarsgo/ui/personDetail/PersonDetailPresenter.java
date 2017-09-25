package com.starwarsgo.ui.personDetail;

import android.support.annotation.NonNull;

import com.starwarsgo.data.source.PersonDataSource;

/**
 * Created by jsantini on 25/09/17.
 */

public class PersonDetailPresenter implements PersonDetailContract.Presenter {

    private final PersonDataSource mPersonDataSource;
    private PersonDetailContract.View mPersonDetailView;

    public PersonDetailPresenter(@NonNull final PersonDataSource personDataSource) {
        this.mPersonDataSource = personDataSource;
    }
    
    @Override
    public void setView(PersonDetailContract.View view) {
        this.mPersonDetailView = view;
    }

    @Override
    public void dropView() {
        this.mPersonDetailView = null;
    }
}
