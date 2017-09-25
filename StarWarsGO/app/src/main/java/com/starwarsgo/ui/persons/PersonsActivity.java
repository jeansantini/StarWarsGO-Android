package com.starwarsgo.ui.persons;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.starwarsgo.BaseActivity;
import com.starwarsgo.R;
import com.starwarsgo.data.source.PersonRepository;
import com.starwarsgo.model.Person;
import com.starwarsgo.ui.persons.adapter.PersonsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonsActivity extends BaseActivity implements PersonsContract.View, PersonsAdapter.PersonClickListener {

    PersonsContract.Presenter mPresenter;
    PersonsAdapter personsAdapter;

    @BindView(R.id.rv_persons)
    RecyclerView rvPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        ButterKnife.bind(this);
        mPresenter = new PersonsPresenter(new PersonRepository(this));
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

    private void setupList() {
        personsAdapter = new PersonsAdapter(this);
        rvPersons.setLayoutManager(new LinearLayoutManager(this));
        rvPersons.setAdapter(personsAdapter);
        personsAdapter.setmPersonClickListener(this);
    }

    @Override
    public void personClick(int position) {

    }

    @Override
    public void showPersons(List<Person> persons) {
        personsAdapter.setPersons(persons);
    }
}
