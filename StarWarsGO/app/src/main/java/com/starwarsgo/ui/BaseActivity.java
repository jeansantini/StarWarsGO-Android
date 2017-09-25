package com.starwarsgo.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.starwarsgo.R;
import com.starwarsgo.ui.dialog.LoadingDialog;
import com.starwarsgo.ui.dialog.MessageDialog;
import com.starwarsgo.util.Utils;

public class BaseActivity extends AppCompatActivity {

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        overridePendingTransition(R.anim.slide_in_rigth_to_left, R.anim.slide_out_rigth_to_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_to_rigth, R.anim.slide_out_left_to_rigth);
    }

    public void showOrHideLoading(final boolean isShowLoading, final String msg) {
        if(isShowLoading) {
            showLoading(msg);
        } else {
            hideLoading();
        }
    }

    private void showLoading(final String msg) {
        Utils.hideKeyboard(this);
        hideLoading();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        loadingDialog = LoadingDialog.newInstance(msg);
        loadingDialog.setCancelable(false);
        loadingDialog.show(ft, "dialog");
    }

    private void hideLoading() {
        if(loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public void showGenericError() {
        hideLoading();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_generic_error), null, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }

    public void showConnectionError() {
        hideLoading();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_connection_error), null, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }
}
