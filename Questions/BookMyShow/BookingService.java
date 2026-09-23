package BookMyShow;

import java.util.List;
import java.util.Map;

public class BookingService {
    private final MovieController movieController;
    private final TheatreController theatreController;

    public BookingService(MovieController movieController, TheatreController theatreController) {
        this.movieController = movieController;
        this.theatreController = theatreController;
    }

    public List<Movie> searchMovies(City city) {
        return movieController.getMoviesByCity(city);
    }

    public Map<Theatre, List<Show>> getShows(City city, String movieName) {
        Movie movie = movieController.getMovieByName(movieName);
        if (movie == null) {
            throw new IllegalArgumentException("Movie not found: " + movieName);
        }
        return theatreController.getShowsByMovie(movie, city);
    }

    public Booking createBooking(
            String bookingId,
            User user,
            Show show,
            List<Integer> seatIds,
            PaymentType paymentType) {

        Booking booking = new Booking(bookingId, user);
        booking.setShow(show);

        double total = 0;
        for (int seatId : seatIds) {
            Seat seat = show.findSeat(seatId);
            if (seat == null) {
                throw new IllegalArgumentException("Invalid seat: " + seatId);
            }

            if (!show.bookSeat(seatId)) {
                throw new IllegalStateException("Seat already booked: " + seatId);
            }

            booking.getBookedSeats().add(seat);
            total += getPrice(seat.getSeatCategory());
        }

        booking.setTotalAmount(total);
        booking.setPaymentType(paymentType);

        PaymentFactory.create(paymentType).pay(total);
        booking.markPaid();
        return booking;
    }

    private double getPrice(SeatCategory category) {
        switch (category) {
            case SILVER:
                return 150;
            case GOLD:
                return 250;
            case PLATINUM:
                return 400;
            default:
                return 150;
        }
    }
}
