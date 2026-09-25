package IRCTC;

import IRCTC.enums.BirthType;

public class Passenger {
    final String name;
    final int age;
    final BirthType preference;

    Passenger(String name, int age, BirthType preference) {
        this.name = name;
        this.age = age;
        this.preference = preference;
    }
}
