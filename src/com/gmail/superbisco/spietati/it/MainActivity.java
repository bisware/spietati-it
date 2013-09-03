package com.gmail.superbisco.spietati.it;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import bean.Recensione;
import utils.RecensioniUtil;

import java.util.List;

public class MainActivity extends Activity {

    public final static String EXTRA_KEYS = "com.gmail.superbisco.spietati.MESSAGE";
    public final static String EXTRA_IDFILM = "com.gmail.superbisco.spietati.IDRECENSIONE";

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // Elenco ultime recensioni
        List<Recensione> listRece = RecensioniUtil.getElencoUltimeRecensioni();
        RecensioniAdapter adapter = new RecensioniAdapter(this, R.layout.rowlayout, listRece);

        // Popolamento lista
        final ListView listView = (ListView) findViewById(R.id.listViewRecensioni);
        listView.setAdapter(adapter);

        // Evento click
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id) {
                Recensione r = (Recensione)adapter.getItemAtPosition(position);

                apriRecensione(r);

                Toast.makeText(getApplicationContext(),
                        "Apertura recensione " + r.getNome(), Toast.LENGTH_LONG)
                        .show();


            }
        };
        listView.setOnItemClickListener(clickListener);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                //final String item = (String) parent.getItemAtPosition(position);
//                final String item = ((Recensione) parent.getItemAtPosition(position)).getIdFilm();
//                view.animate().setDuration(500).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
//                                list.remove(item);
//                                adapter.notifyDataSetChanged();
//                                view.setAlpha(1);
//                            }
//                        });
//            }
//
//        });

	}

    private void apriRecensione(Recensione r) {
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

        Intent intent = new Intent(this, DisplayResultsActivity.class);

        EditText textCerca = (EditText) findViewById(R.id.textCerca);
        String searchWhat = textCerca.getText().toString();
        intent.putExtra(EXTRA_KEYS, searchWhat);
        startActivity(intent);
    }
}
