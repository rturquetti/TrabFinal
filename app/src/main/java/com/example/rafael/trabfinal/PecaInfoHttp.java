package com.example.rafael.trabfinal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 25/11/2016.
 */

public class PecaInfoHttp {
    //public static final String MARCAS_URL = "http://fipeapi.appspot.com/api/1/carros/marcas.json";

    public static final String MARCAS_URL = "http://www.codifique.net/wsCM/MarcasRafael.json";
    private static HttpURLConnection conectar(String urlArquivo) throws IOException {

        final int SEGUNDOS = 1000; //apenas para facilitar a conversão para segundos dos parametros
        URL url = new URL(urlArquivo);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(10 * SEGUNDOS);
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true); //Será feita leitura de dados
        conexao.setDoOutput(false); ///Não será feita escrita de dados no servidor
        conexao.connect();
        return conexao;
    }

    public static boolean temConexao (Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();
        return  (info != null && info.isConnected());
    }

    public static List<ObjMarcas> carregarObjMarcasJson(){
        try{
            HttpURLConnection conexao = conectar(MARCAS_URL);

            int resposta = conexao.getResponseCode(); //Código do protocolo http. Exemplo: 404 arquivo nao encontrado
            if(resposta == HttpURLConnection.HTTP_OK) {
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject(bytesParaString(is));
                return lerJsonObjMarcas(json);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<ObjMarcas> lerJsonObjMarcas(JSONObject json) throws JSONException {

        List<ObjMarcas> listajsonObjMarcas = new ArrayList<ObjMarcas>();

        JSONArray jsonListaObjMarcas = json.getJSONArray("marcas");
        for(int i=0; i < jsonListaObjMarcas.length(); i++) {
            JSONObject jsonObjMarcas = jsonListaObjMarcas.getJSONObject(i);

            ObjMarcas a = new ObjMarcas(
                    jsonObjMarcas.getString("name"),
                    jsonObjMarcas.getString("fipe_name"),
                    jsonObjMarcas.getString("order"),
                    jsonObjMarcas.getString("key"),
                    jsonObjMarcas.getString("id")
            );

            listajsonObjMarcas.add(a);
        }
        return listajsonObjMarcas;
    }

    private static String bytesParaString(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        //Buffer para armazenar todos os alunos lidos do arquivo
        ByteArrayOutputStream bufferAlunos = new ByteArrayOutputStream();
        //retorna a quantidade de bytes lidos
        int qtdBytesLidos;
        //faz a leitura de 1 em 1 Kb
        while((qtdBytesLidos = is.read(buffer)) !=-1){
            //copia a quantidade de bytes lidos de buffer para bufferAlunos
            bufferAlunos.write(buffer, 0, qtdBytesLidos);
        }
        return new String(bufferAlunos.toByteArray(), "UTF-8");
    }
}
