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

            TextView idOrcamento = (TextView)linha.findViewById(R.id.idOrcamento);
            idOrcamento.setText(String.valueOf(orcamento.getId()));
            TextView nomeCliente = (TextView)linha.findViewById(R.id.nomeCliente);
            nomeCliente.setText(String.valueOf(orcamento.getIdCliente()));
            TextView dataHora = (TextView)linha.findViewById(R.id.dataHora);
            dataHora.setText(orcamento.getDataHora());

            return linha;
        }

}
