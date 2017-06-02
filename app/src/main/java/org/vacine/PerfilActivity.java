package org.vacine;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PerfilActivity extends AppCompatActivity {

    private TextInputEditText inputName;
    private RadioGroup genderGroup;
    private RadioButton genderSelection;
    private TextInputEditText inputBirthdayDate;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        findViews();
        setActions();
    }

    private void findViews() {
        inputName = (TextInputEditText) findViewById(R.id.txt_input_name);
        genderGroup = (RadioGroup) findViewById(R.id.radio_group_gender_selection);

        int index = genderGroup.getCheckedRadioButtonId();
        genderSelection = (RadioButton) findViewById(index);
        inputBirthdayDate = (TextInputEditText) findViewById(R.id.txt_input_birthday_date);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }

    private void setActions() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCarteirinha(setBundle());
            }
        });
    }

    private Bundle setBundle(){
        Bundle params = new Bundle();
        params.putString("name", inputName.getText().toString());

        /** TODO
         * Arrumar a captura do genero da tela de perfil
         */
        params.putString("gender", genderSelection.getText().toString());

        params.putString("birthday", inputBirthdayDate.getText().toString());
        return params;
    }

    private void loadCarteirinha(Bundle params) {
        Intent intent = new Intent(PerfilActivity.this, CarteirinhaActivity.class);
        intent.putExtras(params);
        startActivity(intent);
    }

}
