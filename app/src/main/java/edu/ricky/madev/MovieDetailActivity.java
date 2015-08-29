package edu.ricky.madev;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import edu.ricky.madev.controller.EventArrayAdapter;
import edu.ricky.madev.model.Event;
import edu.ricky.madev.model.Movie;
import edu.ricky.madev.model.MovieModel;


public class MovieDetailActivity extends ActionBarActivity {

    static Event event;
    ImageView moviePoster;
    TextView movieTitle;
    TextView movieIMDBRating;
    TextView movieYear;
    TextView moviePlot;
    ListView eventList;
    Button createEventbtn;
    // model
    MovieModel theModel = MovieModel.getSingleton();
    Movie movie;
    EventArrayAdapter eventArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movie = theModel.getMovieById(extras.getString("id"));

            getLayoutViews();
            setContent(movie);
            eventArrayAdapter = new EventArrayAdapter(this, movie.getPartyEvent());
            eventList.setAdapter(eventArrayAdapter);
            setListViewHeightBasedOnChildren(eventList);
            eventList.setOnItemClickListener(
                    new ListView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getBaseContext(), EventDetailActivity.class);
                            event = (Event) eventList.getItemAtPosition(position);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("event", event);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
            );
            createEventbtn.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), EventDetailActivity.class);
                            startActivity(intent);
                        }
                    }
            );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
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

    private void getLayoutViews() {
        moviePoster = (ImageView) findViewById(R.id.detail_post_imageview);
        movieTitle = (TextView) findViewById(R.id.detail_title_textview);
        movieIMDBRating = (TextView) findViewById(R.id.detail_rating_score);
        movieYear = (TextView) findViewById(R.id.detail_year_textview);
        moviePlot = (TextView) findViewById(R.id.detail_plot_textview);
        eventList = (ListView) findViewById(R.id.detail_event_listview);
        createEventbtn = (Button) findViewById(R.id.detail_create_event_btn);
    }
    private void setContent(Movie mv) {
        moviePoster.setImageResource(mv.getImg());
        movieTitle.setText(mv.getTitle());
        movieIMDBRating.setText(mv.getImdbRating());
        movieYear.setText(mv.getYear());
        moviePlot.setText(mv.getShortPlot());
    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        final int HEIGHT_MARGIN_BOTTOM = 20;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + HEIGHT_MARGIN_BOTTOM;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
