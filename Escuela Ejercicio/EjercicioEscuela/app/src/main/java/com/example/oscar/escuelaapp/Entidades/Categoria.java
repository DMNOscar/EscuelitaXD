package com.example.oscar.escuelaapp.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Oscar on 13/06/2018.
 */

public class Categoria implements Parcelable {

    private int id;
    private String nombre;
    private boolean activo;

    public Categoria() {
    }


    public Categoria(int id, String nombre, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeByte(this.activo ? (byte) 1 : (byte) 0);
    }

    protected Categoria(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.activo = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Categoria> CREATOR = new Parcelable.Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel source) {
            return new Categoria(source);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };
}
