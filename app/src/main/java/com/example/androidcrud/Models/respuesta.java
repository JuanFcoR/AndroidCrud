package com.example.androidcrud.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class respuesta {

    @SerializedName("exito")
    @Expose
    private int exito;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("data")
    @Expose
    private List<cerveza> data = null;

    public final int getExito() {
        return exito;
    }

    public final void setExito(int exito) {
        this.exito = exito;
    }

    public final String getMensaje() {
        return mensaje;
    }

    public final void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<cerveza>  getData() {
        return data;
    }

    public final void setData(List<cerveza> data) {
        this.data = data;
    }

    public respuesta()
    {
        this.mensaje="";
        this.exito=1;

    }

}
