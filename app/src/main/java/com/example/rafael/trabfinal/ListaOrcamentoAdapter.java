package com.example.rafael.trabfinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by rafael on 02/12/2016.
 */

public class ListaOrcamentoAdapter  extends BaseAdapter{

        private List<Orcamento> orcamentos;
        private Activity activity;

        public ListaOrcamentoAdapter(List<Orcamento> orcamentos, Activity activity) {
            this.orcamentos = orcamentos;
            this.activity = activity;
        }

        @Override
        public int getCount() {
            return orcamentos.size();
        }

        @Override
        public Object getItem(int position) {
            return orcamentos.get(position);
        }

        @Override
        public long getItemId(int position) {
            Orcamento orcamento = orcamentos.get(position);

            return orcamentos.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Orcamento orcamento = orcamentos.get(position);

            LayoutInflater inflater = activity.getLayoutInflater();
            View linha = inflater.inflate(R.layout.activity_linha_orcamento,null);

            TextView nomeLinhaPeca = (TextView)linha.findViewById(R.id.idOrcamento);
            nomeLinhaPeca.setText((int) orcamento.getId());
            TextView marcaLinhaPeca = (TextView)linha.findViewById(R.id.nomeCliente);
            marcaLinhaPeca.setText((int) orcamento.getId());
            TextView marcaLinhaCarro = (TextView)linha.findViewById(R.id.data);
            marcaLinhaCarro.setText((int) orcamento.getId());

            return linha;
        }

}
