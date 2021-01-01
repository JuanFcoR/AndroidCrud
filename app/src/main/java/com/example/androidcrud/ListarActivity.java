package com.example.androidcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidcrud.Models.CervezaAdapter;
import com.example.androidcrud.Models.MyApiAdapter;
import com.example.androidcrud.Models.cerveza;
import com.example.androidcrud.Models.respuesta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarActivity extends AppCompatActivity implements Callback<respuesta> {
    RecyclerView rvCervezas;
    CervezaAdapter adapter;
    FloatingActionButton fabAdd;
    private final String baseUrl="https://webserviceblazorcrud.azurewebsites.net/";
    respuesta respuestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        iniciarControles();
        Call<respuesta> Lista = MyApiAdapter.getApiService().getCervezas();
        Lista.enqueue(this);


    }
    private void iniciarControles()
    {
        rvCervezas = (RecyclerView) findViewById(R.id.rvCervezas);
        //fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
    }

    @Override
    public void onResponse(Call<respuesta> call, Response<respuesta> response) {
        if(response.isSuccessful())
        {
            respuesta laRespuesta = response.body();
            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
            rvCervezas.setLayoutManager(llm);

            adapter = new CervezaAdapter(laRespuesta.getData());
            rvCervezas.setAdapter(adapter);

            DividerItemDecoration itemDecoration = new DividerItemDecoration(rvCervezas.getContext(),llm.getOrientation());
            rvCervezas.addItemDecoration(itemDecoration);

            rvCervezas.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(ListarActivity.this, "hubo un error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<respuesta> call, Throwable t) {

    }
}