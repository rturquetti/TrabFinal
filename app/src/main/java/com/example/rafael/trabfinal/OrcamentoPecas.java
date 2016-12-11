package com.example.rafael.trabfinal;

import java.io.Serializable;

/**
 * Created by rafael on 10/12/2016.
 */

public class OrcamentoPecas implements Serializable {
    Long id, idOrcamento, idPeca;
    int quantidadePeca;
    Double valorPeca;

    public Long getId() {
        return id;
    }

    public Long getIdOrcamento() {
        return idOrcamento;
    }

    public Long getIdPeca() {
        return idPeca;
    }

    public int getQuantidadePeca() {
        return quantidadePeca;
    }

    public Double getValorPeca() {
        return valorPeca;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    public void setQuantidadePeca(int quantidadePeca) {
        this.quantidadePeca = quantidadePeca;
    }

    public void setValorPeca(Double valorPeca) {
        this.valorPeca = valorPeca;
    }
}
