package org.vacine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauricio on 27/05/17.
 */

public class Carteirinha implements Serializable {

    private String id;
    private String name;
    private String gender;
    private String birthdayDate;
    private List<Vacina> vacinas = new ArrayList<>();

    public Carteirinha() {
    }

    public Carteirinha(String id, String name, String gender, String birthdayDate, List<Vacina> vacinas) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthdayDate = birthdayDate;
        this.vacinas = vacinas;
    }

    public Carteirinha(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public List<Vacina> getVacinas() {
        return vacinas;
    }

    public void setVacinas(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    @Override
    public String toString() {
        return "Carteirinha{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdayDate='" + birthdayDate + '\'' +
                ", vacinas=" + vacinas +
                '}';
    }
}
