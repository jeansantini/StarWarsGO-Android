package com.starwarsgo.ui.personDetail;

import com.starwarsgo.BasePresenter;
import com.starwarsgo.BaseView;

/**
 * Created by jsantini on 25/09/17.
 */

public interface PersonDetailContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
