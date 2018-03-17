package com.google.android.cataloguemovie;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static com.google.android.cataloguemovie.R.id.poster;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>>{

    ListView listView;
    MovieItemAdapter adapter;
    EditText editSearch;
    Button btnSearch;

    static final String EXTRAS_WORD ="EXTRAS_WORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieItemAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.lv_movies);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Ini buat Clickable ListView
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MovieItem ClickObject = (MovieItem)adapterView.getItemAtPosition(position);
                Intent x = new Intent(MainActivity.this, DetailMovie.class);
                x.putExtra(DetailMovie.EXTRAS_DTITLE, ClickObject.getmTitle());
                x.putExtra(DetailMovie.EXTRAS_DDESC, ClickObject.getmDescription());
                x.putExtra(DetailMovie.EXTRAS_DPOSTER, ClickObject.getmImageResource());
                startActivity(x);

            //Toast.makeText(MainActivity.this, "Title : "+ ClickObject.getmTitle(),Toast.LENGTH_SHORT).show();
            }
        });


        editSearch = (EditText)findViewById(R.id.edit_search);
        btnSearch = (Button)findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(myListener);

        String movie = editSearch.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_WORD, movie);
    }

    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int i, Bundle bundle) {
        String keyWord = "";
        if(bundle != null){
            keyWord = bundle.getString(EXTRAS_WORD);
        }
        return new MyAsyncTaskLoader(this, keyWord);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> movieItems) {
        adapter.setData(movieItems);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItem>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //Ini buat Button Search
            if(view.getId()==R.id.btn_search) {
                String movie = editSearch.getText().toString();
                if (TextUtils.isEmpty(movie)) return;

                Bundle bundle = new Bundle();
                bundle.putString(EXTRAS_WORD, movie);
                getLoaderManager().restartLoader(0, bundle, MainActivity.this);
            }
        }

    };


}

