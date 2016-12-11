package com.example.rafael.trabfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 09/12/2016.
 */

public class PecasOrcamentoDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "OrcamentoPecas";
    private static final int VERSAO = 1;

    public PecasOrcamentoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void salva(ArrayList<Peca> pecasOrcamento, Long orcamento){

        for(int e = 0; e < pecasOrcamento.size(); e++ ){
            ContentValues values = new ContentValues();
            Peca pecaOrcamentoAdd = pecasOrcamento.get(e);
            values.put("idOrcamento",orcamento);
            values.put("idPeca",pecaOrcamentoAdd.getIdPeca());
            values.put("quantidadePeca",pecaOrcamentoAdd.getQtdePeca());
            values.put("valorPeca",pecaOrcamentoAdd.getPreco());
            getWritableDatabase().insert("OrcamentoPecas",null,values);
        }
    }

    public void editar(Orcamento peca){
//        ContentValues values = new ContentValues();
//
//        values.put("fotoPeca",peca.getFotoPeca());
//        values.put("nomePeca",peca.getNomePeca());
//        values.put("marcaPeca",peca.getMarcaPeca());
//        values.put("tipoCarro",peca.getTipoCarro());
//        values.put("marcaCarro",peca.getMarcaCarro());
//        values.put("modeloCarro",peca.getModeloCarro());
//        values.put("anoModeloCarro",peca.getAnoModeloCarro());
//        values.put("qtdePeca",peca.getQtdePeca());
//        values.put("precoPeca",peca.getPreco());
//
//        String[] args = {peca.getIdPeca().toString()};
//        getWritableDatabase().update("Pecas",values,"id=?",args);
    }

    public void deletar(Long orcDeletar){
        String[] args = {String.valueOf(orcDeletar)};
        getWritableDatabase().delete("OrcamentoPecas","idOrcamento=?",args);

    }


    public List<Peca> getListaOrcPreenchida(List<Peca> pecas, Long Orc){
        String[] colunas = {"id", "idOrcamento", "idPeca", "quantidadePeca", "valorPeca"};

        Cursor cursor = getWritableDatabase().query("OrcamentoPecas", colunas, "OrcamentoPecas.idOrcamento like '%"+Orc+"%'", null, null, null, null);

        ArrayList<Peca> listaPecaMandar = new ArrayList<Peca>();

        while (cursor.moveToNext()) {
            Peca pecaParaMandar = new Peca();
            //pecaParaMandar.setNomePeca(pecaPercorrida.getNomePeca());
            for(int i = 0; i < pecas.size(); i++ ) {
                Peca cacaPeca = pecas.get(i);
                if (cacaPeca.getIdPeca() == cursor.getInt(2)){
                    pecaParaMandar.setNomePeca(cacaPeca.getNomePeca());
                }

            }
            pecaParaMandar.setQtdePeca(cursor.getInt(3));
            pecaParaMandar.setPreco(cursor.getDouble(4));

            listaPecaMandar.add(pecaParaMandar);
        }

        return listaPecaMandar;
    }


    public List<OrcamentoPecas> getLista(){
        String[] colunas = {"id","idOrcamento","idPeca","quantidadePeca","valorPeca"};

        Cursor cursor = getWritableDatabase().query("OrcamentoPecas",colunas,null,null,null,null,null);

        ArrayList<OrcamentoPecas> orcamentosPecas = new ArrayList<OrcamentoPecas>();

        while (cursor.moveToNext()) {

            OrcamentoPecas pecaOrcamento = new OrcamentoPecas();
            pecaOrcamento.setId(cursor.getLong(0));
            pecaOrcamento.setIdOrcamento(cursor.getLong(1));
            pecaOrcamento.setIdPeca(cursor.getLong(2));
            pecaOrcamento.setQuantidadePeca(cursor.getInt(3));
            pecaOrcamento.setValorPeca(cursor.getDouble(4));

            orcamentosPecas.add(pecaOrcamento);
        }

        return orcamentosPecas;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String stringPecaOrcamento = "CREATE TABLE OrcamentoPecas (id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "idOrcamento INTEGER, idPeca INTEGER, quantidadePeca INTEGER, valorPeca Double,"+
                "FOREIGN KEY(idOrcamento) REFERENCES Orcamentos(id),"+
                "FOREIGN KEY(idPeca) REFERENCES clientes(id));";
        db.execSQL(stringPecaOrcamento);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String string = "DROP TABLE IF EXISTS OrcamentoPecas";
        db.execSQL(string);
        this.onCreate(db);
    }

}
