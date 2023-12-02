package com.example.smo;

import java.util.concurrent.atomic.AtomicInteger;

public class Requests {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int index;
    private final int sourceIndex;
    private double processingTime;
    private double startTime;
    private double generationTime;
    private double timeInSystem;
    private double waitingTime;

    public Requests(int sourceIndex_) {
        index = COUNTER.getAndIncrement();
        sourceIndex = sourceIndex_;
        processingTime = 0;
        startTime = 0;
        generationTime = 0;
        timeInSystem = 0;
    }
    public void setGenerationTime(double time) {
        generationTime = time;
        timeInSystem = generationTime;
    }
    public void setStartTime(double time) { startTime = time; }
    public void setProcessingTime(double time) {
        processingTime = time;
        setTimeInSystem();
    }
    private void setTimeInSystem() { timeInSystem += processingTime; }
    public void setWaitingTime(double time) {
        waitingTime = time - startTime;
        timeInSystem += waitingTime;
    }
    public static void resetCounter() { COUNTER.set(1); }
    public int getIndex() { return index; }
    public int getSourceIndex() {
        return sourceIndex;
    }
    public double getStartTime() { return startTime; }
    public double getGenerationTime() { return generationTime; }
    public double getProcessingTime() {
        return processingTime;
    }
    public double getTimeInSystem() { return timeInSystem; }
    public double getWaitingTime() { return waitingTime; }
}
