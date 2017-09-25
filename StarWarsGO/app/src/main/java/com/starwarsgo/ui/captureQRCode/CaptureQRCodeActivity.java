package com.starwarsgo.ui.captureQRCode;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.starwarsgo.ui.BaseActivity;
import com.starwarsgo.R;
import com.starwarsgo.data.source.PersonRepository;
import com.starwarsgo.ui.dialog.MessageDialog;

public class CaptureQRCodeActivity extends BaseActivity implements CaptureQRCodeContract.View {

    CaptureQRCodePresenter mPresenter;

    public static Intent getStartIntent(@NonNull final Context context) {
        return new Intent(context, CaptureQRCodeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_qrcode);
        mPresenter = new CaptureQRCodePresenter(new PersonRepository(this));
        initCaptureQRCode();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }

    @Override
    public void onDestroy() {
        mPresenter.dropView();
        super.onDestroy();
    }

    private void initCaptureQRCode() {
        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);

        } catch (Exception e) {

            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            startActivity(marketIntent);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                String url = data.getStringExtra("SCAN_RESULT");
                mPresenter.savePerson(url);
            }
            if(resultCode == RESULT_CANCELED){
                finish();
            }
        }
    }

    @Override
    public void showSuccessSavePerson() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        final MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_save_person_success), new MessageDialog.OnModalDialogListener() {
                    @Override
                    public void onModalDialogOKClick() {
                        finish();
                    }
                }, MessageDialog.TYPE_SUCCESS);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }

    @Override
    public void showErrorPersonAlreadyExists() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        final MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_save_person_already_exists), new MessageDialog.OnModalDialogListener() {
                    @Override
                    public void onModalDialogOKClick() {
                        finish();
                    }
                }, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }

    @Override
    public void showSavePersonError() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        final MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_save_person_error), new MessageDialog.OnModalDialogListener() {
                    @Override
                    public void onModalDialogOKClick() {
                        finish();
                    }
                }, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }

    @Override
    public String getMsgLoading() {
        return getString(R.string.msg_loading_save_person);
    }
}
