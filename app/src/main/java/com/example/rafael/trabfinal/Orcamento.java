package com.example.rafael.trabfinal;

import java.io.Serializable;

/**
 * Created by rafael on 02/12/2016.
 */

public class Orcamento implements Serializable {
    long id,idCliente;
    String dataHora;

    @Override
    public String toString() {
        return "id=" + id +", idCliente=" + idCliente;
    }



    public long getId() {
        return id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
