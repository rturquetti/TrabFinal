package com.example.rafael.trabfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by rafael on 24/11/2016.
 */

public class CadastroPecaAuxiliar {
    private ImageView imgFotoPeca;
    private TextView editNomePeca;
    private TextView editMarcaPeca;
    private TextView editTipoCarro;
    private TextView editMarcaCarro;
    private TextView editModeloCarro;
    private TextView editAnoModeloCarro;
    private TextView editPrecoPeca;
    private Peca peca;


    public CadastroPecaAuxiliar(CadastroPeca cadastroPeca) {
        imgFotoPeca = (ImageView) cadastroPeca.findViewById(R.id.imgFotoPeca);
        editNomePeca = (TextView) cadastroPeca.findViewById(R.id.editNomePeca);
        editMarcaPeca = (TextView) cadastroPeca.findViewById(R.id.editMarcaPeca);
        editTipoCarro = (TextView) cadastroPeca.findViewById(R.id.editTipoCarro);
        editMarcaCarro = (TextView) cadastroPeca.findViewById(R.id.editMarcaCarro);
        editModeloCarro = (TextView) cadastroPeca.findViewById(R.id.editModeloCarro);
        editAnoModeloCarro = (TextView) cadastroPeca.findViewById(R.id.editAnoModeloCarro);
        editPrecoPeca = (TextView) cadastroPeca.findViewById(R.id.editPrecoPeca);
        peca = new Peca();
    }

    public void exibePeca(Peca pecaAlterar){
        peca = pecaAlterar;
        editNomePeca.setText(pecaAlterar.getNomePeca());
        editMarcaPeca.setText(pecaAlterar.getMarcaPeca());
        editTipoCarro.setText(pecaAlterar.getTipoCarro());
        editMarcaCarro.setText(pecaAlterar.getMarcaCarro());
        editModeloCarro.setText(pecaAlterar.getModeloCarro());
        editAnoModeloCarro.setText(pecaAlterar.getAnoModeloCarro());
        editPrecoPeca.setText(String.valueOf(pecaAlterar.getPreco()));
    }

    public Peca retornaPeca() {
        peca.setNomePeca(editNomePeca.getText().toString());
        peca.setMarcaPeca(editMarcaPeca.getText().toString());
        peca.setTipoCarro(editTipoCarro.getText().toString());
        peca.setMarcaCarro(editMarcaCarro.getText().toString());
        peca.setModeloCarro(editModeloCarro.getText().toString());
        peca.setAnoModeloCarro(editAnoModeloCarro.getText().toString());
        //peca.setPreco(Double.parseDouble(String.valueOf(editPrecoPeca.getText())));

        return peca;
    }

    public boolean CampoVazio(){
        if ((editNomePeca.getText().toString().equals(""))
                ||(editMarcaPeca.getText().toString().equals(""))
                ||(editTipoCarro.getText().toString().equals(""))
                ||(editMarcaCarro.getText().toString().equals(""))
                ||(editModeloCarro.getText().toString().equals(""))
                ||(editAnoModeloCarro.getText().toString().equals(""))
                ||(editPrecoPeca.getText().toString().equals(""))){
            return true;
        }
        else {
            return false;
        }
    }

    public ImageView getImgFotoPeca(){
        return imgFotoPeca;
    }

    public void carregaMarcaCarro(String marcaCarro){
        editMarcaCarro.setText(marcaCarro);
    }

    public void carregaImagem(String caminhoImg){
        peca.setFotoPeca(caminhoImg);
        Bitmap imagem = BitmapFactory.decodeFile(caminhoImg);
        Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem,100,100,true);


        Log.d("passei aqui","-----------");
        imgFotoPeca.setImageBitmap(imagemReduzida);
    }

}
