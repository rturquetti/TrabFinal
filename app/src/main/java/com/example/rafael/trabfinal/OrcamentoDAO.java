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
        ContentValues values = new ContentValues();
        values.put("idClienteOrc",orcamento.getIdCliente());
        values.put("dataHoraOrc",orcamento.getDataHora());

        getWritableDatabase().insert("Orcamentos",null,values);
    }




    public void editar(Orcamento orcamento){
        ContentValues values = new ContentValues();
        values.put("idClienteOrc",orcamento.getIdCliente());
        values.put("dataHoraOrc",orcamento.getDataHora());

        String[] args = {String.valueOf(orcamento.getId())};
        getWritableDatabase().update("Orcamentos",values,"id=?",args);
    }

    public void deletar(Orcamento orcamento){
        String[] args = {String.valueOf(orcamento.getId())};
        getWritableDatabase().delete("Orcamentos","id=?",args);

    }

    public List<Orcamento> getLista(){
        String[] colunas = {"id","idClienteOrc","dataHoraOrc"};

        Cursor cursor = getWritableDatabase().query("Orcamentos",colunas,null,null,null,null,null);

        ArrayList<Orcamento> orcamentos = new ArrayList<Orcamento>();

        while (cursor.moveToNext()) {
            Orcamento orcamento = new Orcamento();
            orcamento.setId(cursor.getLong(0));
            orcamento.setIdCliente(Long.parseLong(cursor.getString(1)));
            orcamento.setDataHora(cursor.getString(2));

            orcamentos.add(orcamento);
        }
        return orcamentos;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String stringOrcamento = "CREATE TABLE Orcamentos (id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "idClienteOrc INTEGER, dataHoraOrc TEXT, FOREIGN KEY(idClienteOrc) REFERENCES clientes(id));";
        db.execSQL(stringOrcamento);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String string = "DROP TABLE IF EXISTS Pecas";
        db.execSQL(string);
        this.onCreate(db);
    }
}
