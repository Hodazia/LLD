package IRCTC;

import  java.time.LocalDate;
import java.time.LocalDateTime;
import IRCTC.enums.JourneyStatus;
import java.util.List;
import java.util.ArrayList;

public class TrainJourney {
    final String journeyId;
    final Train train;
    final LocalDate journeyDate;
    final String source;
    final String destination;
    final LocalDateTime departure;
    final LocalDateTime arrival;

    JourneyStatus status = JourneyStatus.SCHEDULED;

    final SeatInventory inventory;

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
