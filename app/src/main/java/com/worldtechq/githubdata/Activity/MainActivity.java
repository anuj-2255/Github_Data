package com.worldtechq.githubdata.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.worldtechq.githubdata.Adaptor.HubAdaptor;
import com.worldtechq.githubdata.R;

public class MainActivity extends AppCompatActivity {

    private  final String URL="https://api.github.com/users";// github open api
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.container);
        //recycler view layout
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // making volley request
        StringRequest stringRequest=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Code",response);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                User[] users = gson.fromJson(response,User[].class);
                recyclerView.setAdapter(new HubAdaptor(MainActivity.this,users));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //adding it to volley request queue
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
