package com.starwarsgo.ui.personDetail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.starwarsgo.ui.BaseActivity;
import com.starwarsgo.R;
import com.starwarsgo.data.source.PersonRepository;
import com.starwarsgo.data.source.domain.model.Person;
import com.starwarsgo.ui.dialog.MessageDialog;
import com.starwarsgo.util.Constants;
import com.starwarsgo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonDetailActivity extends BaseActivity implements PersonDetailContract.View {

    PersonDetailPresenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_person_name)
    TextView tvPersonName;

    @BindView(R.id.tv_person_mass)
    TextView tvPersonMass;

    @BindView(R.id.tv_person_height)
    TextView tvPersonHeight;

    @BindView(R.id.tv_person_birth_year)
    TextView tvPersonBirthYear;

    @BindView(R.id.tv_person_eye_color)
    TextView tvPersonEyeColor;

    @BindView(R.id.tv_person_hair_color)
    TextView tvPersonHairColor;

    @BindView(R.id.tv_person_created)
    TextView tvPersonCreated;

    @BindView(R.id.tv_person_edited)
    TextView tvPersonEdited;

    @BindView(R.id.tv_person_gender)
    TextView tvPersonGender;

    @BindView(R.id.tv_person_homeworld)
    TextView tvPersonHomeworld;

    @BindView(R.id.tv_person_skin_color)
    TextView tvPersonSkinColor;

    public static Intent getStartIntent(@NonNull final Context context, @NonNull final String url) {
        Intent intent = new Intent(context, PersonDetailActivity.class);
        intent.putExtra(Constants.EXTRA_URL_PERSON, url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);
        mPresenter = new PersonDetailPresenter(new PersonRepository(this),
                getIntent().getStringExtra(Constants.EXTRA_URL_PERSON));
        setupToolbar();
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
        ab.setTitle(R.string.person_detail_title);
        ab.setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_48dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showPerson(Person person) {
        tvPersonName.setText(person.getName());
        tvPersonHeight.setText(person.getHeight());
        tvPersonMass.setText(person.getMass());
        tvPersonBirthYear.setText(person.getBirthYear());
        tvPersonEyeColor.setText(person.getEyeColor());
        tvPersonHairColor.setText(person.getHairColor());
        tvPersonCreated.setText(person.getCreated());
        tvPersonEdited.setText(person.getEdited());
        tvPersonGender.setText(person.getGender());
        tvPersonHomeworld.setText(person.getHomeworld());
        tvPersonSkinColor.setText(person.getSkinColor());
    }

    @Override
    public void showErrorLoadPerson() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        final MessageDialog messageDialog = MessageDialog.newInstance(
                getResources().getString(R.string.msg_load_person_error), new MessageDialog.OnModalDialogListener() {
                    @Override
                    public void onModalDialogOKClick() {
                        finish();
                    }
                }, MessageDialog.TYPE_ERROR);
        messageDialog.setCancelable(false);
        messageDialog.show(ft, "dialog");
    }
}
