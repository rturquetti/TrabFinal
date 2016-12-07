package com.example.rafael.trabfinal;

import java.io.Serializable;

/**
 * Created by rafael on 02/12/2016.
 */

public class Orcamento implements Serializable {
    long id,idCliente, idProdutoOrc;
    String dataHora;

    @Override
    public String toString() {
        return "Orcamento{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", idProdutoOrc=" + idProdutoOrc +
                '}';
    }



    public long getId() {
        return id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public long getIdProdutoOrc() {
        return idProdutoOrc;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setIdProdutoOrc(long idProdutoOrc) {
        this.idProdutoOrc = idProdutoOrc;
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
