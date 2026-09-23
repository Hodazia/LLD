package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
    private final Map<City, List<Theatre>> cityVsTheatre = new HashMap<>();
    private final List<Theatre> allTheatre = new ArrayList<>();

    public void addTheatre(Theatre theatre, City city) {
        allTheatre.add(theatre);
        theatre.setCity(city);

        List<Theatre> theatres = cityVsTheatre.getOrDefault(city, new ArrayList<>());
        theatres.add(theatre);
        cityVsTheatre.put(city, theatres);
    }

    public Map<Theatre, List<Show>> getShowsByMovie(Movie movie, City city) {
        Map<Theatre, List<Show>> theatreVsShows = new HashMap<>();
        List<Theatre> theatres = cityVsTheatre.get(city);

        if (theatres == null || movie == null) {
            return theatreVsShows;
        }

        for (Theatre theatre : theatres) {
            List<Show> matchingShows = new ArrayList<>();
            for (Show show : theatre.getShows()) {
                if (show.getMovie().getMovieId() == movie.getMovieId()) {
                    matchingShows.add(show);
                }
            }
            if (!matchingShows.isEmpty()) {
                theatreVsShows.put(theatre, matchingShows);
            }
        }
        return theatreVsShows;
    }
}
