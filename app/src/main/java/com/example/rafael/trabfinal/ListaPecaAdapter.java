package com.example.rafael.trabfinal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rafael on 24/11/2016.
 */

public class ListaPecaAdapter extends BaseAdapter {
    private List<Peca> pecas;
    private Activity activity;

    public ListaPecaAdapter(List<Peca> pecas, Activity activity) {
        this.pecas = pecas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return pecas.size();
    }

    @Override
    public Object getItem(int position) {
        return pecas.get(position);
    }

    @Override
    public long getItemId(int position) {
        Peca peca = pecas.get(position);

        return pecas.get(position).getIdPeca();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Peca peca = pecas.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();
        View linha = inflater.inflate(R.layout.activity_linha_peca,null);

        ImageView imgLinhaPeca = (ImageView) linha.findViewById(R.id.imgLinhaPeca);
        if(peca.getFotoPeca() != null){
            Bitmap fotoPeca = BitmapFactory.decodeFile(peca.getFotoPeca());
            Bitmap fotoReduzida = Bitmap.createScaledBitmap(fotoPeca,100,100,true);
            imgLinhaPeca.setImageBitmap(fotoReduzida);
        }
        else{
            Drawable semfoto = activity.getResources().getDrawable(R.mipmap.ic_wallpaper_black_24dp);
            imgLinhaPeca.setImageDrawable(semfoto);
        }

        TextView nomeLinhaPeca = (TextView)linha.findViewById(R.id.nomeLinhaPeca);
        nomeLinhaPeca.setText(peca.getNomePeca());
        TextView marcaLinhaPeca = (TextView)linha.findViewById(R.id.marcaLinhaPeca);
        marcaLinhaPeca.setText(peca.getMarcaPeca());
        TextView marcaLinhaCarro = (TextView)linha.findViewById(R.id.marcaLinhaCarro);
        marcaLinhaCarro.setText(peca.getMarcaCarro());

        return linha;
    }
}
