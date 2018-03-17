package com.google.android.cataloguemovie;

import android.icu.text.SimpleDateFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;

import static android.R.attr.id;
import static com.google.android.cataloguemovie.R.id.release_date;

/**
 * Created by MANCHOY on 3/11/2018.
 */

public class MovieItem implements Parcelable {

    private String mId;
    private String mTitle;
    private String mDescription;
    private String mReleaseDate;
    private String mImageResource;




    public MovieItem(JSONObject object) {

        try{
            String id = object.getString("id");
            String title = object.getString("title");
            String description = object.getString("overview");
            String release_date = object.getString("release_date");
            String poster = object.getString("poster_path");

            this.mId = id;
            this.mTitle = title;
            this.mDescription = description;
            this.mReleaseDate = release_date;
            this.mImageResource = poster;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }


    public String getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(String mImageResource) {
        this.mImageResource = mImageResource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mDescription);
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.mImageResource);
    }

    protected MovieItem(Parcel in) {
        this.mTitle = in.readString();
        this.mDescription = in.readString();
        this.mReleaseDate = in.readString();
        this.mImageResource = in.readString();
    }

    public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
}

