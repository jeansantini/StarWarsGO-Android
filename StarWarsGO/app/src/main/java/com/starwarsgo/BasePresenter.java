package com.starwarsgo;

/**
 * Created by jsantini on 24/09/17.
 */

public interface BasePresenter<T> {

    void setView(T view);
    void dropView();
}
