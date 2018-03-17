package com.google.android.cataloguemovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailMovie extends AppCompatActivity {

    static final String EXTRAS_DTITLE ="EXTRAS_DTITLE";
    static final String EXTRAS_DDESC = "EXTRAS_DDESC";
    static final String EXTRAS_DPOSTER = "EXTRAS_DPOSTER";
    private TextView tvDetailTitle, tvDetailDesc;
    private ImageView ivDetailPoster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvDetailTitle = (TextView) findViewById(R.id.detail_title);
        tvDetailDesc = (TextView) findViewById(R.id.detail_description);
        ivDetailPoster = (ImageView) findViewById(R.id.detail_poster);

        Bundle extras = getIntent().getExtras();

        String title = extras.getString("EXTRAS_DTITLE");
        String desc = extras.getString("EXTRAS_DDESC");
        String poster = extras.getString("EXTRAS_DPOSTER");
        Picasso.with(this).load("http://image.tmdb.org/t/p/w500"+poster).into(ivDetailPoster);
        tvDetailTitle.setText(title);
        tvDetailDesc.setText(desc);


    }
}
