package com.example.rafael.trabfinal;

import java.io.Serializable;

/**
 * Created by rafael on 24/11/2016.
 */

public class Peca implements Serializable{
    Long idPeca;
    String fotoPeca,nomePeca,marcaPeca,tipoCarro,marcaCarro,modeloCarro,anoModeloCarro;
    Double preco;

    public String toString(){
        return "nome: "+nomePeca+"id: "+idPeca;
    }

    public Long getIdPeca() {

        return idPeca;
    }

    public String getFotoPeca() {
        return fotoPeca;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public String getMarcaPeca() {
        return marcaPeca;
    }

    public String getTipoCarro() {
        return tipoCarro;
    }

    public String getMarcaCarro() {
        return marcaCarro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public String getAnoModeloCarro() {
        return anoModeloCarro;
    }

    public Double getPreco() {
        return preco;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    public void setFotoPeca(String fotoPeca) {
        this.fotoPeca = fotoPeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public void setMarcaPeca(String marcaPeca) {
        this.marcaPeca = marcaPeca;
    }

    public void setTipoCarro(String tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

    public void setMarcaCarro(String marcaCarro) {
        this.marcaCarro = marcaCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public void setAnoModeloCarro(String anoModeloCarro) {
        this.anoModeloCarro = anoModeloCarro;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
