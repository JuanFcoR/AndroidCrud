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
    @SerializedName("cervezaData")
    @Expose
    private List<cerveza> cervezaData = null;

    @SerializedName("productoData")
    @Expose
    private List<producto> productoData = null;

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

    public List<cerveza>  getCervezaData() {
        return cervezaData;
    }

    public final void setCervezaData(List<cerveza> data) {
        this.cervezaData = data;
    }

    public List<producto>  getProductoData() {
        return productoData;
    }

    public final void setProductoData(List<producto> data) {
        this.productoData = data;
    }

    public respuesta()
    {
        this.mensaje="";
        this.exito=1;

    }

}
