package com.gmail.superbisco.spietati.it;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.webkit.WebView;
import utils.Costanti;

public class DisplayRecensioneActivity extends Activity {

    private WebView browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recensione);

        Intent intent = getIntent();
        String idFilm = intent.getStringExtra(MainActivity.EXTRA_IDFILM);

        String url = Costanti.URL_BASE_SPIETATI + Costanti.URL_SCHEDA_DETT + idFilm;

        browser = (WebView) findViewById(R.id.webView);

        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl(url);

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
        //myLocationManager.requestLocationUpdates(PROVIDER, 10000, 100.0f, onLocation);
    }

    @Override
    public void onPause() {
        super.onPause();
        //myLocationManager.removeUpdates(onLocation);
    }

    LocationListener onLocation = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Calling back to JavaScript in case the location changes
            StringBuilder buf = new StringBuilder("javascript:whereami(");
            buf.append(String.valueOf(location.getLatitude()));
            buf.append(",");
            buf.append(String.valueOf(location.getLongitude()));
            buf.append(")");
            browser.loadUrl(buf.toString());
        }

        public void onProviderDisabled(String provider) {
            // required for interface, not used
        }

        public void onProviderEnabled(String provider) {
            // required for interface, not used
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // required for interface, not used
        }
    };

//    public class Locater {
//        public String getLocation() throws JSONException {
//            Location loc = myLocationManager.getLastKnownLocation(PROVIDER);
//            if (loc == null) {
//                return (null);
//            }
//            JSONObject json = new JSONObject();
//            json.put("lat", loc.getLatitude());
//            json.put("lon", loc.getLongitude());
//            return (json.toString());
//        }
//    }
}
