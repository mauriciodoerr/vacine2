package org.vacine;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Profile
 *
 * @author Mauricio
 * @version 1.0
 * @since 20/05/2017
 */
public class PerfilActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // gerarHashFb();

        findViews();
        checkFbLogin();
    }

    /*
    // Gerar Hash FaceBook
    private void gerarHashFb() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }*/

    /**
     * Check is user is logged in, if not asks to login
     */
    private void checkFbLogin() {
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            loadCarteirinha(setBundle(profile.getId(), profile.getName()));
        } else {
            fbLogin();
        }
    }

    /**
     * Login with Facebook
     */
    private void fbLogin() {
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {
                            String name = object.getString("name");
                            String id = object.getString("id");
                            Toast.makeText(getApplicationContext(), "Bem-vindo " + name, Toast.LENGTH_LONG).show();
                            loadCarteirinha(setBundle(id, name));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("TAG", "onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("TAG", "onError");
            }
        });
    }

    /**
     * Map the layout into variables
     */
    private void findViews() {
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
    }

    /**
     * Get the name and put inside a Bundle to send it over to CarteirinhaActivity
     *
     * @return Bundle filled up with the name as "name"
     */
    private Bundle setBundle(String fbLoginId, String fbLoginName) {
        Bundle params = new Bundle();
        params.putString("id", fbLoginId);
        params.putString("name", fbLoginName);
        return params;
    }

    /**
     * Receives the Bundle and start the CarteirinhaActivity
     *
     * @param params Bundle received from setBundle()
     */
    private void loadCarteirinha(Bundle params) {
        Intent intent = new Intent(PerfilActivity.this, CarteirinhaActivity.class);
        intent.putExtras(params);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
