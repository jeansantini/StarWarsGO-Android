package com.starwarsgo.data.source.domain.model;

/**
 * Created by jsantini on 25/09/17.
 */

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.ForeignCollectionField;
import com.starwarsgo.data.source.domain.dto.ResponseGetPersonDTO;

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

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="height", nullable = true)
    private String height;

    @Column(name="mass", nullable = true)
    private String mass;

    @SerializedName("hair_color")
    @Column(name="hair_color", nullable = true)
    private String hairColor;

    @SerializedName("skin_color")
    @Column(name="skin_color", nullable = true)
    private String skinColor;

    @SerializedName("eye_color")
    @Column(name="eye_color", nullable = true)
    private String eyeColor;

    @SerializedName("birth_year")
    @Column(name="birth_year", nullable = true)
    private String birthYear;

    @Column(name="gender", nullable = true)
    private String gender;

    @Column(name="homeworld", nullable = true)
    private String homeworld;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<PersonFilm> films;

    @Column(name = "created", nullable = true)
    private String created;

    @Column(name = "edited", nullable = true)
    private String edited;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public ForeignCollection<PersonFilm> getFilms() {
        return films;
    }

    public void setFilms(ForeignCollection<PersonFilm> films) {
        this.films = films;
    }

    public static Person converter(ResponseGetPersonDTO dto) {
        Person person = new Person(dto.getUrl());
        person.setHeight(dto.getHeight());
        person.setMass(dto.getMass());
        person.setBirthYear(dto.getBirthYear());
        person.setEyeColor(dto.getEyeColor());
        person.setHairColor(dto.getHairColor());
        person.setCreated(dto.getCreated());
        person.setEdited(dto.getEdited());
        person.setName(dto.getName());
        person.setGender(dto.getGender());
        person.setHomeworld(dto.getHomeworld());
        person.setSkinColor(dto.getSkinColor());
        return person;
    }
}
