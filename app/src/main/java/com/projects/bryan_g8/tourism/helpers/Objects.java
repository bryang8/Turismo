package com.projects.bryan_g8.tourism.helpers;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.projects.bryan_g8.tourism.bean.Departamento;
import com.projects.bryan_g8.tourism.bean.Usuario;
import com.projects.bryan_g8.tourism.volley.WebService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bryan_g8 on 4/06/16.
 */
public class Objects {

    public static List<Departamento> departamentos;

    public static void setDepartamentos(View v){
        final View view = v;
        departamentos = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, WebService.departamentos, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for (int x = 0; x<response.length();x++){
                        JSONObject row = response.getJSONObject(x);
                        departamentos.add(new Departamento(row.getInt("idDepartamento"),row.getString("nombre")));
                    }
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
    }
}
