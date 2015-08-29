package edu.ricky.madev;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.ricky.madev.controller.MovieArrayAdapter;
import edu.ricky.madev.model.Movie;
import edu.ricky.madev.model.MovieModel;


public class MainActivity extends ActionBarActivity {
    // model
    MovieModel theModel = MovieModel.getSingleton();
    // view
    ListView movieListView;
    //Controller
    MovieArrayAdapter movieArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListView = ( ListView ) findViewById(R.id.listview);
        // Create the adapter between movie list model and the movie list view
        movieArrayAdapter = new MovieArrayAdapter(this, theModel.getAllMovies());
        movieListView.setAdapter(movieArrayAdapter);

        // Configure events

        movieListView.setOnItemClickListener(
                new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Movie mv = (Movie) movieListView.getItemAtPosition(position);
                        Intent intent = new Intent(getBaseContext(), MovieDetailActivity.class);
                        intent.putExtra("id", mv.getImdbId());
                        startActivity(intent);
                    }
                }
        );

    }

    public MainActivity() {
        super();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initMovieList(ListView v) {
        // Load movie list
        List<Movie> me = theModel.getAllMovies();
        // Each row in the list stores img, title, year, genre
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        for(Movie m: me){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("img", Integer.toString(m.getImg()) );
            hm.put("title", m.getTitle());
            hm.put("year", m.getYear());
            hm.put("genre", m.getGenre());
            hm.put("rating", m.getImdbRating());
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "img","title","year","genre", "rating" };

        // Ids of views in listview_layout
        int[] to = { R.id.iv_event_thumb,R.id.tv_event_name,R.id.tv_mvYear,R.id.tv_evVenue, R.id.tv_eventrating};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        MovieAdapter adapter = new MovieAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Setting the adapter to the listView
        v.setAdapter(adapter);
    }
}
