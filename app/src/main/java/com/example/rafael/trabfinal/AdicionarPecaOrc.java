package com.example.rafael.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rafael on 08/12/2016.
 */

public class AdicionarPecaOrc extends AppCompatActivity {
    private  AdicionarPecaOrcAuxiliar auxiliar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_peca_orc);

        Intent intent = getIntent();
        final Peca pecaParaAdicionar = (Peca) intent.getSerializableExtra("pecaClicado");

        ImageView imgFotoPeca = (ImageView) findViewById(R.id.imgFotoPeca);
        TextView nomePecaOrcamento = (TextView) findViewById(R.id.nomePecaOrcamento);
        TextView marcaPecaOrcamento = (TextView) findViewById(R.id.marcaPecaOrcamento);
        final EditText addQtdePecaOrcamento = (EditText) findViewById(R.id.addQtdePecaOrcamento);
        final EditText addPrecoPecaOrcamento = (EditText) findViewById(R.id.addPrecoPecaOrcamento);
        Button btAdicionarOrc = (Button) findViewById(R.id.btAdicionarOrc);
        Button btCacelarOrc = (Button) findViewById(R.id.btCacelarOrc);

        auxiliar = new AdicionarPecaOrcAuxiliar(this);
        auxiliar.exibePeca(pecaParaAdicionar);
        auxiliar.carregaImagem(pecaParaAdicionar.getFotoPeca());


        btCacelarOrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btAdicionarOrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pecaParaAdicionar.setQtdePeca(Integer.parseInt(String.valueOf(addQtdePecaOrcamento.getText().toString())));
                pecaParaAdicionar.setPreco(Double.parseDouble(String.valueOf(addPrecoPecaOrcamento.getText().toString())));
                Intent paraOrcamento = new Intent(AdicionarPecaOrc.this,CadastroOrcamento.class);

                Log.d("peçaMandada"+pecaParaAdicionar,"ret");
                Log.d("quantidadepeçaMandada"+pecaParaAdicionar.getQtdePeca(),"ret");
                Log.d("valorpeçaMandada"+pecaParaAdicionar.getPreco(),"ret");

                paraOrcamento.putExtra("pecaClicado",pecaParaAdicionar);
                setResult(RESULT_OK,paraOrcamento);
                finish();
            }
        });
    }

}
