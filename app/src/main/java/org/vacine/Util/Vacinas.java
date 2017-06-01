package org.vacine.Util;

import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import org.vacine.model.Carteirinha;
import org.vacine.model.Vacina;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Util created to mock a Carteirinha
 *
 * @author Mauricio
 * @since 21/05/2017
 * @version 1.0
 */

public class Vacinas {

    private static final String TAG = "Vacinas";
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef = database.getReference("carteirinha");

    public static List<Vacina> getVacinas() {
        List<Vacina> vacinas = new ArrayList<>();

        vacinas.add(new Vacina(1, "Vacina 1", "10/02/2017", "Curitiba", "Descricao 1"));
        vacinas.add(new Vacina(2, "Vacina 2", "10/02/2017", "Curitiba", "Descricao 2"));
        vacinas.add(new Vacina(3, "Vacina 3", "10/02/2017", "Curitiba", "Descricao 3"));
        vacinas.add(new Vacina(4, "Vacina 4", "10/02/2017", "Curitiba", "Descricao 4"));
        vacinas.add(new Vacina(5, "Vacina 5", "10/02/2017", "Curitiba", "Descricao 5"));
        vacinas.add(new Vacina(6, "Vacina 6", "10/02/2017", "Curitiba", "Descricao 6"));
        vacinas.add(new Vacina(7, "Vacina 7", "10/02/2017", "Curitiba", "Descricao 7"));
        vacinas.add(new Vacina(8, "Vacina 8", "10/02/2017", "Curitiba", "Descricao 8"));
        vacinas.add(new Vacina(9, "Vacina 9", "10/02/2017", "Curitiba", "Descricao 9"));
        vacinas.add(new Vacina(10, "Vacina 10", "10/02/2017", "Curitiba", "Descricao 10"));
        vacinas.add(new Vacina(11, "Vacina 11", "10/02/2017", "Curitiba", "Descricao 11"));
        vacinas.add(new Vacina(12, "Vacina 12", "10/02/2017", "Curitiba", "Descricao 12"));
        vacinas.add(new Vacina(13, "Vacina 13", "10/02/2017", "Curitiba", "Descricao 13"));
        vacinas.add(new Vacina(14, "Vacina 14", "10/02/2017", "Curitiba", "Descricao 14"));
        vacinas.add(new Vacina(15, "Vacina 15", "10/02/2017", "Curitiba", "Descricao 15"));
        vacinas.add(new Vacina(16, "Vacina 16", "10/02/2017", "Curitiba", "Descricao 16"));
        vacinas.add(new Vacina(17, "Vacina 17", "10/02/2017", "Curitiba", "Descricao 17"));

        return vacinas;
    }

    public static void setVacinasFirebase(String name) {

        Carteirinha carteirinha = new Carteirinha(getVacinas());
        carteirinha.setId(name + new Date().getTime());
        carteirinha.setName(name);
        carteirinha.setBirthdayDate("27/05/2017");
        carteirinha.setGender("Masculino");
        myRef.child(carteirinha.getId()).setValue(carteirinha);

    }

}
