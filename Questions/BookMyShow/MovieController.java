package BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    private final Map<City, List<Movie>> cityVsMovies = new HashMap<>();
    private final Map<Integer, Movie> moviesById = new HashMap<>();

    public void addMovie(Movie movie, City city) {
        moviesById.putIfAbsent(movie.getMovieId(), movie);

        List<Movie> movies = cityVsMovies.getOrDefault(city, new ArrayList<>());
        if (!movies.contains(movie)) {
            movies.add(movie);
        }
        cityVsMovies.put(city, movies);
    }

    public Movie getMovieByName(String movieName) {
        for (Movie movie : moviesById.values()) {
            if (movie.getMovieName().equals(movieName)) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> getMoviesByCity(City city) {
        return cityVsMovies.getOrDefault(city, new ArrayList<>());
    }
}
