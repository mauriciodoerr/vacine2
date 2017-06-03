package org.vacine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Carteirinha holds all the customer information
 *
 * @author Mauricio
 * @since 27/05/2017
 * @version 1.0
 */

public class Carteirinha implements Serializable {

    private String id;
    private String name;
    private List<Vacina> vacinas = new ArrayList<>();

    public Carteirinha() {
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
                ", vacinas=" + vacinas +
                '}';
    }
}
