package com.starwarsgo.ui.personDetail;

import android.os.Bundle;

import com.starwarsgo.BaseActivity;
import com.starwarsgo.R;

import butterknife.ButterKnife;

public class PersonDetailActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);
    }
}
