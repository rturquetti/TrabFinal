package com.example.rafael.trabfinal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

/**
 * Created by rafael on 24/11/2016.
 */

public class CadastroPeca extends AppCompatActivity {
    private CadastroPecaAuxiliar auxiliar;
    private String caminhoImg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_peca);

        Intent intent = getIntent();
        final Peca pecaParaAlterar = (Peca) intent.getSerializableExtra("pecaClicado");

        Button botaoSalvar = (Button) findViewById(R.id.buttonSalvar);
        Button botaoCancelar = (Button) findViewById(R.id.buttonCancelar);

        auxiliar = new CadastroPecaAuxiliar(this);

        if (pecaParaAlterar != null) {
            botaoSalvar.setText("Alterar");
            auxiliar.exibePeca(pecaParaAlterar);
        }

        botaoSalvar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Peca peca = auxiliar.retornaPeca();
                PecaDAO dao = new PecaDAO(CadastroPeca.this);

                if (pecaParaAlterar == null) {
                    if (auxiliar.CampoVazio()) {
                        Toast.makeText(getApplicationContext(), "Preencha os Campos", Toast.LENGTH_LONG).show();
                    } else {
                        dao.salva(peca);
                        dao.close();
                        finish();
                    }
                } else {
                    if (auxiliar.CampoVazio()) {
                        Toast.makeText(getApplicationContext(), "Preencha os Campos", Toast.LENGTH_LONG).show();
                    } else {
                        peca.setIdPeca(pecaParaAlterar.getIdPeca());
                        dao.editar(peca);
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

        final ImageView imgFotoPeca = auxiliar.getImgFotoPeca();
        imgFotoPeca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                caminhoImg = Environment.getExternalStorageDirectory().toString() + "/" + System.currentTimeMillis() + ".png";

                File arquivo = new File(caminhoImg);

                Uri localImage = Uri.fromFile(arquivo);

                irCamera.putExtra(MediaStore.EXTRA_OUTPUT, localImage);

                startActivityForResult(irCamera, 123);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 123){
            if (resultCode == Activity.RESULT_OK){
                Log.d("apssei aqui 1 "+Activity.RESULT_OK,"");
                auxiliar.carregaImagem(caminhoImg);
            }
            else {
                caminhoImg = null;
            }
        }
    }
}
