package edu.ricky.madev.model;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ricky Wu on 2015/8/26.
 */
public class Event implements Serializable {

    private String name;
    private float rating;
    private Date partyDate;
    private String partyVenue;
    private String location;
    private ArrayList<Invitee> invitees;

    public Event(String name) {
        this.name = name;
        this.location = "";
        this.invitees = new ArrayList<>();
        this.partyDate = new Date();
        this.partyVenue = "";
        this.rating = 0.0f;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Invitee> getInvitees() {
        return invitees;
    }

    public void setInvitees(ArrayList<Invitee> invitees) {
        this.invitees = invitees;
    }

    public void addInvitee(Invitee invitee) {
        this.invitees.add(invitee);
    }

    public String getPartyDate() {
        SimpleDateFormat ft =
                new SimpleDateFormat("dd.MM.yyyy");
        return ft.format(partyDate);
    }

    public String getPartyTime() {
        SimpleDateFormat ft =
                new SimpleDateFormat("HH:mm");
        return ft.format(partyDate);
    }

    public void setPartyDate(String partydate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Log.i("MAD", "setPartyDate()");
        try {
            partyDate = sdf.parse(partydate);
        } catch (ParseException e) {
            Log.i("MAD",e.toString());
        }
    }

    public String getPartyVenue() {
        Log.i("MAD", partyDate.toString());
        return partyVenue;
    }

    public void setPartyVenue(String partyVenue) {
        this.partyVenue = partyVenue;
    }
}
