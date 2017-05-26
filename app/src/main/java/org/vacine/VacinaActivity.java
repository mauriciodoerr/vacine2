package org.vacine;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.vacine.model.Vacina;

public class VacinaActivity extends AppCompatActivity {

    private EditText txtEditVacinaName;
    private EditText txtEditDate;
    private EditText txtEditPlace;
    private TextView txtEditDescription;

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
    }

    private void findViews(){
        txtEditVacinaName = (EditText) findViewById(R.id.edt_edit_vacina_name);
        txtEditDate = (EditText) findViewById(R.id.edt_edit_vacina_date);
        txtEditPlace = (EditText) findViewById(R.id.edt_edit_vacina_place);
        txtEditDescription = (TextView) findViewById(R.id.txt_view_edit_vacina_descricao);
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
}
