package com.example.rafael.trabfinal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by rafael on 28/11/2016.
 */

public class CadastroOrcamento extends AppCompatActivity {
    private CadastroOrcamentoAuxiliar auxiliar;
    ListView listPecasOrc;
    ArrayList<Peca> pecas = new ArrayList<Peca>();
    ManagerPecaOrcAdapter linhaOrc = new ManagerPecaOrcAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento);

        Intent intent = getIntent();
        final Orcamento orcamentoParaAlterar = (Orcamento) intent.getSerializableExtra("orcamentoClicado");

        TextView editDataHora = (TextView) findViewById(R.id.textDataHora);
        EditText editClienteOrc = (EditText) findViewById(R.id.editClienteOrc);
        listPecasOrc = (ListView) findViewById(R.id.listPecasOrc);
        EditText editPrecoOrc = (EditText) findViewById(R.id.editPrecoOrc);
        ImageView imgAddPeca = (ImageView) findViewById(R.id.imgAddPeca);
        Button botaoSalvar = (Button) findViewById(R.id.buttonSalvar);
        Button botaoCancelar = (Button) findViewById(R.id.buttonCancelar);

        auxiliar = new CadastroOrcamentoAuxiliar(this);

        if (orcamentoParaAlterar != null) {
            botaoSalvar.setText("Alterar");
            auxiliar.exibeOrcamento(orcamentoParaAlterar);
        }


        editClienteOrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irLista = new Intent(CadastroOrcamento.this, ManagerCliente.class);
                startActivityForResult(irLista, 1112);
            }
        });

        imgAddPeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irLista = new Intent(CadastroOrcamento.this, ManagerPeca.class);
                startActivityForResult(irLista, 1113);
            }
        });

        botaoSalvar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Orcamento orcamento = auxiliar.retornaOrcamento();
                OrcamentoDAO dao = new OrcamentoDAO(CadastroOrcamento.this);

                if (orcamentoParaAlterar == null) {
                    if (auxiliar.CampoVazio()) {
                        Toast.makeText(getApplicationContext(), "Preencha os Campos", Toast.LENGTH_LONG).show();
                    } else {
                        dao.salva(orcamento);
                        dao.close();
                        finish();
                    }
                } else {
                    if (auxiliar.CampoVazio()) {
                        Toast.makeText(getApplicationContext(), "Preencha os Campos", Toast.LENGTH_LONG).show();
                    } else {
                        orcamento.setId(orcamentoParaAlterar.getId());
                        dao.editar(orcamento);
                        finish();
                    }
                }
            }
        });

        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1112){
            if (resultCode == Activity.RESULT_OK){
                Cliente clienteClicado = (Cliente) data.getSerializableExtra("clienteClicado");

                auxiliar.carregaCliente(clienteClicado.getNomeCli().toString());
            }
        }
        if(requestCode == 1113){
            if (resultCode == Activity.RESULT_OK){
                Peca pecaClicada = (Peca) data.getSerializableExtra("pecaClicado");
                linhaOrc.atualizaLista(listPecasOrc, pecaClicada);
                /*
                Log.d("peçaClicada"+pecaClicada,"ret");

                pecas.add(pecaClicada);

                ListaPecaOrcAdapter adapter = new ListaPecaOrcAdapter(pecas,this);

                listPecasOrc.setAdapter(adapter);
*/
            }
        }
    }
}

