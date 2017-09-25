package com.starwarsgo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jsantini on 25/09/17.
 */

@Entity(name = "film")
public class Film {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="url", nullable = false)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
