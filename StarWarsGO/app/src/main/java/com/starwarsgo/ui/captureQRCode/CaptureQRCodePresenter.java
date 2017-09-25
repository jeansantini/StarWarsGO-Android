package com.starwarsgo.ui.captureQRCode;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.starwarsgo.data.source.PersonDataSource;
import com.starwarsgo.model.Person;

/**
 * Created by jsantini on 25/09/17.
 */

public class CaptureQRCodePresenter  implements CaptureQRCodeContract.Presenter {

    private PersonDataSource mPersonDataSource;

    @Nullable
    private CaptureQRCodeContract.View mQRCodeView;

    CaptureQRCodePresenter(@NonNull final PersonDataSource personDataSource) {
        mPersonDataSource = personDataSource;
    }

    @Override
    public void setView(final CaptureQRCodeContract.View view) {
        mQRCodeView = view;
    }

    @Override
    public void dropView() {
        mQRCodeView = null;
    }

    public void savePerson(String url) {
        Person person = new Person(url);
        mPersonDataSource.savePerson(person, new PersonDataSource.SavePersonCallback() {
            @Override
            public void onPersonSaved() {
                mQRCodeView.showSuccessSavePerson();
            }

            @Override
            public void onPersonAlreadyExists() {
                mQRCodeView.showErrorPersonAlreadyExists();
            }

            @Override
            public void onPersonSaveError() {
                mQRCodeView.showSavePersonError();
            }
        });

    }
}
