package com.starwarsgo.ui.captureQRCode;

import com.starwarsgo.ui.BasePresenter;
import com.starwarsgo.ui.BaseView;

/**
 * Created by jsantini on 24/09/17.
 */

public interface CaptureQRCodeContract {

    interface View extends BaseView<Presenter>  {

        void showSuccessSavePerson();

        void showErrorPersonAlreadyExists();

        void showSavePersonError();

        String getMsgLoading();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
