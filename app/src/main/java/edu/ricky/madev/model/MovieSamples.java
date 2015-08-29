package edu.ricky.madev.model;

import java.util.List;

import edu.ricky.madev.R;

/**
 * Created by Ricky Wu on 2015/8/26.
 */
public class MovieSamples {

    static final int numMovies = 7;
    // Movie Json Strings
    static final String[] mvJsons = {
            "{\"Title\":\"Before Midnight\",\"Year\":\"2013\",\"Rated\":\"R\",\"Released\":\"14 Jun 2013\",\"Runtime\":\"109 min\",\"Genre\":\"Drama, Romance\",\"Director\":\"Richard Linklater\",\"Writer\":\"Richard Linklater, Julie Delpy, Ethan Hawke, Richard Linklater (characters), Kim Krizan (characters)\",\"Actors\":\"Ethan Hawke, Julie Delpy, Seamus Davey-Fitzpatrick, Jennifer Prior\",\"Plot\":\"We meet Jesse and Celine nine years on in Greece. Almost two decades have passed since their first meeting on that train bound for Vienna.\",\"Language\":\"English, Greek, French\",\"Country\":\"USA, Greece\",\"Awards\":\"Nominated for 1 Oscar. Another 23 wins & 47 nominations.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMjA5NzgxODE2NF5BMl5BanBnXkFtZTcwNTI1NTI0OQ@@._V1_SX300.jpg\",\"Metascore\":\"94\",\"imdbRating\":\"8.0\",\"imdbVotes\":\"80921\",\"imdbID\":\"tt2209418\",\"Type\":\"movie\",\"Response\":\"True\"}",
            "{\"Title\":\"Bodyguard\",\"Year\":\"2011\",\"Rated\":\"N/A\",\"Released\":\"31 Aug 2011\",\"Runtime\":\"130 min\",\"Genre\":\"Romance\",\"Director\":\"Siddique\",\"Writer\":\"J.P. Chowksey (screenplay), Kiran Kotrial (screenplay), Siddique\",\"Actors\":\"Salman Khan, Kareena Kapoor, Raj Babbar, Asrani\",\"Plot\":\"The daughter of a wealthy nobleman secretly falls in love with her bodyguard.\",\"Language\":\"Hindi, English\",\"Country\":\"India\",\"Awards\":\"4 wins & 10 nominations.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTMyOTkzMzg4Ml5BMl5BanBnXkFtZTcwNTkxNzQzNg@@._V1_SX300.jpg\",\"Metascore\":\"41\",\"imdbRating\":\"4.5\",\"imdbVotes\":\"14469\",\"imdbID\":\"tt1729637\",\"Type\":\"movie\",\"Response\":\"True\"}",
            "{\"Title\":\"Frozen\",\"Year\":\"2013\",\"Rated\":\"PG\",\"Released\":\"27 Nov 2013\",\"Runtime\":\"102 min\",\"Genre\":\"Animation, Adventure, Comedy\",\"Director\":\"Chris Buck, Jennifer Lee\",\"Writer\":\"Jennifer Lee (screenplay), Hans Christian Andersen (inspired by the story \\\"The Snow Queen\\\" by), Chris Buck (story), Jennifer Lee (story), Shane Morris (story), Dean Wellins (additional story)\",\"Actors\":\"Kristen Bell, Idina Menzel, Jonathan Groff, Josh Gad\",\"Plot\":\"When the newly crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister, Anna, teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.\",\"Language\":\"English, Icelandic\",\"Country\":\"USA\",\"Awards\":\"Won 2 Oscars. Another 76 wins & 53 nominations.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTQ1MjQwMTE5OF5BMl5BanBnXkFtZTgwNjk3MTcyMDE@._V1_SX300.jpg\",\"Metascore\":\"74\",\"imdbRating\":\"7.6\",\"imdbVotes\":\"360146\",\"imdbID\":\"tt2294629\",\"Type\":\"movie\",\"Response\":\"True\"}",
            "{\"Title\":\"Iron Man\",\"Year\":\"2008\",\"Rated\":\"PG-13\",\"Released\":\"2 May 2008\",\"Runtime\":\"126 min\",\"Genre\":\"Action, Adventure, Sci-Fi\",\"Director\":\"Jon Favreau\",\"Writer\":\"Mark Fergus (screenplay), Hawk Ostby (screenplay), Art Marcum (screenplay), Matt Holloway (screenplay), Stan Lee (characters), Don Heck (characters), Larry Lieber (characters), Jack Kirby (characters)\",\"Actors\":\"Robert Downey Jr., Terrence Howard, Jeff Bridges, Gwyneth Paltrow\",\"Plot\":\"After being held captive in an Afghan cave, an industrialist creates a unique weaponized suit of armor to fight evil.\",\"Language\":\"English, Persian, Urdu, Arabic, Hungarian\",\"Country\":\"USA\",\"Awards\":\"Nominated for 2 Oscars. Another 18 wins & 51 nominations.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg\",\"Metascore\":\"79\",\"imdbRating\":\"7.9\",\"imdbVotes\":\"605494\",\"imdbID\":\"tt0371746\",\"Type\":\"movie\",\"Response\":\"True\"}",
            "{\"Title\":\"Minions\",\"Year\":\"2015\",\"Rated\":\"PG\",\"Released\":\"10 Jul 2015\",\"Runtime\":\"91 min\",\"Genre\":\"Animation, Comedy, Family\",\"Director\":\"Kyle Balda, Pierre Coffin\",\"Writer\":\"Brian Lynch\",\"Actors\":\"Sandra Bullock, Jon Hamm, Michael Keaton, Allison Janney\",\"Plot\":\"Minions Stuart, Kevin and Bob are recruited by Scarlet Overkill, a super-villain who, alongside her inventor husband Herb, hatches a plot to take over the world.\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"N/A\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTg2MTMyMzU0M15BMl5BanBnXkFtZTgwOTU3ODk4NTE@._V1_SX300.jpg\",\"Metascore\":\"56\",\"imdbRating\":\"6.7\",\"imdbVotes\":\"46891\",\"imdbID\":\"tt2293640\",\"Type\":\"movie\",\"Response\":\"True\"}",
            "{\"Title\":\"The Matrix\",\"Year\":\"1999\",\"Rated\":\"R\",\"Released\":\"31 Mar 1999\",\"Runtime\":\"136 min\",\"Genre\":\"Action, Sci-Fi\",\"Director\":\"Andy Wachowski, Lana Wachowski\",\"Writer\":\"Andy Wachowski, Lana Wachowski\",\"Actors\":\"Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving\",\"Plot\":\"A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.\",\"Language\":\"English\",\"Country\":\"USA, Australia\",\"Awards\":\"Won 4 Oscars. Another 33 wins & 40 nominations.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTkxNDYxOTA4M15BMl5BanBnXkFtZTgwNTk0NzQxMTE@._V1_SX300.jpg\",\"Metascore\":\"73\",\"imdbRating\":\"8.7\",\"imdbVotes\":\"1065503\",\"imdbID\":\"tt0133093\",\"Type\":\"movie\",\"Response\":\"True\"}",
            "{\"Title\":\"The Truman Show\",\"Year\":\"1998\",\"Rated\":\"PG\",\"Released\":\"5 Jun 1998\",\"Runtime\":\"103 min\",\"Genre\":\"Drama\",\"Director\":\"Peter Weir\",\"Writer\":\"Andrew Niccol\",\"Actors\":\"Jim Carrey, Laura Linney, Noah Emmerich, Natascha McElhone\",\"Plot\":\"An insurance salesman/adjuster discovers his entire life is actually a T.V. show.\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"Nominated for 3 Oscars. Another 41 wins & 58 nominations.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTM4NjY2MTEzM15BMl5BanBnXkFtZTcwMTk2Njk3OA@@._V1_SX300.jpg\",\"Metascore\":\"90\",\"imdbRating\":\"8.1\",\"imdbVotes\":\"583779\",\"imdbID\":\"tt0120382\",\"Type\":\"movie\",\"Response\":\"True\"}"
    };

    static final int[] image = new int[] {
            R.mipmap.before_midnight,
            R.mipmap.bodyguard,
            R.mipmap.frozen,
            R.mipmap.iron_man,
            R.mipmap.minions,
            R.mipmap.the_matrix,
            R.mipmap.the_truman_show
    };

    static final Event[][] events = new Event[][] {
            new Event[]{
                    new Event("Movie Reading"),
                    new Event("Night owl!")
            },
            new Event[]{},
            new Event[]{},
            new Event[]{},
            new Event[]{},
            new Event[]{},
            new Event[]{}
    };

    static final Invitee[][] invitees = new Invitee[][] {
            new Invitee[]{
                    new Invitee("Ricky Wu", "0475412639"),
                    new Invitee("Jason Hory", "0402276854")
            },
            new Invitee[]{},
            new Invitee[]{},
            new Invitee[]{},
            new Invitee[]{},
            new Invitee[]{},
            new Invitee[]{}
    };
}
