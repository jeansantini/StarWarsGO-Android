package com.starwarsgo.ui.personDetail;

import com.starwarsgo.ui.BasePresenter;
import com.starwarsgo.ui.BaseView;
import com.starwarsgo.data.source.domain.model.Person;

/**
 * Created by jsantini on 25/09/17.
 */

public interface PersonDetailContract {

    interface View extends BaseView<Presenter> {

        void showPerson(Person person);

        void showErrorLoadPerson();
    }

    interface Presenter extends BasePresenter<View> {

    }
}
