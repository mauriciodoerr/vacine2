package org.vacine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Profile
 *
 * @author Mauricio
 * @since 20/05/2017
 * @version 1.0
 */
public class PerfilActivity extends AppCompatActivity {

    private TextInputEditText inputName;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        findViews();
        setActions();
    }

    /**
     * Map the layout into variables
     */
    private void findViews() {
        inputName = (TextInputEditText) findViewById(R.id.txt_input_name);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }

    /**
     * Setup listeners for buttons in layout
     * When clicking the Login button, it will validate if the name is filled up before login
     */
    private void setActions() {

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()){
                    loadCarteirinha(setBundle());
                }
            }
        });
    }

    /**
     * Get the name and put inside a Bundle to send it over to CarteirinhaActivity
     * @return Bundle filled up with the name as "name"
     */
    private Bundle setBundle(){
        Bundle params = new Bundle();
        params.putString("name", inputName.getText().toString());
        return params;
    }

    /**
     * Receives the Bundle and start the CarteirinhaActivity
     * @param params Bundle received from setBundle()
     */
    private void loadCarteirinha(Bundle params) {
        Intent intent = new Intent(PerfilActivity.this, CarteirinhaActivity.class);
        intent.putExtras(params);
        startActivity(intent);
    }

    /**
     * Validates if all edit text are filled up, otherwise prints a friendly error message on screen
     * @return true if all edit text are filled up
     */
    private boolean validateForm(){
        if (inputName.getText().toString().length() == 0){
            inputName.setError("Qual seu nome?");
        } else {
            return true;
        }
        return false;
    }

}
