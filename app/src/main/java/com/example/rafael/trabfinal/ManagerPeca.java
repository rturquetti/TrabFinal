package com.example.rafael.trabfinal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
                pegaInfo();
                //Intent irCadastro = new Intent(ManagerPeca.this, AdicionarPecaOrc.class);
                //irCadastro.putExtra("pecaClicado", pecaClicado);
                //setResult(RESULT_OK,irCadastro);
                //startActivityForResult(irCadastro);
                //finish();

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


    public void pegaInfo() {
        LayoutInflater inflater = getLayoutInflater();

        View dialoglayout = inflater.inflate(R.layout.dialog, null);

        final EditText qtdDialog = (EditText) dialoglayout.findViewById(R.id.qtdDialog);
        final EditText valorDialog = (EditText) dialoglayout.findViewById(R.id.valorDialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(ManagerPeca.this);
        builder.setView(dialoglayout);
        builder.setTitle("Informe");
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent irCadastro = new Intent(ManagerPeca.this, CadastroOrcamento.class);
                irCadastro.putExtra("pecaClicado", pecaClicado);
                setResult(RESULT_OK,irCadastro);

                pecaClicado.setQtdePeca(Integer.parseInt(String.valueOf(qtdDialog.getText().toString())));
                pecaClicado.setPreco(Double.parseDouble(String.valueOf(valorDialog.getText().toString())));

                Log.d("peçaMandada"+pecaClicado.getNomePeca(),"ret");
                Log.d("quantidadepeçaMandada"+pecaClicado.getQtdePeca(),"ret");
                Log.d("valorpeçaMandada"+pecaClicado.getPreco(),"ret");
                finish();
            }
        });
        builder.show();
    }

/*   public void pegaInfo() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setCancelable(false);
        builder.setTitle("Info");
        builder.setView(inflater.inflate(R.layout.dialog, null));

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                //valorPeca = input.getText().toString();
                //Toast.makeText(ManagerPeca.this, "positivo=" + input.getText().toString(), Toast.LENGTH_SHORT).show();




                //Log.d("quatidade passada: "+quantidadeNoDialog.getText().toString(),"//");
                //Log.d("valor passado: "+valorNoDialog.getText().toString(),"//");
//                Intent irCadastro = new Intent(ManagerPeca.this, CadastroOrcamento.class);
//                irCadastro.putExtra("pecaClicado", pecaClicado);
//                irCadastro.putExtra("quantidade",qtdePeca);
//                irCadastro.putExtra("valor",valorPeca);
//                setResult(RESULT_OK,irCadastro);
//                finish();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });


        builder.show();
    }
    */
}

