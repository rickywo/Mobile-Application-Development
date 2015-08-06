package edu.ricky.madev;

/**
 * Created by Ricky Wu on 2015/8/6.
 */
class MovieFeeder {
    static final private int[] image = new int[] {
            R.mipmap.before_midnight,
            R.mipmap.bodyguard,
            R.mipmap.frozen,
            R.mipmap.iron_man,
            R.mipmap.minions,
            R.mipmap.the_matrix,
            R.mipmap.the_truman_show
    };
    static final private String[] m_title = new String[] {
            "Before Midnight",
            "Bodyguard",
            "Frozen",
            "Iron Man",
            "Minions",
            "The Matrix",
            "The Truman Show"
    };
    static final private String[] prod_year = new String[] {
            "2013",
            "2011",
            "2013",
            "2011",
            "2015",
            "1999",
            "1998"
    };
    static final private String[] m_genre = new String[] {
            "Drama, Romance",
            "Romance",
            "Animation, Adventure, Comedy",
            "Action, Adventure",
            "Animation, Comedy, Family",
            "Action, Sci-Fi",
            "Drama"
    };

    static public int[] getMovieImages() {
        return image;
    }

    static public String[] getMovieTitles() {
        return m_title;
    }

    static public String[] getMovieYears() {
        return prod_year;
    }

    static public String[] getMovieGenres() {
        return m_genre;
    }
}
