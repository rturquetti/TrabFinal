package com.example.rafael.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by rafael on 24/11/2016.
 */

public class ManagerPeca extends AppCompatActivity {
    private ListView listPeca;
    private Peca peca;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_peca);

        listPeca = (ListView) findViewById(R.id.listPeca);

        //informar à listview para abrir o menu quando clicar
        registerForContextMenu(listPeca);

        listPeca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Peca pecaClicado = (Peca) adapter.getItemAtPosition(posicao);
                Intent irCadastro = new Intent(ManagerPeca.this, CadastroPeca.class);
                irCadastro.putExtra("pecaClicado", pecaClicado);
                startActivity(irCadastro);
            }
        });
        listPeca.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
                peca = (Peca) adapter.getItemAtPosition(posicao);
                return false;
            }
        });
    }

    protected void onResume() {
        super.onResume();
        atualizaLista();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                PecaDAO dao = new PecaDAO(ManagerPeca.this);

                dao.deletar(peca);
                dao.close();
                atualizaLista();

                return false;
            }
        });
    }

    public void atualizaLista(){
        PecaDAO dao = new PecaDAO(this);
        List<Peca> pecas = dao.getLista();
        dao.close();

        ListaPecaAdapter adapter = new ListaPecaAdapter(pecas,this);

        listPeca.setAdapter(adapter);
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_peca,menu);

/*
        ListView listPeca = (ListView)findViewById(R.id.listPeca);

        PecaDAO dao = new PecaDAO(this);
        List<Peca> pecas = dao.getLista();
        dao.close();

        int layaout = android.R.layout.simple_list_item_1;

        ArrayAdapter<Peca> adapter = new ArrayAdapter<Peca>(this, layaout ,pecas);
        listPeca.setAdapter(adapter);
*/
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int itemClicado = item.getItemId();

        switch (itemClicado){
            case R.id.novaPeca:
                Intent irCadastro = new Intent(this, CadastroPeca.class);
                startActivity(irCadastro);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

