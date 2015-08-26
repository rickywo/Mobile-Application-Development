package edu.ricky.madev;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MovieDetailActivity extends ActionBarActivity {

    static String mvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        final TextView tv_title = (TextView) findViewById(R.id.detail_title_textview);
        final TextView tv_year = (TextView) findViewById(R.id.detail_year_textview);
        final TextView tv_plot = (TextView) findViewById(R.id.detail_plot_textview);
        final ImageView imgView = (ImageView) findViewById(R.id.detail_post_imageview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mvTitle = extras.getString("movie_title");
            tv_title.setText(MainActivity.movieEventHashMap.get(mvTitle).getTitle());
            tv_year.setText(MainActivity.movieEventHashMap.get(mvTitle).getYear());
            tv_plot.setText(MainActivity.movieEventHashMap.get(mvTitle).getShortPlot());
            imgView.setImageResource(MainActivity.movieEventHashMap.get(mvTitle).getImg());
        }
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
}
