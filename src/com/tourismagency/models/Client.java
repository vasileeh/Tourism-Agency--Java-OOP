package com.tourismagency.models;

public class Client {
    private String name;
    private String email;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Client{name='" + name + "', email='" + email + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Client)) return false;
        Client other = (Client) obj;
        return name.equals(other.name) && email.equals(other.email);
    }
}
