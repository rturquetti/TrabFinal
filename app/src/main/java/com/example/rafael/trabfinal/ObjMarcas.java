package com.example.rafael.trabfinal;

import java.io.Serializable;

/**
 * Created by rafael on 26/11/2016.
 */

public class ObjMarcas implements Serializable{
    String name, fipe_name, order, key, id;

    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public String getFipe_name() {
        return fipe_name;
    }

    public String getOrder() {
        return order;
    }

    public String getKey() {
        return key;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFipe_name(String fipe_name) {
        this.fipe_name = fipe_name;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setId(String id) {
        this.id = id;
    }
}
