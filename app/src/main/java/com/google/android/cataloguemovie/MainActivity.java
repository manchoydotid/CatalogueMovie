package com.google.android.cataloguemovie;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>>{

    ListView listView;
    MovieItemAdapter adapter;
    EditText editSearch;
    Button btnSearch;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
                Intent x = new Intent(MainActivity.this, DetailMovieActivity.class);
                x.putExtra(DetailMovieActivity.EXTRAS_DTITLE, ClickObject.getmTitle());
                x.putExtra(DetailMovieActivity.EXTRAS_DDESC, ClickObject.getmDescription());
                x.putExtra(DetailMovieActivity.EXTRAS_DPOSTER, ClickObject.getmImageResource());
                startActivity(x);

            }
        });


//        editSearch = (EditText)findViewById(R.id.edit_search);
//        btnSearch = (Button)findViewById(R.id.btn_search);
//        btnSearch.setOnClickListener(myListener);
//        String movie = editSearch.getText().toString();
//        Bundle bundle = new Bundle();
//        bundle.putString(EXTRAS_WORD, movie);

        toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        viewPager =(ViewPager)findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NowPlayingTabFragment(), "Now Playing");
        adapter.addFragment(new UpcomingTabFragment(), "Upcoming");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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

//    View.OnClickListener myListener = new View.OnClickListener(){
//        @Override
//        public void onClick(View view) {
//            //Ini buat Button Search
//            if(view.getId()==R.id.btn_search) {
//                String movie = editSearch.getText().toString();
//                if (TextUtils.isEmpty(movie)) return;
//
//                Bundle bundle = new Bundle();
//                bundle.putString(EXTRAS_WORD, movie);
//                getLoaderManager().restartLoader(0, bundle, MainActivity.this);
//            }
//        }
//
//    };


}

