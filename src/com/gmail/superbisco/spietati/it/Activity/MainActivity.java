package com.gmail.superbisco.spietati.it.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.gmail.superbisco.spietati.it.bean.ElencoFilmItem;
import com.gmail.superbisco.spietati.it.adapter.ElencoRecensioniAdapter;
import com.gmail.superbisco.spietati.it.R;
import com.gmail.superbisco.spietati.it.utils.RecensioniUtil;

import java.util.List;

public class MainActivity extends Activity {

    public final static String EXTRA_KEYS = "com.gmail.superbisco.spietati.MESSAGE";
    public final static String EXTRA_IDFILM = "com.gmail.superbisco.spietati.IDRECENSIONE";

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // Elenco ultime recensioni
        Toast.makeText(this, "Caricamento elenco recensioni", Toast.LENGTH_LONG).show();

        new ElencaRecensioniTask().execute();

//        List<ElencoFilmItem> listRece = RecensioniUtil.getElencoUltimeRecensioni();
//        ElencoRecensioniAdapter com.gmail.superbisco.spietati.it.adapter = new ElencoRecensioniAdapter(this, R.layout.row_elenco_film, listRece);
//
//        // Popolamento lista
//        final ListView listView = (ListView) findViewById(R.id.listViewRecensioni);
//        listView.setAdapter(com.gmail.superbisco.spietati.it.adapter);
//
//        // Evento click
//        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> com.gmail.superbisco.spietati.it.adapter, View view,
//                                    int position, long id) {
//                ElencoFilmItem r = (Recensione)com.gmail.superbisco.spietati.it.adapter.getItemAtPosition(position);
//
//                apriRecensione(r);
//
//                Toast.makeText(getApplicationContext(),
//                        "Apertura activity_recensione " + r.getNome(), Toast.LENGTH_LONG)
//                        .show();
//
//
//            }
//        };
//        listView.setOnItemClickListener(clickListener);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                //final String item = (String) parent.getItemAtPosition(position);
//                final String item = ((ElencoFilmItem) parent.getItemAtPosition(position)).getIdFilm();
//                view.animate().setDuration(500).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
//                                list.remove(item);
//                                com.gmail.superbisco.spietati.it.adapter.notifyDataSetChanged();
//                                view.setAlpha(1);
//                            }
//                        });
//            }
//
//        });

	}

    // Elenco recensioni film
    class ElencaRecensioniTask extends AsyncTask<Void, Void, List<ElencoFilmItem>> {

        protected List<ElencoFilmItem> doInBackground(Void...args) {
            return RecensioniUtil.getElencoUltimeRecensioni();
        }

        protected void onPostExecute(List<ElencoFilmItem> result) {
            ElencoRecensioniAdapter adapter = new ElencoRecensioniAdapter(getApplicationContext(), R.layout.row_elenco_film, result);

            // Popolamento lista
            final ListView listView = (ListView) findViewById(R.id.listViewRecensioni);
            listView.setAdapter(adapter);

            // Evento click
            AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapter, View view,
                                        int position, long id) {
                    ElencoFilmItem r = (ElencoFilmItem)adapter.getItemAtPosition(position);

                    apriRecensione(r);

                    Toast.makeText(getApplicationContext(),
                            "Apertura activity_recensione " + r.getNome(), Toast.LENGTH_LONG).show();


                }
            };
            listView.setOnItemClickListener(clickListener);
        }
    }

    private void apriRecensione(ElencoFilmItem r) {
        Intent intent = new Intent(this, DisplayRecensioneActivity.class);
        intent.putExtra(EXTRA_IDFILM, r.getIdFilm());
        startActivity(intent);
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

    //final Button btnCerca = (Button) findViewById(R.id.btnCerca);

    public void avviaRicerca(View view) {

        Intent intent = new Intent(this, DisplaySearchResultsActivity.class);

        EditText textCerca = (EditText) findViewById(R.id.textCerca);
        String searchWhat = textCerca.getText().toString();
        intent.putExtra(EXTRA_KEYS, searchWhat);
        startActivity(intent);
    }
}
