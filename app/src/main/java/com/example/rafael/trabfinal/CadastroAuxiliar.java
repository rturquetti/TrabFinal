package com.example.rafael.trabfinal;

import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by rafael on 23/11/2016.
 */

public class CadastroAuxiliar {
    private TextView editCpfCli;
    private TextView editNomeCli;
    private TextView editRuaCli;
    private TextView editNumCli;
    private TextView editCompleCli;
    private TextView editBairroCli;
    private TextView editCidadeCli;
    private Spinner spinEstadoCli;
    private TextView editCepCli;
    private TextView editTelCli;
    private TextView editEmailCli;

    public CadastroAuxiliar(Cadastro cadastro) {
        editCpfCli = (TextView) cadastro.findViewById(R.id.editCpfCli);
        editNomeCli = (TextView) cadastro.findViewById(R.id.editNomeCli);
        editRuaCli = (TextView) cadastro.findViewById(R.id.editRuaCli);
        editNumCli = (TextView) cadastro.findViewById(R.id.editNumCli);
        editCompleCli = (TextView) cadastro.findViewById(R.id.editCompleCli);
        editBairroCli = (TextView) cadastro.findViewById(R.id.editBairroCli);
        editCepCli = (TextView) cadastro.findViewById(R.id.editCepCli);
        editCidadeCli = (TextView) cadastro.findViewById(R.id.editCidadeCli);
        spinEstadoCli = (Spinner) cadastro.findViewById(R.id.spinEstadoCli);
        editTelCli = (TextView) cadastro.findViewById(R.id.editTelCli);
        editEmailCli = (TextView) cadastro.findViewById(R.id.editEmailCli);
    }

    public Spinner retornaSpnner(Cadastro cadastro){
        return spinEstadoCli = (Spinner) cadastro.findViewById(R.id.spinEstadoCli);
    }

    public void exibeCliente(Cliente clienteAlterar){
        editCpfCli.setText(clienteAlterar.getCpfCli());
        editNomeCli.setText(clienteAlterar.getNomeCli());
        editRuaCli.setText(clienteAlterar.getRuaCli());
        editNumCli.setText(String.valueOf(clienteAlterar.getNumCli()));
        editCompleCli.setText(clienteAlterar.getCompleCli());
        editBairroCli.setText(clienteAlterar.getBairroCli());
        editCepCli.setText(clienteAlterar.getCepCli());
        editCidadeCli.setText(clienteAlterar.getCidadeCli());
        spinEstadoCli.setSelection(retornaEstadoPosicao(clienteAlterar.getEstadoCli()));
        editTelCli.setText(clienteAlterar.getTelCli());
        editEmailCli.setText(clienteAlterar.getEmailCli());
    }

    public Cliente retornaCliente() {
        Cliente cliente = new Cliente();

        cliente.setCpfCli(editCpfCli.getText().toString());
        cliente.setNomeCli(editNomeCli.getText().toString());
        cliente.setRuaCli(editRuaCli.getText().toString());
        cliente.setNumCli(Integer.parseInt(String.valueOf(editNumCli.getText())));
        cliente.setCompleCli(editCompleCli.getText().toString());
        cliente.setBairroCli(editBairroCli.getText().toString());
        cliente.setCidadeCli(editCidadeCli.getText().toString());
        cliente.setEstadoCli(spinEstadoCli.getSelectedItem().toString());
        cliente.setCepCli(editCepCli.getText().toString());
        cliente.setTelCli(editTelCli.getText().toString());
        cliente.setEmailCli(editEmailCli.getText().toString());

        return cliente;
    }

    public boolean CampoVazio(){
        if ((editCpfCli.getText().toString().equals(""))
                ||(editNomeCli.getText().toString().equals(""))
                ||(editRuaCli.getText().toString().equals(""))
                ||(editNumCli.getText().toString().equals(""))
                ||(editCompleCli.getText().toString().equals(""))
                ||(editBairroCli.getText().toString().equals(""))
                ||(editCidadeCli.getText().toString().equals(""))
                ||(spinEstadoCli.getSelectedItem().toString().equals("Estado"))
                ||(editCepCli.getText().toString().equals(""))
                ||(editTelCli.getText().toString().equals(""))
                ||(editEmailCli.getText().toString().equals(""))){
            return true;
        }
        else {
            return false;
        }
    }

    public int retornaEstadoPosicao(String nomeEstado){
        int valor = 0;
        switch (nomeEstado){
            case "Acre" : valor = 1;break;
            case "Alagoas" : valor = 2;break;
            case "Amapá" : valor = 3;break;
            case "Amazonas" : valor = 4;break;
            case "Bahia" : valor = 5;break;
            case "Ceará" : valor = 6;break;
            case "Distrito Federal" : valor = 7;break;
            case "Espirito Santo" : valor = 8;break;
            case "Goiás" : valor = 9;break;
            case "Maranhão" : valor = 10;break;
            case "Mato Grosso" : valor = 11;break;
            case "Mato Grosso do Sul" : valor = 12;break;
            case "Minas Gerais" : valor = 13;break;
            case "Pará" : valor = 14;break;
            case "Paraíba" : valor = 15;break;
            case "Paraná" : valor = 16;break;
            case "Pernambuco" : valor = 17;break;
            case "Piauí" : valor = 18;break;
            case "Rio de Janeiro" : valor = 19;break;
            case "Rio Grande do Norte" : valor = 20;break;
            case "Rio Grande do Sul" : valor = 21;break;
            case "Rondônia" : valor = 22;break;
            case "Roraima" : valor = 23;break;
            case "Santa Catarina" : valor = 24;break;
            case "São Paulo" : valor = 25;break;
            case "Sergipe" : valor = 26;break;
            case "Tocantins" : valor = 27;break;
        }
        return valor;
    }
}
