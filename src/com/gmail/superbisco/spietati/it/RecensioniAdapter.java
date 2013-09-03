package com.gmail.superbisco.spietati.it;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import bean.Recensione;

import java.util.List;

public class RecensioniAdapter extends ArrayAdapter<Recensione> {

    public RecensioniAdapter(Context context, int textViewResourceId, List<Recensione> values) {
        super(context, textViewResourceId, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewOptimize(position, convertView, parent);
    }
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
//        TextView nome = (TextView) rowView.findViewById(R.id.label);
//        TextView descrizione = (TextView) rowView.findViewById(R.id.secondLine);
//        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//        Recensione r = getItem(position);   //values[position]
//        nome.setText(r.getNome());
//        descrizione.setText(r.getIdFilm());
////        // Change the icon for Windows and iPhone
////        String s = values[position];
////        if (s.startsWith("iPhone")) {
////            imageView.setImageResource(R.drawable.no);
////        } else {
////            imageView.setImageResource(R.drawable.ok);
////        }
//
//        return rowView;
//    }

    public View getViewOptimize(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowlayout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)convertView.findViewById(R.id.label);
            viewHolder.description = (TextView)convertView.findViewById(R.id.secondLine);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Recensione r = getItem(position);
        viewHolder.name.setText(r.getNome());
        viewHolder.description.setText(r.getIdFilm());
        return convertView;
    }

    private class ViewHolder {
        public TextView name;
        public TextView description;
    }
}
