package com.example.smo;

public class TableViewAutoInstruments {
    private final String name;
    private double coefficient;

    public TableViewAutoInstruments(Instruments instrument, Statistics statistics) {
        name = "Instrument " + instrument.getIndex();
        calculateCoefficient(instrument, statistics);
    }
    private void calculateCoefficient(Instruments instrument, Statistics statistics) {
        double scale = Math.pow(10, 3);
        coefficient = Math.ceil((instrument.getFullProcessingTime() / statistics.getWorkingTime()) * scale) / scale;
    }
    public String getName() { return name; }
    public double getCoefficient() { return coefficient; }
}
