package org.vacine;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class PerfilActivity extends AppCompatActivity {

    private View profileContent;
    private TextInputEditText inputName;
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
        profileContent = findViewById(R.id.profile_content);
        inputName = (TextInputEditText) findViewById(R.id.txt_input_name);
        genderSelection = (RadioButton) findViewById(R.id.radio_gender_selection);
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
        params.putBoolean("createProfile", true);
        return params;
    }

    private void loadCarteirinha(Bundle params) {
        Intent intent = new Intent(PerfilActivity.this, CarteirinhaActivity.class);
        intent.putExtras(params);
        startActivity(intent);
    }

}
