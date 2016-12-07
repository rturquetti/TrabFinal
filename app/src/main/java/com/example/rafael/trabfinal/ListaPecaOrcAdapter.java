package com.example.rafael.trabfinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by rafael on 05/12/2016.
 */

public class ListaPecaOrcAdapter extends BaseAdapter{
    private List<Peca> pecas;
    private Activity activity;

    public ListaPecaOrcAdapter(List<Peca> pecas, Activity activity) {
        this.pecas = pecas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return pecas.size();
    }

    @Override
    public Object getItem(int position) {
        return pecas.get(position);
    }

    @Override
    public long getItemId(int position) {
        Peca peca = pecas.get(position);

        return pecas.get(position).getIdPeca();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Peca peca = pecas.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        View linha = inflater.inflate(R.layout.activity_linha_prod_orc,null);

        TextView qtdeLinhaOrc = (TextView)linha.findViewById(R.id.qtdePecaOrc);
        qtdeLinhaOrc.setText("");
        TextView nomeLinhaOrc = (TextView)linha.findViewById(R.id.nomePecaOrc);
        nomeLinhaOrc.setText(peca.getNomePeca());
        TextView valorLinhaOrc = (TextView)linha.findViewById(R.id.valorPecaOrc);
        valorLinhaOrc.setText(peca.getPreco().toString());

        return linha;
    }

}
