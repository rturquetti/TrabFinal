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
 * Created by rafael on 28/11/2016.
 */

public class OrcamentoDAO extends SQLiteOpenHelper{
    private static final String DATABASE = "Orcamentos";
    private static final int VERSAO = 1;

    public OrcamentoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void salva(Orcamento orcamento){
//        ContentValues values = new ContentValues();
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
//        getWritableDatabase().insert("Pecas",null,values);
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

    public void deletar(Orcamento orcamento){
        String[] args = {String.valueOf(orcamento.getId())};
        getWritableDatabase().delete("Pecas","id=?",args);

    }

    public List<Orcamento> getLista(){
        String[] colunas = {"id","fotoPeca","nomePeca","marcaPeca","tipoCarro","marcaCarro","modeloCarro","anoModeloCarro","qtdePeca","precoPeca"};

        Cursor cursor = getWritableDatabase().query("Pecas",colunas,null,null,null,null,null);

        ArrayList<Orcamento> orcamentos = new ArrayList<Orcamento>();

        while (cursor.moveToNext()) {

            Orcamento orcamento = new Orcamento();



            orcamentos.add(orcamento);
        }

        return orcamentos;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String string = "CREATE TABLE Pecas (id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "fotoPeca TEXT, nomePeca TEXT, marcaPeca TEXT, tipoCarro TEXT, marcaCarro TEXT,"+
                "modeloCarro TEXT, anoModeloCarro TEXT, qtdePeca INTEGER,precoPeca REAL);";
        db.execSQL(string);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String string = "DROP TABLE IF EXISTS Pecas";
        db.execSQL(string);
        this.onCreate(db);
    }
}
