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
 * Created by rafael on 24/11/2016.
 */

public class PecaDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "Pecas";
    private static final int VERSAO = 1;

    public PecaDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void salva(Peca peca){
        ContentValues values = new ContentValues();
        values.put("fotoPeca",peca.getFotoPeca());
        values.put("nomePeca",peca.getNomePeca());
        values.put("marcaPeca",peca.getMarcaPeca());
        values.put("tipoCarro",peca.getTipoCarro());
        values.put("marcaCarro",peca.getMarcaCarro());
        values.put("modeloCarro",peca.getModeloCarro());
        values.put("anoModeloCarro",peca.getAnoModeloCarro());
        values.put("qtdePeca",peca.getQtdePeca());
        values.put("precoPeca",peca.getPreco());

        getWritableDatabase().insert("Pecas",null,values);
    }

    public void editar(Peca peca){
        ContentValues values = new ContentValues();

        values.put("fotoPeca",peca.getFotoPeca());
        values.put("nomePeca",peca.getNomePeca());
        values.put("marcaPeca",peca.getMarcaPeca());
        values.put("tipoCarro",peca.getTipoCarro());
        values.put("marcaCarro",peca.getMarcaCarro());
        values.put("modeloCarro",peca.getModeloCarro());
        values.put("anoModeloCarro",peca.getAnoModeloCarro());
        values.put("qtdePeca",peca.getQtdePeca());
        values.put("precoPeca",peca.getPreco());

        String[] args = {peca.getIdPeca().toString()};
        getWritableDatabase().update("Pecas",values,"id=?",args);
    }

    public void deletar(Peca peca){
        String[] args = {peca.getIdPeca().toString()};
        getWritableDatabase().delete("Pecas","id=?",args);

    }

    public List<Peca> getLista(){
        String[] colunas = {"id","fotoPeca","nomePeca","marcaPeca","tipoCarro","marcaCarro","modeloCarro","anoModeloCarro","qtdePeca","precoPeca"};

        Cursor cursor = getWritableDatabase().query("Pecas",colunas,null,null,null,null,null);

        ArrayList<Peca> pecas = new ArrayList<Peca>();

        while (cursor.moveToNext()) {

            Peca peca = new Peca();

            peca.setIdPeca(cursor.getLong(0));
            peca.setFotoPeca(cursor.getString(1));
            peca.setNomePeca(cursor.getString(2));
            peca.setMarcaPeca(cursor.getString(3));
            peca.setTipoCarro(cursor.getString(4));
            peca.setMarcaCarro(cursor.getString(5));
            peca.setModeloCarro(cursor.getString(6));
            peca.setAnoModeloCarro(cursor.getString(7));
            peca.setQtdePeca(cursor.getInt(8));
            peca.setPreco(cursor.getDouble(9));

            pecas.add(peca);
        }

        return pecas;
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
