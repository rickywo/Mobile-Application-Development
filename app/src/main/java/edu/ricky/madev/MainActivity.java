package edu.ricky.madev;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.ricky.madev.model.MovieEvent;


public class MainActivity extends ActionBarActivity {
    static int currentSelectedMovie;
    static ArrayList<MovieEvent> me = new ArrayList<MovieEvent>();
    static HashMap<String, MovieEvent> movieEventHashMap = new HashMap<String, MovieEvent>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load movie list
        MovieEvent.MovieEventModel model = new MovieEvent.MovieEventModel();
        me = model.getEvents();
        // Each row in the list stores img, title, year, genre
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(MovieEvent m: me){
            movieEventHashMap.put(m.getTitle(), m);
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("img", Integer.toString(m.getImg()) );
            hm.put("title", m.getTitle());
            hm.put("year", m.getYear());
            hm.put("genre", m.getGenre());
            hm.put("rating", m.getImdbRating());
            //hm.put("uri","http://ia.media-imdb.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg");
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "img","title","year","genre", "rating" };

        // Ids of views in listview_layout
        int[] to = { R.id.iv_mvPoster,R.id.tv_mvTitle,R.id.tv_mvYear,R.id.tv_mvGenre, R.id.tv_imdbrating};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        MovieAdapter adapter = new MovieAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);

        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                TextView tv = (TextView) view.findViewById(R.id.tv_mvTitle);
                currentSelectedMovie = position;
                //Log.i("MAD", tv.getText().toString());
                Intent intent = new Intent(getBaseContext(), MoviePostActivity.class);
                intent.putExtra("movie_title", tv.getText().toString());
                startActivity(intent);
            }
        });
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
}
