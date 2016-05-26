package com.projects.bryan_g8.tourism;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.projects.bryan_g8.tourism.bean.Usuario;
import com.projects.bryan_g8.tourism.volley.WebService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SignUp extends AppCompatActivity {
    private TextView txtName, txtEmail,txtUsername,txtPassword,txtConfirmPassword;
    private Button btnSignUp;
    private Usuario userLogged=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtName = (TextView) findViewById(R.id.txtNameSU);
        txtEmail= (TextView) findViewById(R.id.txtEmailSU);
        txtUsername=(TextView) findViewById(R.id.txtUsernameSU);
        txtPassword=(TextView) findViewById(R.id.txtPasswordSU);
        txtConfirmPassword= (TextView)findViewById (R.id.txtConfirmPasswordSU);
        btnSignUp = (Button) findViewById(R.id.btnSignUpSU);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
                HashMap<String, String> params=new HashMap<>();
                params.put("nick",txtUsername.getText().toString());
                params.put("contraseña",txtPassword.getText().toString());
                params.put("rolId","2");
                params.put("correo",txtEmail.getText().toString());
                params.put("nombre",txtName.getText().toString());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebService.registrar,new JSONObject(params),new Response.Listener<JSONObject>(){
                   @Override
                   public void onResponse(JSONObject response){
                       try{
                           startActivity(new Intent(SignUp.this,Login.class));
                       }catch(Exception ex){

                       }
                   }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError err)  {
                        Log.d("Error: Response ",err.getMessage());
                    }
                });
            }
        });
    }
}
