package IRCTC;

import  java.time.LocalDate;
import java.time.LocalDateTime;
import IRCTC.enums.JourneyStatus;
import java.util.List;
import java.util.ArrayList;

public class TrainJourney {
    private final String journeyId;
    private final Train train;
    private final LocalDate journeyDate;
    private final String source;
    private final String destination;
    private final LocalDateTime departure;
    private final LocalDateTime arrival;

    JourneyStatus status = JourneyStatus.SCHEDULED;

    private final SeatInventory inventory;

    TrainJourney(String journeyId, Train train,
                 LocalDate journeyDate,
                 String source, String destination,
                 LocalDateTime departure,
                 LocalDateTime arrival) {
        this.journeyId = journeyId;
        this.train = train;
        this.journeyDate = journeyDate;
        this.source = source;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;

        List<Seat> allSeats = new ArrayList<>();
        for (Coach coach : train.coaches) {
            allSeats.addAll(coach.seats);
        }
        this.inventory = new SeatInventory(allSeats);
    }
}
