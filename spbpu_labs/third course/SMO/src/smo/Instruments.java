package com.example.smo;

import java.util.concurrent.atomic.AtomicInteger;

public class Instruments {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int index;
    private boolean freeFlag;
    private double startTime;
    private double endTime;
    private double processingTime;
    private double fullProcessingTime;
    private Requests workingRequest;

    public Instruments() {
        index = COUNTER.getAndIncrement();
        freeFlag = true;
        startTime = 0;
        endTime = 0;
        processingTime = 0;
        fullProcessingTime = 0;
        workingRequest = null;
    }
    public void occupyInstrument(Requests request, double startTime, double processTime) {
        workingRequest = request;
        setStartTime(startTime);
        setEndTime(processTime);
        setProcessingTime(processTime);
        updateFullProcessingTime(processTime);
        freeFlag = false;
    }
    public void stopOccupation() {
        workingRequest.setProcessingTime(processingTime);
        freeFlag = true;
    }
    private void setStartTime(double time) {
        startTime = time;
    }
    private void setEndTime(double time) { endTime = startTime + time; }
    private void setProcessingTime(double time) { processingTime = time; }
    private void updateFullProcessingTime(double time) { fullProcessingTime += time; }
    public static void resetCounter() { COUNTER.set(1); }
    public int getIndex() {
        return index;
    }
    public boolean getFreeFlag() {
        return freeFlag;
    }
    public double getStartTime() {
        return startTime;
    }
    public double getEndTime() { return endTime; }
    public double getProcessingTime() {
        return processingTime;
    }
    public double getFullProcessingTime() { return fullProcessingTime; }
    public Requests getWorkingRequest() { return workingRequest; }
}
