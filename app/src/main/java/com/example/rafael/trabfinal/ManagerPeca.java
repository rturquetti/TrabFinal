package com.example.rafael.trabfinal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 24/11/2016.
 */

public class ManagerPeca extends AppCompatActivity {
    private ListView listPeca;
    private Peca peca;
    private AlertDialog alerta;
    private Peca pecaClicado;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_peca);

        listPeca = (ListView) findViewById(R.id.listPeca);

        //informar à listview para abrir o menu quando clicar
        registerForContextMenu(listPeca);

        listPeca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                pecaClicado = (Peca) adapter.getItemAtPosition(posicao);
                exemplo_simples();
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
        MenuItem editar = menu.add("Editar");
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent irCadastro = new Intent(ManagerPeca.this, CadastroPeca.class);
                irCadastro.putExtra("pecaClicado", peca);
                startActivity(irCadastro);

                return false;
            }
        });
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

    private void exemplo_simples() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        //define o titulo
        builder.setTitle(R.string.quantidade);
        //define a mensagem
        //builder.setMessage("Qualifique este software");
        final EditText input = new EditText(this);
        builder.setView(input);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        //define um botão como positivo
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(ManagerPeca.this, "positivo=" + input.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent irCadastro = new Intent(ManagerPeca.this, CadastroOrcamento.class);
                irCadastro.putExtra("pecaClicado", pecaClicado);
                setResult(RESULT_OK,irCadastro);
                finish();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}

