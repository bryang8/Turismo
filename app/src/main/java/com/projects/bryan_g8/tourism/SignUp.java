package com.projects.bryan_g8.tourism;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.projects.bryan_g8.tourism.bean.Usuario;
import com.projects.bryan_g8.tourism.volley.WebService;


import butterknife.ButterKnife;
import butterknife.InjectView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;


public class SignUp extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private Usuario userLogged=null;


    @InjectView(R.id.input_name_su) EditText _nameText;
    @InjectView(R.id.input_username_su) EditText _usernameText;
    @InjectView(R.id.input_email_su) EditText _emailText;
    @InjectView(R.id.input_password_su) EditText _passwordText;
    @InjectView(R.id.input_confirm_password_su) EditText _confirmPasswordText;
    @InjectView(R.id.btn_signup_su) Button _signupButton;
    @InjectView(R.id.link_login_su) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(v);
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup(View v) {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        HashMap<String, String> params=new HashMap<>();
        params.put("nick",_usernameText.getText().toString());
        params.put("contraseña",_passwordText.getText().toString());
        params.put("idRol","2");
        params.put("correo",_emailText.getText().toString());
        params.put("nombre",_nameText.getText().toString());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.registrar, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray listaUsuarios=response.getJSONArray("user");
                    if(listaUsuarios.length()>0){
                        JSONObject user=listaUsuarios.getJSONObject(0);
                        userLogged=new Usuario(
                                user.getInt("idUsuario"),
                                user.getString("nombre"),
                                user.getString("correo"),
                                user.getString("nick"),
                                "none",
                                response.getString("token"),
                                response.getString("exp")
                        );
                    }else{
                        Toast.makeText(getApplicationContext(),"Please verify your data",Toast.LENGTH_LONG).show();
                    }
                    startActivity(new Intent(SignUp.this,Login.class));
                }catch(Exception ex){
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError err) {

            }
        }

        );
        WebService.getInstance(v.getContext()).addToRequestQueue(request);

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String confirmPassword = _confirmPasswordText.getText().toString();
        String username = _usernameText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (username.isEmpty() || username.length() < 4) {
            _nameText.setError("at least 4 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (confirmPassword.equals(password)) {
            _confirmPasswordText.setError("passwords no not match");
            valid = false;
        } else {
            _passwordText.setError(null);
        }


        return valid;
    }
}