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
 * Created by rafael on 23/11/2016.
 */

public class ClienteDAO extends SQLiteOpenHelper{
    private static final String DATABASE = "orcamento";
    private static final int VERSAO = 2;

    public ClienteDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    public void salva(Cliente cliente){
        ContentValues values = new ContentValues();

        values.put("cpfCli",cliente.getCpfCli());
        values.put("nomecli",cliente.getNomeCli());
        values.put("ruacli",cliente.getRuaCli());
        values.put("numcli",cliente.getNumCli());
        values.put("complecli",cliente.getCompleCli());
        values.put("bairrocli",cliente.getBairroCli());
        values.put("cidadecli",cliente.getCidadeCli());
        values.put("estadocli",cliente.getEstadoCli());
        values.put("cepcli",cliente.getCepCli());
        values.put("telcli",cliente.getTelCli());
        values.put("emailcli",cliente.getEmailCli());

        getWritableDatabase().insert("Clientes",null,values);
    }

    public void editar(Cliente cliente){
        ContentValues values = new ContentValues();

        values.put("cpfCli",cliente.getCpfCli());
        values.put("nomecli",cliente.getNomeCli());
        values.put("ruacli",cliente.getRuaCli());
        values.put("numcli",cliente.getNumCli());
        values.put("complecli",cliente.getCompleCli());
        values.put("bairrocli",cliente.getBairroCli());
        values.put("cidadecli",cliente.getCidadeCli());
        values.put("cepcli",cliente.getCepCli());
        values.put("estadocli",cliente.getEstadoCli());
        values.put("telcli",cliente.getTelCli());
        values.put("emailcli",cliente.getEmailCli());

        String[] args = {cliente.getId().toString()};
        getWritableDatabase().update("Clientes",values,"id=?",args);
    }

    public void deletar(Cliente cliente){
        String[] args = {cliente.getId().toString()};


        Log.d("passei aqui "+cliente.getId().toString(),"");
        getWritableDatabase().delete("Clientes","id=?",args);

    }

    public Cliente getNome(Long nomeCliOrc){
        Cliente clienteRetornar=null;

        String[] colunas = {"id","cpfCli","nomeCli","ruaCli","numCli","compleCli","bairroCli","cepCli","cidadeCli","estadoCli","telCli","emailCli"};

        Cursor cursor = getWritableDatabase().query("Clientes",colunas,"Clientes.id like '%"+nomeCliOrc+"%'",null,null,null,null);

        ArrayList<Cliente> clientes = new ArrayList<>();

        while (cursor.moveToNext()) {

            Cliente cliente = new Cliente();

            cliente.setId(cursor.getLong(0));
            cliente.setNomeCli(cursor.getString(2));

            clientes.add(cliente);
        }

        for (int f=0; f<clientes.size(); f++){
            clienteRetornar = clientes.get(f);
        }

        return clienteRetornar;
    }

    public List<Cliente> getLista(){
        String[] colunas = {"id","cpfCli","nomeCli","ruaCli","numCli","compleCli","bairroCli","cepCli","cidadeCli","estadoCli","telCli","emailCli"};

        Cursor cursor = getWritableDatabase().query("Clientes",colunas,null,null,null,null,null);

        ArrayList<Cliente> clientes = new ArrayList<>();

        while (cursor.moveToNext()) {

            Cliente cliente = new Cliente();

            cliente.setId(cursor.getLong(0));
            cliente.setCpfCli(cursor.getString(1));
            cliente.setNomeCli(cursor.getString(2));
            cliente.setRuaCli(cursor.getString(3));
            cliente.setNumCli(cursor.getInt(4));
            cliente.setCompleCli(cursor.getString(5));
            cliente.setBairroCli(cursor.getString(6));
            cliente.setCepCli(cursor.getString(7));
            cliente.setCidadeCli(cursor.getString(8));
            cliente.setEstadoCli(cursor.getString(9));
            cliente.setTelCli(cursor.getString(10));
            cliente.setEmailCli(cursor.getString(11));

            clientes.add(cliente);
        }

        return clientes;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String string = "CREATE TABLE Clientes (id  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "cpfCli TEXT UNIQUE NOT NULL, nomeCli TEXT UNIQUE NOT NULL," +
                "ruaCli TEXT, numCli REAL,compleCli TEXT, bairroCli TEXT, cidadeCli TEXT,"+
                "estadoCli TEXT, cepCli TEXT, telCli TEXT, emailCli TEXT);";
        db.execSQL(string);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String string = "DROP TABLE IF EXISTS Clientes";
        db.execSQL(string);
        this.onCreate(db);
    }
}
