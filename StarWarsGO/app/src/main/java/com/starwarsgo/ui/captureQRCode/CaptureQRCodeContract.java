package com.starwarsgo.ui.captureQRCode;

import com.starwarsgo.BasePresenter;
import com.starwarsgo.BaseView;

/**
 * Created by jsantini on 24/09/17.
 */

public interface CaptureQRCodeContract {

    interface View extends BaseView<Presenter>  {

        void showSuccessSavePerson();

        void showErrorPersonAlreadyExists();

        void showSavePersonError();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
