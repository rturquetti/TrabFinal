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

public class ManagerAdapterMarca extends Activity {
    MarcaTask mTask;
    ProgressBar mProgressBar;
    ListView listMarcas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_peca);

        listMarcas = (ListView)findViewById(R.id.listPeca);
        iniciarDownload();

        listMarcas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //acesso ao adapter que foi vinculado à listview
            // a lista é uma view é necessário passá-la para ser modificada
            //posição para identificação de qual linha foi clicado
            //informar o id da listview para ser acessada
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                ObjMarcas marcaClicado = (ObjMarcas) adapter.getItemAtPosition(posicao);
                Intent irCadastro = new Intent(ManagerAdapterMarca.this, CadastroPeca.class);
                irCadastro.putExtra("marcaClicado", marcaClicado);
                Log.d("RETORNO LISTA"+marcaClicado.name.toString(),"+");
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
            mTask = new MarcaTask();
            mTask.execute();
        }
    }

    class MarcaTask extends AsyncTask<Void, Void, List<ObjMarcas>> {
        private ProgressDialog progress;
        @Override
        protected  void onPreExecute ()
        {
            super.onPreExecute();
            progress = ProgressDialog.show(ManagerAdapterMarca.this,"Aguarde...","Recebendo Dados",true,true);
        }

        @Override
        protected List<ObjMarcas> doInBackground(Void... params){
            return PecaInfoHttp.carregarObjMarcasJson();
        }

        @Override
        protected void onPostExecute(List<ObjMarcas> marcas)
        {
            progress.dismiss();

            ArrayList<ObjMarcas>marcasDeCarros = new ArrayList<ObjMarcas>();
            marcasDeCarros.addAll(marcas);
            int layaout = android.R.layout.simple_list_item_1;
            ArrayAdapter<ObjMarcas> adapter = new ArrayAdapter<ObjMarcas>(ManagerAdapterMarca.this, layaout ,marcasDeCarros);
            listMarcas.setAdapter(adapter);
        }
    }

}

