package com.example.rafael.trabfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rafael on 08/12/2016.
 */

public class AdicionarPecaOrcAuxiliar {
    private ImageView imgFotoPeca;
    private TextView nomePecaOrcamento;
    private TextView marcaPecaOrcamento;
    private EditText addQtdePecaOrcamento;
    private EditText addPrecoPecaOrcamento;
    private Peca peca;

    public AdicionarPecaOrcAuxiliar(AdicionarPecaOrc adicionarPecaOrc) {
        imgFotoPeca = (ImageView) adicionarPecaOrc.findViewById(R.id.imgFotoPeca);
        nomePecaOrcamento = (TextView) adicionarPecaOrc.findViewById(R.id.nomePecaOrcamento);
        marcaPecaOrcamento = (TextView) adicionarPecaOrc.findViewById(R.id.marcaPecaOrcamento);
        addQtdePecaOrcamento = (EditText) adicionarPecaOrc.findViewById(R.id.addQtdePecaOrcamento);
        addPrecoPecaOrcamento = (EditText) adicionarPecaOrc.findViewById(R.id.addPrecoPecaOrcamento);
        peca = new Peca();
    }

    public void exibePeca(Peca pecaAlterar){
        peca = pecaAlterar;
        nomePecaOrcamento.setText(pecaAlterar.getNomePeca());
        marcaPecaOrcamento.setText(pecaAlterar.getMarcaPeca());
        //addQtdePecaOrcamento.setText(String.valueOf(pecaAlterar.getQtdePeca()));
        //addPrecoPecaOrcamento.setText(String.valueOf(pecaAlterar.getPreco()));
    }

    public Peca retornaPeca() {
        peca.setNomePeca(nomePecaOrcamento.getText().toString());
        peca.setMarcaPeca(marcaPecaOrcamento.getText().toString());
        peca.setQtdePeca(Integer.parseInt(String.valueOf(addQtdePecaOrcamento.getText())));
        peca.setPreco(Double.parseDouble(String.valueOf(addPrecoPecaOrcamento.getText())));

        return peca;
    }

    public boolean CampoVazio(){
        if ((addQtdePecaOrcamento.getText().toString().equals(""))
                ||(addPrecoPecaOrcamento.getText().toString().equals(""))){
            return true;
        }
        else {
            return false;
        }
    }

    public ImageView getImgFotoPeca(){
        return imgFotoPeca;
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
