package com.google.android.cataloguemovie;

import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.attr.onClick;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by MANCHOY on 3/11/2018.
 */

public class MovieItemAdapter extends BaseAdapter {

    private ArrayList<MovieItem> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    private AdapterView.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClik(int position);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        mListener = listener;
    }

    public MovieItemAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItem> items){
        mData = items;
        notifyDataSetChanged();
    }
    public void addItem(final MovieItem item){
        mData.add(item);
        notifyDataSetChanged();
    }
    public void clearData(){
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if(mData == null) return 0;
        return mData.size();
    }

    @Override
    public MovieItem getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView textViewTitle;
        TextView textViewDesc;
        TextView textReleaseDate;
        ImageView imgPoster;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder.textViewTitle = (TextView)convertView.findViewById(R.id.title);
            holder.textViewDesc = (TextView)convertView.findViewById(R.id.description);
            holder.textReleaseDate = (TextView)convertView.findViewById(R.id.release_date);
            holder.imgPoster = (ImageView)convertView.findViewById(R.id.poster);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewTitle.setText(mData.get(position).getmTitle());
        holder.textViewDesc.setText(mData.get(position).getmDescription());
        holder.textReleaseDate.setText(mData.get(position).getmReleaseDate());
        Picasso.with(convertView.getContext()).load("http://image.tmdb.org/t/p/w500"+mData.get
                (position).getmImageResource()).into(holder.imgPoster);

//        convertView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                if(mListener != null){
//                    int position =
//
//                }
//            }
//        });

        return convertView;
    }


}











