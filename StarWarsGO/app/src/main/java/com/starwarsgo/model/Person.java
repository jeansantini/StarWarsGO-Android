package com.starwarsgo.model;

/**
 * Created by jsantini on 25/09/17.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="url", nullable = false)
    private String url;

    public Person() {
        super();
    }
    public Person(String url) {
        super();
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
