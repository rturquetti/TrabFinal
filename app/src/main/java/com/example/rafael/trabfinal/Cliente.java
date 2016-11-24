package com.example.rafael.trabfinal;

import java.io.Serializable;

/**
 * Created by rafael on 23/11/2016.
 */

public class Cliente implements Serializable {
    private Long id;
    private String cpfCli,nomeCli,ruaCli, compleCli, bairroCli,cidadeCli,estadoCli,cepCli,telCli,emailCli;
    private int numCli;

    public String toString(){
        return "nome: "+nomeCli+"id: "+id +" estado: "+ estadoCli;
    }

    public Long getId() {
        return id;
    }

    public String getCpfCli() {
        return cpfCli;
    }

    public String getNomeCli() {
        return nomeCli;
    }

    public String getRuaCli() {
        return ruaCli;
    }

    public String getCompleCli() {
        return compleCli;
    }

    public String getBairroCli() {
        return bairroCli;
    }

    public String getCidadeCli() {
        return cidadeCli;
    }

    public String getEstadoCli() {
        return estadoCli;
    }

    public String getCepCli() {
        return cepCli;
    }

    public String getTelCli() {
        return telCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public int getNumCli() {
        return numCli;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCpfCli(String cpfCli) {
        this.cpfCli = cpfCli;
    }

    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
    }

    public void setRuaCli(String ruaCli) {
        this.ruaCli = ruaCli;
    }

    public void setCompleCli(String compleCli) {
        this.compleCli = compleCli;
    }

    public void setBairroCli(String bairroCli) {
        this.bairroCli = bairroCli;
    }

    public void setCidadeCli(String cidadeCli) {
        this.cidadeCli = cidadeCli;
    }

    public void setEstadoCli(String estadoCli) {
        this.estadoCli = estadoCli;
    }

    public void setCepCli(String cepCli) {
        this.cepCli = cepCli;
    }

    public void setTelCli(String telCli) {
        this.telCli = telCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public void setNumCli(int numCli) {
        this.numCli = numCli;
    }


}