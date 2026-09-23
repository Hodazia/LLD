package BookMyShow;

public class User {
    private final int id;
    private final String name;
    private final City city;

    public User(int id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }
}
