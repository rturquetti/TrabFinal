package com.example.rafael.trabfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by rafael on 27/11/2016.
 */

public class ManagerAdapterTipo extends Activity{
    ProgressBar mProgressBar;
    ListView listTipos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_peca);

        listTipos = (ListView) findViewById(R.id.listPeca);

        String[] tipos = {"carros", "motos", "caminhoes"};

        ArrayList<String> tiposVeiculos = new ArrayList<String>();
        int layaout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManagerAdapterTipo.this, layaout, tipos);
        listTipos.setAdapter(adapter);

        listTipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //acesso ao adapter que foi vinculado à listview
            // a lista é uma view é necessário passá-la para ser modificada
            //posição para identificação de qual linha foi clicado
            //informar o id da listview para ser acessada
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                String tipoClicado = (String) adapter.getItemAtPosition(posicao);
                Intent irCadastro = new Intent(ManagerAdapterTipo.this, CadastroPeca.class);
                irCadastro.putExtra("tipoClicado", tipoClicado);
                setResult(RESULT_OK, irCadastro);
                finish();
            }
        });
    }
}
