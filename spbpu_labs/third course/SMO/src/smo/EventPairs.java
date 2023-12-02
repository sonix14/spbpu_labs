package com.example.smo;

public class EventPairs {
    private double time = 0;
    private String eventText = "";

    public EventPairs(double time_, String eventText_) {
        time = time_;
        eventText = eventText_;
    }
    public double getTime() {
        double scale = Math.pow(10, 3);
        return Math.ceil((time) * scale) / scale;
    }
    public String getEventText() { return eventText; }
}
