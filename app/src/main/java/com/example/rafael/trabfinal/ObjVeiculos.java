package com.example.rafael.trabfinal;

import java.io.Serializable;

/**
 * Created by rafael on 27/11/2016.
 */

public class ObjVeiculos implements Serializable {
    String fipe_marca,name,marca,id,fipe_name;

    public String toString(){
        return fipe_name;
    }

    public ObjVeiculos(String fipe_marca, String name, String marca, String id, String fipe_name) {
        this.fipe_marca = fipe_marca;
        this.name = name;
        this.marca = marca;
        this.id = id;
        this.fipe_name = fipe_name;
    }

    public String getFipe_marca() {
        return fipe_marca;
    }

    public String getName() {
        return name;
    }

    public String getMarca() {
        return marca;
    }

    public String getId() {
        return id;
    }

    public String getFipe_name() {
        return fipe_name;
    }

    public void setFipe_marca(String fipe_marca) {
        this.fipe_marca = fipe_marca;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFipe_name(String fipe_name) {
        this.fipe_name = fipe_name;
    }
}
