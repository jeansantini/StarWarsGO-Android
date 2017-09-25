package com.starwarsgo.ui.persons;

import com.starwarsgo.BasePresenter;
import com.starwarsgo.BaseView;
import com.starwarsgo.model.Person;

import java.util.List;

/**
 * Created by jsantini on 25/09/17.
 */

public interface PersonsContract {

    interface View extends BaseView<Presenter> {

        void showPersons(List<Person> persons);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
