package com.example.rafael.trabfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by rafael on 05/12/2016.
 */

public class ManagerPecaOrcAdapter extends AppCompatActivity {
    private ListView listPecasOrc;
    private Peca pecaClicada;
    ArrayList<Peca> pecas = new ArrayList<Peca>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento);

        listPecasOrc = (ListView) findViewById(R.id.listPecasOrc);

        //informar à listview para abrir o menu quando clicar
        registerForContextMenu(listPecasOrc);

        listPecasOrc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                pecaClicada = (Peca) adapter.getItemAtPosition(posicao);
            }
        });
        listPecasOrc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
                pecaClicada = (Peca) adapter.getItemAtPosition(posicao);
                return false;
            }
        });
    }

    protected void onResume() {
        super.onResume();
        atualizaLista(listPecasOrc,pecaClicada);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("Remover");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });
    }

    public void atualizaLista(ListView listPecasOrc, Peca pecaClicada){
/*        Intent addPeca = getIntent();
        Peca pecaParaAdd = (Peca) addPeca.getSerializableExtra("pecaClicado");

        Log.d("peçaClicada"+pecaParaAdd,"ret");

        ArrayList<Peca> pecas = new ArrayList<Peca>();
        pecas.add(pecaParaAdd);

        ListaPecaAdapter adapter = new ListaPecaAdapter(pecas,this);

        listPecaOrc.setAdapter(adapter);


*/



        pecas.add(pecaClicada);

        ListaPecaOrcAdapter adapter = new ListaPecaOrcAdapter(pecas,this);

        listPecasOrc.setAdapter(adapter);


    }
}