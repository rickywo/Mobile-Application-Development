package edu.ricky.madev.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.ricky.madev.MovieDetailActivity;
import edu.ricky.madev.R;
import edu.ricky.madev.model.Event;
import edu.ricky.madev.model.Movie;

/**
 * Created by keith on 20/08/15.
 */
public class EventArrayAdapter extends ArrayAdapter<Event> {

    // Reference Controller
    private MovieDetailActivity activity;

    // Reference Model (not nessessary in this case)
    private List<Event> eventsList;



    // Constructor
    public EventArrayAdapter(MovieDetailActivity activity, List<Event> eventsList)
    {
        super(activity, R.layout.event_list_layout, eventsList);

        this.activity = activity;
        this.eventsList = eventsList;
    }



    @Override
    public View getView(int position, View cachedView, ViewGroup parent)
    {
        View movieItemView;

        if (cachedView == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();

            movieItemView = inflater.inflate(R.layout.event_list_layout, parent, false);
        }
        else
        {
            movieItemView = cachedView;
        }

        // Get The model

        Event ev = getItem(position);

        // Get our views

        TextView nameView = (TextView) movieItemView.findViewById(R.id.tv_event_name);
        TextView dateView = (TextView) movieItemView.findViewById(R.id.tv_evDate);
        TextView venueView = (TextView) movieItemView.findViewById(R.id.tv_evVenue);
        TextView ratingView = (TextView) movieItemView.findViewById(R.id.tv_eventrating);
        ImageView posterView = (ImageView) movieItemView.findViewById(R.id.iv_event_thumb);

        // Fill in the Views with content

        nameView.setText(ev.getName());
        dateView.setText(ev.getPartyDate().toString());
        venueView.setText(ev.getPartyVenue());
        ratingView.setText(Float.toString(ev.getRating()));

        return movieItemView;
    }
}
