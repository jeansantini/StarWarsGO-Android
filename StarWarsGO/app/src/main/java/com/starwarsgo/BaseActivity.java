package com.starwarsgo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void showOrHideLoading(final boolean isShowLoading, final String msg) {
        if(isShowLoading) {
            showLoading(msg);
        } else {
            hideLoading();
        }
    }

    private void showLoading(final String msg) {

    }

    private void hideLoading() {

    }

    public void showGenericError() {

    }

    public void showConnectionError() {

    }
}
