package com.starwarsgo.ui;

/**
 * Created by jsantini on 24/09/17.
 */

public interface BaseView<T> {

    void showOrHideLoading(boolean isShowLoading, String msg);

    void showGenericError();

    void showConnectionError();
}
