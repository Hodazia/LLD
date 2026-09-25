package IRCTC;

import IRCTC.enums.Role;

public abstract class User {
    private final String id;
    private final String name;
    private final String email;
    private final Role role;

    protected User(String id, String name,
        String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getId() { return id; }
    public Role getRole() { return role; }
}


class Customer extends User {
    public Customer(String id, String name, String email) {
        super(id, name, email, Role.CUSTOMER);
    }
}

class Admin extends User {
    public Admin(String id, String name, String email) {
        super(id, name, email, Role.ADMIN);
    }
}

class Partner extends User {
    public Partner(String id, String name, String email) {
        super(id, name, email, Role.PARTNER);
    }
}
