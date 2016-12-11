package com.example.rafael.trabfinal;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rafael on 03/12/2016.
 */

public class CadastroOrcamentoAuxiliar {
        private TextView textDataHora;
        private EditText editClienteOrc;
        private ListView listPecasOrc;
        private TextView textPrecoTotalOrc;


        public CadastroOrcamentoAuxiliar(CadastroOrcamento cadastroOrcamento) {
            textDataHora = (TextView) cadastroOrcamento.findViewById(R.id.textDataHora);
            textDataHora.setText(retornaDataHora());
            editClienteOrc = (EditText) cadastroOrcamento.findViewById(R.id.editClienteOrc);
            listPecasOrc = (ListView) cadastroOrcamento.findViewById(R.id.listPecasOrc);
            textPrecoTotalOrc = (TextView) cadastroOrcamento.findViewById(R.id.textPrecoTotalOrc);
        }

        public void exibeOrcamento(Orcamento orcamentoAlterar, Cliente nomeClienteOrc, List<Peca> listPecasAdd){
            editClienteOrc.setText(nomeClienteOrc.getNomeCli());
            textDataHora.setText(orcamentoAlterar.getDataHora());
            carregaPreco(String.valueOf(getValorTotal(listPecasAdd)));
        }

        public Orcamento retornaOrcamento(Cliente clienteOrcamento) {
            Orcamento orcamento = new Orcamento();

            orcamento.setDataHora(textDataHora.getText().toString());
            orcamento.setIdCliente(clienteOrcamento.getId());

            return orcamento;
        }


    public String retornaDataHora() {
        Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH) + 1;
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int hora = c.get(Calendar.HOUR);
        int minuto = c.get(Calendar.MINUTE);

        String stDia, stMes ,stAno, stHora, stMinuto;
        if (mes < 10){
            stMes = "0"+String.valueOf(mes);
        }
        else {
            stMes = String.valueOf(mes);
        }
        if (dia < 10){
            stDia = "0"+String.valueOf(dia);
        }
        else {
            stDia = String.valueOf(dia);
        }
        if (hora < 10){
            stHora = "0"+String.valueOf(hora);
        }
        else {
            stHora = String.valueOf(hora);
        }
        if (minuto < 10){
            stMinuto = "0"+String.valueOf(minuto);
        }
        else {
            stMinuto = String.valueOf(minuto);
        }

        stAno = String.valueOf(ano);

        return stDia+"/"+stMes+"/"+stAno+"   "+stHora+":"+stMinuto;
    }

    public boolean CampoVazio(){
            if ((editClienteOrc.getText().toString().equals(""))){
                return true;
            }
            else {
                return false;
            }
        }

    public Double getValorTotal(List<Peca> pecas){
        Double valor=0.0;

        for(int i = 0; i < pecas.size(); i++ ) {
            Peca cacaPeca = pecas.get(i);
            valor += (cacaPeca.getQtdePeca()) * (cacaPeca.getPreco());
        }
        return valor;
    }

    public void carregaCliente(String clienteOrc){
        editClienteOrc.setText(clienteOrc);
    }

    public void carregaPreco(String valorTotal){
        textPrecoTotalOrc.setText(valorTotal);
    }
}
