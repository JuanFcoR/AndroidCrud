package com.example.androidcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.StrictMode.ThreadPolicy;


import com.example.androidcrud.Interfaces.MyApiService;
import com.example.androidcrud.Models.MyApiAdapter;
import com.example.androidcrud.Models.cerveza;
import com.example.androidcrud.Models.respuesta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText edId, edNombre, edMarca;
    public cerveza oCerveza;
    respuesta oRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edId=findViewById(R.id.edId);
        edNombre=findViewById(R.id.edNombre);
        edMarca=findViewById(R.id.edMarca);

        int ID= getIntent().getIntExtra("valorId",0);
        if(ID!=0) {
            toast("Cerveza Encontrada");
            find(ID);
        }
        else
            limpiar();
    }

    public void toast(String val) {
        Toast.makeText(this, val, Toast.LENGTH_SHORT).show();
    }

    private void limpiar(){
        oCerveza= new cerveza();
        edId.setText("0");
        edNombre.setText("");
        edMarca.setText("");
    }
    private boolean validar() {
        boolean paso = true;
        try{
            Integer.parseInt(edId.getText().toString());
            paso=true;
        }catch(Exception e){
            paso=false;
            toast("El id solo puede ser numerico");
        }

        if(edNombre.getText().toString().matches("")||edMarca.getText().toString().matches("")){
            paso=false;
            toast("Nombre y/o marca no pueden estar vacios");
        }
        return paso;
    }

    private void llenarCampos(cerveza cerv) {
        edId.setText(String.valueOf(cerv.getId()));
        edNombre.setText(String.valueOf(cerv.getNombre()));
        edMarca.setText(String.valueOf(cerv.getMarca()));
    }
    private cerveza llenarClase() {
        int Id;
        String ID=edId.getText().toString();
        if(ID.matches("")) {
            Id=0;
        } else{
            Id=Integer.parseInt(ID);
        }
        String Nombre =edNombre.getText().toString();
        String Marca=edMarca.getText().toString();
        cerveza cerv = new cerveza(Id,Nombre,Marca);
        return cerv;
    }

    //Esta funcion en base al ID escrito busca si existe la cerveza en el API, de lo contrario
    private void find(int id)
    {
        //Se toma el Id
        //Se crea una clase call
        Call<cerveza> Lista = MyApiAdapter.getApiService().find(String.valueOf(id));
        Lista.enqueue(new Callback<cerveza>() {
            @Override
            public void onResponse(Call<cerveza> call, Response<cerveza> response) {
                try {
                    //Si los datos se enviaron con exito se recibe del API la cerveza
                    if(response.isSuccessful()){
                        oCerveza=response.body();
                        //Se llenan los TextView con la cerveza
                        llenarCampos(oCerveza);
                    }

                }catch (Exception ex)
                {
                    toast(ex.getMessage().toString());
                }
            }

            @Override
            public void onFailure(Call<cerveza> call, Throwable t) {
                toast(t.getMessage());
            }
        });
    }

    boolean validarId() {
        boolean paso=false;
        String miId=edId.getText().toString();
        boolean tp = tryParseInt(miId);
        try{
            if((Integer.parseInt(miId)!=0||(edId.getText().toString()!=null))&&tp) {
                find(Integer.parseInt(edId.getText().toString()));
                paso = true;
            }
            else{
                toast("El dato es invalido");
            }
        }catch (Exception nfe){
            toast(nfe.getMessage());
        }
        return paso;
    }

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //Los metodos del Crud Correspondientes a cada Boton
    public void Guardar(View view){
        if(validar())
            save();
    }
    public void Buscar(View view) {
        if(validarId())
            find(Integer.parseInt(edId.getText().toString()));
    }
    public void Eliminar(View view){
        if(validarId())
            delete(Integer.parseInt(edId.getText().toString()));
    }
    public void Listar(View view){
        Intent intent = new Intent(this,ListarActivity.class);
        startActivity(intent);
    }

    public void save() {
        oCerveza=llenarClase();
        if(!validar())
            return;
        Call<respuesta> Lista;
        if(oCerveza.getId()!=0) {
           Lista = MyApiAdapter.getApiService().edit(oCerveza);
        }
        else{
            Lista = MyApiAdapter.getApiService().save(oCerveza);
        }

        Lista.enqueue(new Callback<respuesta>() {
            @Override
            public void onResponse(Call<respuesta> call, Response<respuesta> response) {
                if(response.isSuccessful()){
                    oRespuesta=response.body();
                    if(oRespuesta.getExito()==1) {
                        limpiar();
                        toast("Guardado");
                    }
                }
            }
            @Override
            public void onFailure(Call<respuesta> call, Throwable t) {
                toast(t.getMessage());
            }
        });
    }

    private void delete(int id) {
        //Se crea una clase call
        Call<respuesta> Lista = MyApiAdapter.getApiService().delete(String.valueOf(id));
        Lista.enqueue(new Callback<respuesta>() {
            @Override
            public void onResponse(Call<respuesta> call, Response<respuesta> response) {
                if(response.isSuccessful()){
                    oRespuesta=response.body();
                    if(oRespuesta.getExito()==1) {
                        limpiar();
                        toast("Se Elimino Correctamente");
                    }
                }

            }

            @Override
            public void onFailure(Call<respuesta> call, Throwable t) {
                toast(t.getMessage());
            }
        });
    }
}