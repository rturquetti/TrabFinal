package com.example.rafael.trabfinal;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 28/11/2016.
 */

public class ManagerOrcamento extends AppCompatActivity {
    private ListView listOrcamento;
    private Orcamento orcamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_orcamento);

        listOrcamento = (ListView) findViewById(R.id.listOrc);

        //informar à listview para abrir o menu quando clicar
        registerForContextMenu(listOrcamento);

        listOrcamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //acesso ao adapter que foi vinculado à listview
            // a lista é uma view é necessário passá-la para ser modificada
            //posição para identificação de qual linha foi clicado
            //informar o id da listview para ser acessada
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Orcamento orcamentoClicado = (Orcamento) adapter.getItemAtPosition(posicao);
                Intent irCadastro = new Intent(ManagerOrcamento.this, CadastroOrcamento.class);
                irCadastro.putExtra("orcamentoClicado", orcamentoClicado);
                startActivity(irCadastro);
            }
        });

        listOrcamento.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
                orcamento = (Orcamento) adapter.getItemAtPosition(posicao);
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
                OrcamentoDAO dao = new OrcamentoDAO(ManagerOrcamento.this);
                PecasOrcamentoDAO dao2 = new PecasOrcamentoDAO(ManagerOrcamento.this);
                Long orcDeletar = dao.deletar(orcamento);
                dao2.deletar(orcDeletar);
                dao.close();
                dao2.close();
                atualizaLista();

                return false;
            }
        });
        super.onCreateContextMenu(menu,v, menuInfo);
    }

    public void atualizaLista(){
        OrcamentoDAO dao = new OrcamentoDAO(this);
        List<Orcamento> orcamento = dao.getLista();
        dao.close();

        ListaOrcamentoAdapter adapter = new ListaOrcamentoAdapter(orcamento,this);
        listOrcamento.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_orcamento,menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int itemClicado = item.getItemId();

        switch (itemClicado){
            case R.id.novoOrcamento:
                Intent irCadastroOrcamento = new Intent(this, CadastroOrcamento.class);
                startActivity(irCadastroOrcamento);
                break;
            case R.id.novoCli:
                Intent irCadastroCliente = new Intent(this, CadastroCliente.class);
                startActivity(irCadastroCliente);
                break;
            case R.id.novaPeca:
                Intent irCadastroPeca = new Intent(this, CadastroPeca.class);
                startActivity(irCadastroPeca);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}