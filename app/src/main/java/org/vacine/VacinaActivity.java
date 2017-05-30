package org.vacine;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.vacine.model.Carteirinha;
import org.vacine.model.Vacina;

import java.util.ArrayList;
import java.util.List;

public class VacinaActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dataRef;

    private EditText txtEditVacinaName;
    private EditText txtEditDate;
    private EditText txtEditPlace;
    private TextView txtEditDescription;
    private Button btnAddVacina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina);

        findViews();
        if (getIntent().getBundleExtra("extra") != null) {
            Vacina vacina = (Vacina) getIntent().getBundleExtra("extra").getSerializable("vacina");
            setInfo(vacina);
            validateTransitions(txtEditVacinaName);
        }
        setActions();
    }

    private void findViews(){
        txtEditVacinaName = (EditText) findViewById(R.id.edt_edit_vacina_name);
        txtEditDate = (EditText) findViewById(R.id.edt_edit_vacina_date);
        txtEditPlace = (EditText) findViewById(R.id.edt_edit_vacina_place);
        txtEditDescription = (TextView) findViewById(R.id.txt_view_edit_vacina_descricao);
        btnAddVacina = (Button) findViewById(R.id.btn_add_vacina);
    }

    private void setInfo(Vacina vacina) {
        txtEditVacinaName.setText(vacina.getName());
        txtEditDate.setText(vacina.getDate());
        txtEditPlace.setText(vacina.getPlace());
        txtEditDescription.setText(vacina.getDescription());
    }

    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }

    private void setActions(){
        /** TODO : Implement to get values from screen and pass it to firebase
         *
          */
        btnAddVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Vacina> vacinas = new ArrayList<>();
                Vacina vacina = new Vacina(20, txtEditVacinaName.getText().toString(), txtEditDate.getText().toString(), txtEditPlace.getText().toString(), "");
                vacinas.add(vacina);
                Carteirinha carteirinha = new Carteirinha();
                carteirinha.setVacinas(vacinas);

                dataRef.child("Teste2").setValue(carteirinha);

            }
        });
    }
}
