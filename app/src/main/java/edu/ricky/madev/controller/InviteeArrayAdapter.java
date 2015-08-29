package edu.ricky.madev.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.ricky.madev.EventDetailActivity;
import edu.ricky.madev.R;
import edu.ricky.madev.model.Invitee;

/**
 * Created by keith on 20/08/15.
 */
public class InviteeArrayAdapter extends ArrayAdapter<Invitee> {

    // Reference Controller
    private EventDetailActivity activity;

    // Reference Model (not nessessary in this case)
    private List<Invitee> inviteesList;



    // Constructor
    public InviteeArrayAdapter(EventDetailActivity activity, List<Invitee> invitees)
    {
        super(activity, R.layout.invitees_list_layout, invitees);

        this.activity = activity;
        this.inviteesList = invitees;
    }



    @Override
    public View getView(int position, View cachedView, ViewGroup parent)
    {
        View inviteeItemView;

        if (cachedView == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();

            inviteeItemView = inflater.inflate(R.layout.invitees_list_layout, parent, false);
        }
        else
        {
            inviteeItemView = cachedView;
        }

        // Get The model

        Invitee ev = getItem(position);

        // Get our views

        TextView nameView = (TextView) inviteeItemView.findViewById(R.id.invitee_name_textview);
        //ImageView posterView = (ImageView) movieItemView.findViewById(R.id.iv_event_thumb);

        // Fill in the Views with content

        nameView.setText(ev.getName());

        return inviteeItemView;
    }

    public void remove(Invitee object) {
        if (inviteesList.contains(object)) {

            inviteesList.remove(object);
        }
        notifyDataSetChanged();
    }

    public void add(Invitee object) {
        if (!inviteesList.contains(object)) {

            inviteesList.add(object);
        }
        notifyDataSetChanged();
    }
}
