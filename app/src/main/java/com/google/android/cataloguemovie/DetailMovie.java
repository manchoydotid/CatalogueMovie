package com.google.android.cataloguemovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailMovie extends AppCompatActivity {

    static final String EXTRAS_DTITLE ="EXTRAS_DTITLE";
    private TextView tvDetailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvDetailTitle = (TextView) findViewById(R.id.detail_title);

        String detailTitle  = getIntent().getExtras().getString("title");
        tvDetailTitle.setText(detailTitle);
    }
}
