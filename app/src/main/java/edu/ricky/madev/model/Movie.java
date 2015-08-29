package edu.ricky.madev.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricky Wu on 2015/8/26.
 */
public class Movie implements Serializable {

    // General information from IMDB
    private String title;
    private String year;
    private String shortPlot;
    private String longPlot;
    private String imdbId;
    private String genre;
    private String imdbRating;
    private float rating;

    private int img;
    // List for holding event
    List<Event> partyEvent;

    public Movie(String id, String title,String year,String genre, String imdbRating, String shortPlot) {

        this.imdbId = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.rating = 0;
        this.shortPlot = shortPlot;
        this.img = 0;
        this.longPlot = "";
        this.partyEvent = new ArrayList<>();
    }




    public String getGenre() {
        return genre;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float r) {
        this.rating = r;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getLongPlot() {
        return longPlot;
    }

    public String getShortPlot() {
        return shortPlot;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public void addEvent(Event e) {
        partyEvent.add(e);
    }

    public List<Event> getPartyEvent() {
        return partyEvent;
    }
}
