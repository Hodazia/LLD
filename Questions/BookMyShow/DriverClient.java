package BookMyShow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DriverClient {
    private final MovieController movieController = new MovieController();
    private final TheatreController theatreController = new TheatreController();
    private final BookingService bookingService = new BookingService(movieController, theatreController);

    public static void main(String[] args) {
        DriverClient client = new DriverClient();
        client.initialize();

        User alice = new User(1, "Alice", City.Bangalore);
        User bob = new User(2, "Bob", City.Mumbai);

        client.createBooking(alice, "Brand New Day", 30);
        client.createBooking(bob, "Brand New Day", 45);

        System.out.println("\n--- Concurrent booking test (same seat) ---");
        client.simulateConcurrentBooking(City.Bangalore, "Brand New Day", 10);
    }

    private void createBooking(User user, String movieName, int seatId) {
        System.out.println("\n[" + user.getName() + " @ " + user.getCity() + "] booking " + movieName);

        List<Movie> movies = bookingService.searchMovies(user.getCity());
        System.out.println("  Available movies: " + formatMovies(movies));

        Map<Theatre, List<Show>> showsByTheatre = bookingService.getShows(user.getCity(), movieName);
        if (showsByTheatre.isEmpty()) {
            System.out.println("  No shows found for " + movieName);
            return;
        }

        Map.Entry<Theatre, List<Show>> entry = showsByTheatre.entrySet().iterator().next();
        Theatre theatre = entry.getKey();
        Show show = entry.getValue().get(0);

        System.out.println("  Selected theatre: " + theatre.getTheatreId()
                + " | show at " + show.getShowStartTime() + ":00");

        try {
            Booking booking = bookingService.createBooking(
                    "BK-" + user.getId() + "-" + seatId,
                    user,
                    show,
                    List.of(seatId),
                    PaymentType.UPI);

            System.out.println("  BOOKING SUCCESSFUL | id=" + booking.getBookingId()
                    + " | seats=" + seatId
                    + " | amount=₹" + booking.getTotalAmount());
        } catch (IllegalStateException e) {
            System.out.println("  BOOKING FAILED: " + e.getMessage());
        }
    }

    private void simulateConcurrentBooking(City city, String movieName, int seatId) {
        Show show = bookingService.getShows(city, movieName).values().iterator().next().get(0);
        User u1 = new User(3, "Thread-1", city);
        User u2 = new User(4, "Thread-2", city);

        Thread t1 = new Thread(() -> {
            try {
                bookingService.createBooking("BK-T1", u1, show, List.of(seatId), PaymentType.UPI);
                System.out.println("  Thread-1 booked seat " + seatId);
            } catch (IllegalStateException e) {
                System.out.println("  Thread-1 failed: " + e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                bookingService.createBooking("BK-T2", u2, show, List.of(seatId), PaymentType.CREDIT_CARD);
                System.out.println("  Thread-2 booked seat " + seatId);
            } catch (IllegalStateException e) {
                System.out.println("  Thread-2 failed: " + e.getMessage());
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String formatMovies(List<Movie> movies) {
        List<String> names = new ArrayList<>();
        for (Movie movie : movies) {
            names.add(movie.getMovieName());
        }
        return names.toString();
    }

    private void initialize() {
        createMovies();
        createTheatre();
    }

    private void createMovies() {
        Movie ddlj = new Movie();
        ddlj.setMovieId(1);
        ddlj.setMovieName("DDLJ");
        ddlj.setMovieDuration(160);

        Movie spiderman = new Movie();
        spiderman.setMovieId(2);
        spiderman.setMovieName("Brand New Day");
        spiderman.setMovieDuration(130);

        movieController.addMovie(spiderman, City.Bangalore);
        movieController.addMovie(ddlj, City.Bangalore);
        movieController.addMovie(spiderman, City.Hyderabad);
        movieController.addMovie(spiderman, City.Delhi);
        movieController.addMovie(spiderman, City.Mumbai);
        movieController.addMovie(ddlj, City.Mumbai);
    }

    private void createTheatre() {
        Movie ddljMovie = movieController.getMovieByName("DDLJ");
        Movie spidy = movieController.getMovieByName("Brand New Day");

        Theatre inoxTheatre = new Theatre();
        inoxTheatre.setTheatreId(1);
        inoxTheatre.setScreen(createScreens());
        List<Show> inoxShows = new ArrayList<>();
        inoxShows.add(createShow(1, inoxTheatre.getScreen().get(0), ddljMovie, 8));
        inoxShows.add(createShow(2, inoxTheatre.getScreen().get(1), spidy, 16));
        inoxTheatre.setShows(inoxShows);
        theatreController.addTheatre(inoxTheatre, City.Bangalore);

        Theatre pvrTheatre = new Theatre();
        pvrTheatre.setTheatreId(2);
        pvrTheatre.setScreen(createScreens());
        List<Show> pvrShows = new ArrayList<>();
        pvrShows.add(createShow(3, pvrTheatre.getScreen().get(0), ddljMovie, 13));
        pvrShows.add(createShow(4, pvrTheatre.getScreen().get(1), spidy, 20));
        pvrTheatre.setShows(pvrShows);
        theatreController.addTheatre(pvrTheatre, City.Mumbai);
    }

    private List<Screen> createScreens() {
        List<Screen> screens = new ArrayList<>();

        Screen screen1 = new Screen();
        screen1.setScreenId(1);
        screen1.setSeats(createSeats());
        screens.add(screen1);

        Screen screen2 = new Screen();
        screen2.setScreenId(2);
        screen2.setSeats(createSeats());
        screens.add(screen2);

        return screens;
    }

    private Show createShow(int showId, Screen screen, Movie movie, int showStartTime) {
        Show show = new Show();
        show.setShowId(showId);
        show.setScreen(screen);
        show.setMovie(movie);
        show.setShowStartTime(showStartTime);
        return show;
    }

    private List<Seat> createSeats() {
        List<Seat> seats = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.SILVER);
            seats.add(seat);
        }

        for (int i = 40; i < 70; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.GOLD);
            seats.add(seat);
        }

        for (int i = 70; i < 100; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seat.setSeatCategory(SeatCategory.PLATINUM);
            seats.add(seat);
        }

        return seats;
    }
}
