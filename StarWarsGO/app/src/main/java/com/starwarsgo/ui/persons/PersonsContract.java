package com.starwarsgo.ui.persons;

import com.starwarsgo.ui.BasePresenter;
import com.starwarsgo.ui.BaseView;
import com.starwarsgo.data.source.domain.model.Person;

import java.util.List;

/**
 * Created by jsantini on 25/09/17.
 */

public interface PersonsContract {

    interface View extends BaseView<Presenter> {

        void showPersons(List<Person> persons);

        void showMsgErrorLoadPersons();
    }

    interface Presenter extends BasePresenter<View> {

        String getPersonJson(int position);
    }
}
