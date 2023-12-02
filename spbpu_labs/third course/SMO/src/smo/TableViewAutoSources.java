package com.example.smo;

public class TableViewAutoSources {
    private final String name;
    private final int requestCount;
    private final int rejectCount;
    private double timeInSystem;
    private double processingTime;
    private double waitingTime;
    private double dispTOW;
    private double dispTOP;
    private final double rejectionPercent;

    public TableViewAutoSources(Source source, Statistics statistics) {
        name = "Source " + source.getIndex();
        requestCount = source.getRequestCount();
        rejectCount = source.getRejectionCount();
        rejectionPercent = Math.round(source.calculateRejectionPercent());
        calculateProcessingTime(source, statistics);
        calculateSystemTime(source, statistics);
        calculateWaitingTime(source, statistics);
        calculateDispersionWaitingTime(source, statistics);
        calculateDispersionProcessingTime(source, statistics);
    }
    private void calculateProcessingTime(Source source, Statistics statistics) {
        double time = 0;
        if (statistics.getFullProcessingTime().get(source.getIndex()) != null) {
            time = statistics.getFullProcessingTime().get(source.getIndex());
        }

        double scale = Math.pow(10, 3);
        processingTime = Math.ceil((time / requestCount) * scale) / scale;
    }
    private void calculateSystemTime(Source source, Statistics statistics) {
        double time = 0;
        if(statistics.getFullSystemTime().get(source.getIndex()) != null) {
            time = statistics.getFullSystemTime().get(source.getIndex());
        }

        double scale = Math.pow(10, 3);
        timeInSystem = Math.ceil((time / requestCount) * scale) / scale;
    }
    private void calculateWaitingTime(Source source, Statistics statistics) {
        double time = 0;
        if(statistics.getFullWaitingTime().get(source.getIndex()) != null) {
            time = statistics.getFullWaitingTime().get(source.getIndex());
        }

        double scale = Math.pow(10, 3);
        waitingTime = Math.ceil((time / requestCount) * scale) / scale;
    }
    private void calculateDispersionWaitingTime(Source source, Statistics statistics) {
        double sumOfSquares = 0;
        for (int i = 0; i < statistics.getWaitingTimes().get(source.getIndex()).size(); i++) {
            sumOfSquares += Math.pow(statistics.getWaitingTimes().get(source.getIndex()).get(i) - waitingTime, 2);
        }

        double scale = Math.pow(10, 3);
        dispTOW = Math.ceil((sumOfSquares / requestCount) * scale) / scale;
    }
    private void calculateDispersionProcessingTime(Source source, Statistics statistics) {
        double sumOfSquares = 0;
        for (int i = 0; i < statistics.getProcessingTimes().get(source.getIndex()).size(); i++) {
            sumOfSquares += Math.pow(statistics.getProcessingTimes().get(source.getIndex()).get(i) - processingTime, 2);
        }

        double scale = Math.pow(10, 3);
        dispTOP =  Math.ceil((sumOfSquares / requestCount) * scale) / scale;
    }
    public String getName() { return name; }
    public int getRequestCount() { return requestCount; }
    public double getRejectionPercent() { return rejectionPercent; }
    public double getTimeInSystem() { return timeInSystem; }
    public double getProcessingTime() { return processingTime; }
    public double getWaitingTime() { return waitingTime; }
    public double getDispTOW() { return dispTOW; }
    public double getDispTOP() { return dispTOP; }
    public int getRejectCount() { return rejectCount; }
}
