package com.gmail.superbisco.spietati.it.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gmail.superbisco.spietati.it.R;
import com.gmail.superbisco.spietati.it.bean.Recensioni;
import com.gmail.superbisco.spietati.it.bean.SchedaFilm;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SchedaFilmAdapter extends ArrayAdapter<SchedaFilm> {

    public SchedaFilmAdapter(Context context, int textViewResourceId, List<SchedaFilm> values) {
        super(context, textViewResourceId, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewOptimize(position, convertView, parent);
    }

    public View getViewOptimize(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_scheda_film, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.locandina = (ImageView)convertView.findViewById(R.id.imgLocandina);
            viewHolder.titolo = (TextView)convertView.findViewById(R.id.textTitolo);
            viewHolder.regista = (TextView)convertView.findViewById(R.id.textRegista);
            viewHolder.recensione = (TextView)convertView.findViewById(R.id.textRecensione);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SchedaFilm schedaFilm = getItem(position);

        viewHolder.titolo.setText(schedaFilm.getTitolo());
        viewHolder.regista.setText(schedaFilm.getRegista());

        List<Recensioni> recensioniList = schedaFilm.getRecensioni();

        String testo = recensioniList.get(0).getTesto();

        viewHolder.recensione.setText(testo);

        // locandina
        try {
            URL url = new URL(schedaFilm.getLocandinaUrl());
            Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());

            viewHolder.locandina.setImageBitmap(bitmap);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            viewHolder.locandina.setImageResource(R.drawable.ic_launcher);
        }


        return convertView;
    }

    private class ViewHolder {
        public TextView titolo;
        public ImageView locandina;
        public TextView regista;
        public TextView recensione;
    }
}
