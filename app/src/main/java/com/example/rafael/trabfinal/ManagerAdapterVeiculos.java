package com.example.rafael.trabfinal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 26/11/2016.
 */

public class ManagerAdapterVeiculos extends Activity {
    ModeloTask mTask;
    ProgressBar mProgressBar;
    ListView listVeiculos;
    String tipo;
    ObjMarcas marca;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_peca);

        listVeiculos = (ListView)findViewById(R.id.listPeca);

        Intent irHttp = getIntent();
        tipo = irHttp.getExtras().getString("tipoClicado");
        marca = (ObjMarcas) irHttp.getSerializableExtra("marcaClicado");
        iniciarDownload();

        listVeiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //acesso ao adapter que foi vinculado à listview
            // a lista é uma view é necessário passá-la para ser modificada
            //posição para identificação de qual linha foi clicado
            //informar o id da listview para ser acessada
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                ObjVeiculos veiculoClicado = (ObjVeiculos) adapter.getItemAtPosition(posicao);
                Intent irCadastro = new Intent(ManagerAdapterVeiculos.this, CadastroPeca.class);
                irCadastro.putExtra("veiculoClicado", veiculoClicado);
                setResult(RESULT_OK,irCadastro);
                finish();
            }
        });

    }

    private void exibirProgress(boolean exibir){
        mProgressBar.setVisibility(exibir? View.VISIBLE : View.GONE);

    }

    public void iniciarDownload(){
        if(mTask == null || mTask.getStatus() != AsyncTask.Status.RUNNING){
            mTask = new ModeloTask();
            mTask.execute();
        }
    }

    class ModeloTask extends AsyncTask<Void, Void, List<ObjVeiculos>> {
        private ProgressDialog progress;
        @Override
        protected  void onPreExecute ()
        {
            super.onPreExecute();
            progress = ProgressDialog.show(ManagerAdapterVeiculos.this,"Aguarde...","Recebendo Dados",true,true);
        }

        @Override
        protected List<ObjVeiculos> doInBackground(Void... params){
            return VeiculoInfoHttp.carregarObjVeiculosJson(tipo,marca);
        }

        @Override
        protected void onPostExecute(List<ObjVeiculos> veiculos)
        {
            progress.dismiss();

            ArrayList<ObjVeiculos>nomeVeiculos = new ArrayList<ObjVeiculos>();
            nomeVeiculos.addAll(veiculos);
            int layaout = android.R.layout.simple_list_item_1;
            ArrayAdapter<ObjVeiculos> adapter = new ArrayAdapter<ObjVeiculos>(ManagerAdapterVeiculos.this, layaout ,nomeVeiculos);
            listVeiculos.setAdapter(adapter);
        }
    }

}

