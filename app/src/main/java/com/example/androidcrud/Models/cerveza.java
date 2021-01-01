package com.example.androidcrud.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class cerveza {

    @SerializedName("id")
    @Expose
    private  int id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("marca")
    @Expose
    private String marca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public cerveza(){
        this.id=0;
        this.nombre="";
        this.marca="";
    }

    public cerveza(String Nombre, String Marca){
        this.id=0;
        this.nombre=Nombre;
        this.marca=Marca;
    }

    public cerveza(int Id, String Nombre, String Marca){
        this.id=Id;
        this.nombre=Nombre;
        this.marca=Marca;
    }
}
