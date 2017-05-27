package org.vacine.model;

import java.io.Serializable;

/**
 * Model used to show a item Vacina under Carteirinha
 *
 * @author Mauricio Doerr
 * @version 1.0
 * @since 21/05/2017
 */

public class Vacina implements Serializable {

    private Integer id;
    private String name;
    private String date;
    private String place;
    private String description;

    public Vacina() {
    }

    public Vacina(Integer id, String name, String date, String place, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.place = place;
        this.description = description;
    }

    public Vacina(String name, String date, String place, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.place = place;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Vacina{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", place='" + place + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
