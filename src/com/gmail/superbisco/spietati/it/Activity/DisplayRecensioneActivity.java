package com.gmail.superbisco.spietati.it.Activity;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import com.gmail.superbisco.spietati.it.R;
import com.gmail.superbisco.spietati.it.adapter.SchedaFilmAdapter;
import com.gmail.superbisco.spietati.it.bean.SchedaFilm;
import com.gmail.superbisco.spietati.it.utils.RecensioniUtil;

import java.util.List;

public class DisplayRecensioneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensione);

        Intent intent = getIntent();
        String idFilm = intent.getStringExtra(MainActivity.EXTRA_IDFILM);

//        String url = Costanti.URL_BASE_SPIETATI + Costanti.URL_SCHEDA_DETT + idFilm;
//
//        WebView browser = (WebView) findViewById(R.id.webView);
//
//        browser.getSettings().setJavaScriptEnabled(true);
//        browser.loadUrl(url);

        new ElencaRecensioniTask().execute(idFilm);

        // Create the text view
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(idFilm);

        // Set the text view as the activity layout
        //setContentView(R.layout.activity_results);
//        setContentView(textView);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //myLocationManager.removeUpdates(onLocation);
    }


    class ElencaRecensioniTask extends AsyncTask<String, Void, List<SchedaFilm>> {

        private Exception exception;

        protected List<SchedaFilm> doInBackground(String...idFilm) {

            return RecensioniUtil.getSchedaFilm(idFilm);
        }

        protected void onPostExecute(List<SchedaFilm> result) {
            SchedaFilmAdapter adapter = new SchedaFilmAdapter(getApplicationContext(),
                    R.layout.row_scheda_film, result);

            // Popolamento lista
            final ListView listView = (ListView) findViewById(R.id.listViewScheda);
            listView.setAdapter(adapter);
        }
    }


}
