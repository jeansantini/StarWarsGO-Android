package com.starwarsgo.ui.captureQRCode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.starwarsgo.BaseActivity;
import com.starwarsgo.R;
import com.starwarsgo.data.source.PersonRepository;

public class CaptureQRCodeActivity extends BaseActivity implements CaptureQRCodeContract.View {

    CaptureQRCodePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_qrcode);
        mPresenter = new CaptureQRCodePresenter(new PersonRepository(this));

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setView(this);
        initCaptureQRCode();
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
                //handle cancel
            }
        }
    }

    @Override
    public void showSuccessSavePerson() {

    }

    @Override
    public void showErrorPersonAlreadyExists() {

    }

    @Override
    public void showSavePersonError() {

    }
}
