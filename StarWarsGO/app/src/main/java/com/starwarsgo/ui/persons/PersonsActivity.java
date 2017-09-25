package com.starwarsgo.ui.persons;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.starwarsgo.ui.BaseActivity;
import com.starwarsgo.R;
import com.starwarsgo.data.source.PersonRepository;
import com.starwarsgo.data.source.domain.model.Person;
import com.starwarsgo.ui.captureQRCode.CaptureQRCodeActivity;
import com.starwarsgo.ui.dialog.MessageDialog;
import com.starwarsgo.ui.personDetail.PersonDetailActivity;
import com.starwarsgo.ui.persons.adapter.PersonsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonsActivity extends BaseActivity implements PersonsContract.View, PersonsAdapter.PersonClickListener {

    PersonsContract.Presenter mPresenter;
    PersonsAdapter personsAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_persons)
    RecyclerView rvPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        ButterKnife.bind(this);
        mPresenter = new PersonsPresenter(new PersonRepository(this));
        setupToolbar();
        setupList();
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

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.persons_title);
        ab.setDisplayHomeAsUpEnabled(false);
    }

    private void setupList() {
        personsAdapter = new PersonsAdapter(this);
        rvPersons.setLayoutManager(new LinearLayoutManager(this));
        rvPersons.setAdapter(personsAdapter);
        personsAdapter.setmPersonClickListener(this);
    }

    @Override
    public void personClick(@NonNull final String url) {
        startActivity(PersonDetailActivity.getStartIntent(this, url));
    }

    @Override
    public void showPersons(List<Person> persons) {
        personsAdapter.setPersons(persons);
    }

    @Override
    public void showMsgErrorLoadPersons() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        final MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_load_persons_error), new MessageDialog.OnModalDialogListener() {
                    @Override
                    public void onModalDialogOKClick() {
                        finish();
                    }
                }, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }

    @OnClick(R.id.btn_capture_qrcode)
    public void onBtnCaptureQRCodeClick(View v) {
        startActivity(CaptureQRCodeActivity.getStartIntent(this));

    }
}
