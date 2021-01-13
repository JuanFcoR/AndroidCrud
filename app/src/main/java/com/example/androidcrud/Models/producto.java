package com.example.androidcrud.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class producto {

    @SerializedName("productoId")
    @Expose
    private int productoId;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("cantidad")
    @Expose
    private BigDecimal cantidad;
    @SerializedName("precio")
    @Expose
    private BigDecimal precio;
    @SerializedName("reorden")
    @Expose
    private BigDecimal reorden;
    @SerializedName("itbis")
    @Expose
    private BigDecimal itbis;


    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getReorden() {
        return reorden;
    }

    public void setReorden(BigDecimal reorden) {
        this.reorden = reorden;
    }


    public BigDecimal getItbis() {
        return itbis;
    }

    public void setItbis(BigDecimal itbis) {
        this.itbis = itbis;
    }
}
