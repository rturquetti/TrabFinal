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
    private TextView editQtdePeca;
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
        //editQtdePeca.setText(String.valueOf(pecaAlterar.getQtdePeca()));
        //editPrecoPeca.setText(String.valueOf(pecaAlterar.getPreco()));
    }

    public Peca retornaPeca() {
        peca.setNomePeca(editNomePeca.getText().toString());
        peca.setMarcaPeca(editMarcaPeca.getText().toString());
        peca.setTipoCarro(editTipoCarro.getText().toString());
        peca.setMarcaCarro(editMarcaCarro.getText().toString());
        peca.setModeloCarro(editModeloCarro.getText().toString());
        peca.setAnoModeloCarro(editAnoModeloCarro.getText().toString());
        //peca.setQtdePeca(Integer.parseInt(String.valueOf(editQtdePeca.getText())));
        //peca.setPreco(Double.parseDouble(String.valueOf(editPrecoPeca.getText())));

        return peca;
    }

    public void exibePecaOrc(Peca pecaAlterar){
        peca = pecaAlterar;
        editNomePeca.setText(pecaAlterar.getNomePeca());
        editMarcaPeca.setText(pecaAlterar.getMarcaPeca());
        editTipoCarro.setText(pecaAlterar.getTipoCarro());
        editMarcaCarro.setText(pecaAlterar.getMarcaCarro());
        editModeloCarro.setText(pecaAlterar.getModeloCarro());
        editAnoModeloCarro.setText(pecaAlterar.getAnoModeloCarro());
        editQtdePeca.setText(String.valueOf(pecaAlterar.getQtdePeca()));
        editPrecoPeca.setText(String.valueOf(pecaAlterar.getPreco()));
    }

    public Peca retornaPecaOrc() {
        peca.setNomePeca(editNomePeca.getText().toString());
        peca.setMarcaPeca(editMarcaPeca.getText().toString());
        peca.setTipoCarro(editTipoCarro.getText().toString());
        peca.setMarcaCarro(editMarcaCarro.getText().toString());
        peca.setModeloCarro(editModeloCarro.getText().toString());
        peca.setAnoModeloCarro(editAnoModeloCarro.getText().toString());
        peca.setQtdePeca(Integer.parseInt(String.valueOf(editQtdePeca.getText())));
        peca.setPreco(Double.parseDouble(String.valueOf(editPrecoPeca.getText())));

        return peca;
    }

    public boolean CampoVazio(){
        if ((editNomePeca.getText().toString().equals(""))
                ||(editMarcaPeca.getText().toString().equals(""))
                ||(editTipoCarro.getText().toString().equals(""))
                ||(editMarcaCarro.getText().toString().equals(""))
                ||(editModeloCarro.getText().toString().equals(""))
                ||(editAnoModeloCarro.getText().toString().equals(""))){
            return true;
        }
        else {
            return false;
        }
    }

    public ImageView getImgFotoPeca(){
        return imgFotoPeca;
    }

    public void carregaTipoCarro(String tipoCarro){
        editTipoCarro.setText(tipoCarro);
    }

    public void carregaMarcaCarro(String marcaCarro){
        editMarcaCarro.setText(marcaCarro);
    }

    public void carregaModeloCarro(String modeloCarro){
        editModeloCarro.setText(modeloCarro);
    }

    public void carregaImagem(String caminhoImg){
        if (caminhoImg != null) {
            peca.setFotoPeca(caminhoImg);
            Bitmap imagem = BitmapFactory.decodeFile(caminhoImg);
            Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 100, 100, true);

            imgFotoPeca.setImageBitmap(imagemReduzida);
        }
    }

}
