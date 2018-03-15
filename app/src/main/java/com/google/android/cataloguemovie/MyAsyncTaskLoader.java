package com.google.android.cataloguemovie;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static android.R.id.list;

/**
 * Created by MANCHOY on 3/11/2018.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItem>>{

    private ArrayList<MovieItem> mData;
    private boolean mHasResult = false;

    private String mKeyWord;

    public MyAsyncTaskLoader(final Context context, String keyWord){
        super(context);

        onContentChanged();
        this.mKeyWord = keyWord;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if(mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(ArrayList<MovieItem> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if(mHasResult){
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = "18594ab348cc3a56c1c45cc4ed2f35e0";

    @Override
    public ArrayList<MovieItem> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<MovieItem> movieItemses = new ArrayList<>();
        String url = "http://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&query="+mKeyWord;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String Result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(Result);
                    JSONArray results = responseObject.getJSONArray("results");

                    for (int i=0; i< results.length();i++){
                        JSONObject movie = results.getJSONObject(i);
                        MovieItem movieItem = new MovieItem(movie);
                        movieItemses.add(movieItem);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemses;
    }

    protected void onReleaseResources(ArrayList<MovieItem>data){

    }
}


