package com.example.smo;

public enum State {
    BUSY("Busy"),
    WAITING("Waiting"),
    OCCUPIED("Occupied"),
    FREE("Free");

    private final String title;

    State(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
