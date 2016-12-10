package com.example.rafael.trabfinal;

import java.io.Serializable;

/**
 * Created by rafael on 09/12/2016.
 */

public class PecaOrcamento implements Serializable {
    Long id,idOrcamento, IdProduto;
    int qtdePecaOrc;
    Double precoPecaOrc;

    public Long getId() {
        return id;
    }

    public Long getIdOrcamento() {
        return idOrcamento;
    }

    public Long getIdProduto() {
        return IdProduto;
    }

    public int getQtdePecaOrc() {
        return qtdePecaOrc;
    }

    public Double getPrecoPecaOrc() {
        return precoPecaOrc;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public void setIdProduto(Long idProduto) {
        IdProduto = idProduto;
    }

    public void setQtdePecaOrc(int qtdePecaOrc) {
        this.qtdePecaOrc = qtdePecaOrc;
    }

    public void setPrecoPecaOrc(Double precoPecaOrc) {
        this.precoPecaOrc = precoPecaOrc;
    }
}
