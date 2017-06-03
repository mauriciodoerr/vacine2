package org.vacine;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.vacine.model.Carteirinha;
import org.vacine.model.Vacina;

/**
 * Vacina
 *
 * @author Mauricio
 * @since 18/05/2017
 * @version 1.0
 */
public class VacinaActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dataRef = database.getReference("carteirinha");

    private EditText txtEditVacinaName;
    private EditText txtEditDate;
    private EditText txtEditPlace;
    private EditText txtEditDescription;
    private Button btnAddVacina;
    private Button btnDelVacina;

    private Carteirinha carteirinha;
    private Vacina vacina;

    private String returnMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacina);

        // Setup back button upon left screen corner
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViews();
        setupFromBundle();
        setInfo(vacina);
        validateTransitions(txtEditVacinaName);
        setActions();
    }

    /**
     * Setup Vacina and Carteirinha from Bundle
     */
    private void setupFromBundle() {
        vacina = (Vacina) getIntent().getBundleExtra("extra").getSerializable("vacina");
        carteirinha = (Carteirinha) getIntent().getBundleExtra("extra").getSerializable("carteirinha");
    }

    /**
     * Map the layout into variables
     */
    private void findViews(){
        txtEditVacinaName = (EditText) findViewById(R.id.edt_edit_vacina_name);
        txtEditDate = (EditText) findViewById(R.id.edt_edit_vacina_date);
        txtEditPlace = (EditText) findViewById(R.id.edt_edit_vacina_place);
        txtEditDescription = (EditText) findViewById(R.id.txt_view_edit_vacina_descricao);
        btnAddVacina = (Button) findViewById(R.id.btn_add_vacina);
        btnDelVacina = (Button) findViewById(R.id.btn_del_vacina);
    }

    /**
     * Set views on screen with vacina information
     * @param vacina received from carteirinha via Bundle
     */
    private void setInfo(Vacina vacina) {
        txtEditVacinaName.setText(vacina.getName());
        txtEditDate.setText(vacina.getDate());
        txtEditPlace.setText(vacina.getPlace());
        txtEditDescription.setText(vacina.getDescription());
    }

    /**
     * If the android version supports, it will setup the animation
     * @param view
     */
    private void validateTransitions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("view");
        }
    }

    /**
     * Populate a new object to persist into user's carteirinha
     * @return
     */
    private Vacina populateVacina(){
        Vacina vacinaTemp = new Vacina();

        if (vacina.getId() == null) {
            vacinaTemp.setId(carteirinha.getVacinas().size()+1);
        } else {
            vacinaTemp.setId(vacina.getId());
        }
        vacinaTemp.setName(txtEditVacinaName.getText().toString());
        vacinaTemp.setDate(txtEditDate.getText().toString());
        vacinaTemp.setPlace(txtEditPlace.getText().toString());
        vacinaTemp.setDescription(txtEditDescription.getText().toString());
        return vacinaTemp;
    }

    /**
     * Setup listeners for buttons in layout
     * When clicking Gravar button, it will validate if fields are filled up before adding
     * When clicking Apagar button, it wil validate if there is a id among vacina information before deleting
     */
    private void setActions() {

        btnAddVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateForm()){
                    if (vacina.getId() == null){
                        carteirinha.getVacinas().add(populateVacina());
                        returnMessage = "added";
                    } else {

                        for (Vacina vacinaTemp : carteirinha.getVacinas()){
                            int index = carteirinha.getVacinas().indexOf(vacinaTemp);
                            if (vacinaTemp.getId().intValue() == vacina.getId().intValue()){
                                carteirinha.getVacinas().set(index, populateVacina());
                                returnMessage = "updated";
                                break;
                            }

                        }

                    }

                    dataRef.child(carteirinha.getId()).setValue(carteirinha);
                    Toast.makeText(getApplicationContext(), "Vacina: " + populateVacina().getName() + " " + returnMessage + "!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        btnDelVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vacina.getId() == null){
                    Toast.makeText(getApplicationContext(), "Não foi possível remover esta vacina!", Toast.LENGTH_SHORT).show();
                } else {

                    for (Vacina vacinaTemp : carteirinha.getVacinas()) {

                        if (vacinaTemp.getId().intValue() == vacina.getId().intValue()) {
                            carteirinha.getVacinas().remove(vacinaTemp);
                            break;
                        }

                    }

                    dataRef.child(carteirinha.getId()).setValue(carteirinha);
                    Toast.makeText(getApplicationContext(), "Vacina: " + vacina.getName() + " removed!", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });
    }

    /**
     * Go back to previous screen when clicking into back button on top left corner
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Validates if all edit text are filled up, otherwise prints a friendly error message on screen.
     * @return true if all edit text are filled up.
     */
    private boolean validateForm(){
        if (txtEditVacinaName.getText().toString().length() == 0){
            txtEditVacinaName.setError("Qual o nome da vacina?");
        } else if (txtEditDate.getText().toString().length() == 0) {
            txtEditDate.setError("Qual a data que você tomou?");
        } else if (txtEditPlace.getText().toString().length() == 0){
            txtEditPlace.setError("Lembra do local onde tomou?");
        } else {
            return true;
        }
        return false;
    }
}
