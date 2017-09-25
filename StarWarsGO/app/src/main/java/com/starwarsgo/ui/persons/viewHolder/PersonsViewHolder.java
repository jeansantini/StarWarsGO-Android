package com.starwarsgo.ui.persons.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.starwarsgo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jsantini on 25/09/17.
 */

public class PersonsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ll_item_content)
    public LinearLayout llItemContent;

    @BindView(R.id.tv_item_url)
    public TextView tvItemUrl;

    public PersonsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
