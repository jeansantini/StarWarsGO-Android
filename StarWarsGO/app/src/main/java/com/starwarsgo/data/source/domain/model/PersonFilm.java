package com.starwarsgo.data.source.domain.model;

import com.j256.ormlite.field.DatabaseField;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by jsantini on 25/09/17.
 */

@Entity(name="personFilm")
public class PersonFilm {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(foreign = true)
    private Person person;
    @Column(name="url", nullable = false)
    private String film;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }
}
