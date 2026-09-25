package IRCTC;

import java.util.List;
import java.util.ArrayList;

public class Train {
     final String trainNumber;
    private final String name;
     final List<Coach> coaches;

    Train(String trainNumber, String name, List<Coach> coaches) {
        this.trainNumber = trainNumber;
        this.name = name;
        this.coaches = new ArrayList<>(coaches);
    }

    // getters and setters
}
