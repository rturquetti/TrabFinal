package com.example.rafael.trabfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
                Toast.makeText(getApplicationContext(), "clicando", Toast.LENGTH_LONG).show();
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
        atualizaLista();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("Remover");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                pecas.remove(pecaClicada);
                return false;
            }
        });
    }

    public void atualizaLista(){

        pecas.add(pecaClicada);

        ListaPecaOrcAdapter adapter = new ListaPecaOrcAdapter(pecas,this);

        listPecasOrc.setAdapter(adapter);


    }
}
