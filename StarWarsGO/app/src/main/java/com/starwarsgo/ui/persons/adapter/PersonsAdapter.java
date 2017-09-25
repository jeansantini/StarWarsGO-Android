package com.starwarsgo.ui.persons.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starwarsgo.R;
import com.starwarsgo.model.Person;
import com.starwarsgo.ui.persons.viewHolder.PersonsViewHolder;

import java.util.List;

/**
 * Created by jsantini on 25/09/17.
 */

public class PersonsAdapter extends RecyclerView.Adapter<PersonsViewHolder> {

    private Context mContext;
    private List<Person> persons;
    private PersonClickListener mPersonClickListener;

    public PersonsAdapter(@NonNull final Context context) {
        this.mContext = context;
    }
    @Override
    public PersonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_layout, parent, false);
        return new PersonsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonsViewHolder holder, final int position) {
        final Person person = persons.get(position);
        holder.tvItemUrl.setText(person.getUrl());
        if(mPersonClickListener != null) {
            holder.llItemContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPersonClickListener.personClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(persons == null) {
            return 0;
        }
        return persons.size();
    }

    public void setPersons(final List<Person> persons) {
        this.persons = persons;
        notifyDataSetChanged();
    }

    public void setmPersonClickListener(final PersonClickListener personClickListener) {
        this.mPersonClickListener = personClickListener;
    }

    public interface PersonClickListener {
        void personClick(int position);
    }
}
