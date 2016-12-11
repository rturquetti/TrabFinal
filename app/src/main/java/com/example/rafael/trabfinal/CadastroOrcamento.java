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
import java.util.List;

/**
 * Created by rafael on 28/11/2016.
 */

public class CadastroOrcamento extends AppCompatActivity {
    private CadastroOrcamentoAuxiliar auxiliar;
    ListView listPecasOrc;
    private TextView textPrecoTotalOrc,textPrecoOrc;
    ArrayList<Peca> pecas = new ArrayList<Peca>();
    Cliente clienteClicado;
    ArrayList<Cliente> clienteOrcamento = new ArrayList<Cliente>();

    List<Peca> listPecasMandar;
    List<Peca> listPecasAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento);

        Intent intent = getIntent();
        final Orcamento orcamentoParaAlterar = (Orcamento) intent.getSerializableExtra("orcamentoClicado");

        TextView editDataHora = (TextView) findViewById(R.id.textDataHora);
        EditText editClienteOrc = (EditText) findViewById(R.id.editClienteOrc);
        listPecasOrc = (ListView) findViewById(R.id.listPecasOrc);
        textPrecoTotalOrc = (TextView) findViewById(R.id.textPrecoTotalOrc);
        ImageView imgAddPeca = (ImageView) findViewById(R.id.imgAddPeca);
        textPrecoOrc = (TextView) findViewById(R.id.textPrecoOrc);
        Button botaoSalvar = (Button) findViewById(R.id.buttonSalvar);
        Button botaoCancelar = (Button) findViewById(R.id.buttonCancelar);


        auxiliar = new CadastroOrcamentoAuxiliar(this);


        if (orcamentoParaAlterar != null) {
            Double valorTotal = null;
            botaoSalvar.setText("Alterar");

            ClienteDAO clienteOrc = new ClienteDAO(CadastroOrcamento.this);
            PecasOrcamentoDAO montandoListaOrcamento = new PecasOrcamentoDAO(CadastroOrcamento.this);
            PecaDAO listaPecasMontada = new PecaDAO(CadastroOrcamento.this);

            clienteOrcamento = (ArrayList<Cliente>) clienteOrc.getNome(orcamentoParaAlterar.getIdCliente());

            listPecasMandar = listaPecasMontada.getLista();
            listPecasAdd = montandoListaOrcamento.getListaOrcPreenchida(listPecasMandar,orcamentoParaAlterar.getId());

            ListaPecaOrcAdapter adapter = new ListaPecaOrcAdapter(listPecasAdd,this);
            listPecasOrc.setAdapter(adapter);

            clienteOrc.close();
            montandoListaOrcamento.close();
            listaPecasMontada.close();

            auxiliar.exibeOrcamento(orcamentoParaAlterar,clienteOrcamento,listPecasAdd);
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
                Orcamento orcamento = auxiliar.retornaOrcamento(clienteClicado);
                OrcamentoDAO daoOrc = new OrcamentoDAO(CadastroOrcamento.this);
                PecasOrcamentoDAO daoPecaOrc = new PecasOrcamentoDAO(CadastroOrcamento.this);

                if (orcamentoParaAlterar == null) {
                    if (auxiliar.CampoVazio()) {
                        Toast.makeText(getApplicationContext(), "Preencha os Campos", Toast.LENGTH_LONG).show();
                    } else {
                        Long orcGravado = daoOrc.salva(orcamento);
                        daoOrc.close();
                        daoPecaOrc.salva(pecas,orcGravado);
                        daoPecaOrc.close();
                        Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else {
                    if (auxiliar.CampoVazio()) {
                        Toast.makeText(getApplicationContext(), "Preencha os Campos", Toast.LENGTH_LONG).show();
                    } else {
                        orcamento.setId(orcamentoParaAlterar.getId());
                        daoOrc.editar(orcamento);
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
                clienteClicado = (Cliente) data.getSerializableExtra("clienteClicado");

                auxiliar.carregaCliente(clienteClicado.getNomeCli().toString());
            }
        }
        if(requestCode == 1113){
            if (resultCode == Activity.RESULT_OK){
                Peca pecaClicada = (Peca) data.getSerializableExtra("pecaClicado");
                //String quantidade = data.getStringExtra("quantidade");
                //String valor = data.getStringExtra("valor");
                //linhaOrc.atualizaLista(listPecasOrc, pecaClicada);
                /**/
                Log.d("peçaClicada"+pecaClicada,"ret");
                Log.d("quantidadepeçaClicada"+pecaClicada.getQtdePeca(),"ret");
                Log.d("valorpeçaClicada"+pecaClicada.getPreco(),"ret");

                pecas.add(pecaClicada);

                int pecaQuantidade = 0;
                Double pecaValorUni = 0.0;
                Double pecavalorTotal = 0.0;
                for(int i=0;i<pecas.size();i++){
                    pecaQuantidade = pecas.get(i).getQtdePeca();
                    pecaValorUni = pecas.get(i).getPreco();
                    pecavalorTotal += pecaQuantidade *pecaValorUni;
                }

                Log.d("valor total: "+pecavalorTotal.toString(),"ret");

                ListaPecaOrcAdapter adapter = new ListaPecaOrcAdapter(pecas,this);

                listPecasOrc.setAdapter(adapter);

                //editPrecoOrc.setText(pecavalorTotal.toString());
                auxiliar.carregaPreco(String.valueOf(pecavalorTotal));

            }
        }
    }
}

