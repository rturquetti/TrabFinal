package com.example.rafael.trabfinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rafael on 24/11/2016.
 */

public class ListaClienteAdapter extends BaseAdapter{

    private List<Cliente> clientes;
    private Activity activity;

    public ListaClienteAdapter(List<Cliente> clientes, Activity activity) {
        this.clientes = clientes;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int position) {
        return clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        Cliente cliente = clientes.get(position);

        return clientes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cliente cliente = clientes.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        View linha = inflater.inflate(R.layout.activity_linha_cliente,null);

        TextView textViewNomeCliente = (TextView)linha.findViewById(R.id.textViewNomeCliente);
        textViewNomeCliente.setText(cliente.getNomeCli());
        TextView textViewTelCliente = (TextView)linha.findViewById(R.id.textViewTelCliente);
        textViewTelCliente.setText(cliente.getTelCli());
        TextView testViewEmailCliente = (TextView)linha.findViewById(R.id.testViewEmailCliente);
        testViewEmailCliente.setText(cliente.getEmailCli());

        return linha;
    }
}
