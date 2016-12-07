package com.example.rafael.trabfinal;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rafael on 03/12/2016.
 */

public class CadastroOrcamentoAuxiliar {
        private TextView textDataHora;
        private EditText editClienteOrc;
        private ListView listPecasOrc;
        private EditText editPrecoOrc;


        public CadastroOrcamentoAuxiliar(CadastroOrcamento cadastroOrcamento) {
            textDataHora = (TextView) cadastroOrcamento.findViewById(R.id.textDataHora);
            String dataHora = retornaDataHora();
            textDataHora.setText(dataHora);
            editClienteOrc = (EditText) cadastroOrcamento.findViewById(R.id.editClienteOrc);
            listPecasOrc = (ListView) cadastroOrcamento.findViewById(R.id.listPecasOrc);
            editPrecoOrc = (EditText) cadastroOrcamento.findViewById(R.id.editPrecoOrc);
        }

        public void exibeOrcamento(Orcamento orcamentoAlterar){
            editClienteOrc.setText(String.valueOf(orcamentoAlterar.getId()));
            textDataHora.setText(orcamentoAlterar.getDataHora());
            //listPecasOrc.setText(orcamentoAlterar.getNomeCli());
            //editPrecoOrc.setText(orcamentoAlterar.getRuaCli());
        }

        public Orcamento retornaOrcamento() {
            Orcamento orcamento = new Orcamento();

            //cliente.setEmailCli(editEmailCli.getText().toString());

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
        stDia = String.valueOf(dia);
        stMes = String.valueOf(mes);
        stAno = String.valueOf(ano);
        stHora = String.valueOf(hora);
        stMinuto = String.valueOf(minuto);

        return stDia+"/"+stMes+"/"+stAno+" - "+stHora+":"+stMinuto;
    }

    public boolean CampoVazio(){
            if ((editClienteOrc.getText().toString().equals(""))){
                return true;
            }
            else {
                return false;
            }
        }

    public void carregaCliente(String clienteOrc){
        editClienteOrc.setText(clienteOrc);
    }
}
