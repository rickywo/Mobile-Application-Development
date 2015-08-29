package edu.ricky.madev.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import edu.ricky.madev.MainActivity;
import edu.ricky.madev.R;
import edu.ricky.madev.model.Movie;

/**
 * Created by keith on 20/08/15.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    // Reference Controller
    private MainActivity activity;

    // Reference Model (not nessessary in this case)
    private List<Movie> movieList;



    // Constructor
    public MovieArrayAdapter(MainActivity activity, List<Movie> movieList)
    {
        super(activity, R.layout.listview_layout, movieList);

        this.activity = activity;
        this.movieList = movieList;
    }



    @Override
    public View getView(int position, View cachedView, ViewGroup parent)
    {
        View movieItemView;

        if (cachedView == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();

            movieItemView = inflater.inflate(R.layout.listview_layout, parent, false);
        }
        else
        {
            movieItemView = cachedView;
        }

        // Get The model

        Movie movie = getItem(position);

        // Get our views

        TextView titleView = (TextView) movieItemView.findViewById(R.id.tv_event_name);
        TextView yearView = (TextView) movieItemView.findViewById(R.id.tv_mvYear);
        TextView genreView = (TextView) movieItemView.findViewById(R.id.tv_evVenue);
        TextView ratingView = (TextView) movieItemView.findViewById(R.id.tv_eventrating);
        ImageView posterView = (ImageView) movieItemView.findViewById(R.id.iv_event_thumb);

        // Fill in the Views with content

        titleView.setText(movie.getTitle());
        yearView.setText(movie.getYear());
        genreView.setText(movie.getGenre());
        ratingView.setText(movie.getImdbRating());
        posterView.setBackgroundResource(movie.getImg());

        return movieItemView;
    }
}
