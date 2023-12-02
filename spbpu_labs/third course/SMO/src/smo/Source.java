package com.example.smo;

import java.util.concurrent.atomic.AtomicInteger;

public class Source {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final int index;
    private int requestCount;
    private int rejectionCount;
    private Requests request;

    public Source() {
        index = COUNTER.getAndIncrement();
        request = null;
        requestCount = 0;
        rejectionCount = 0;
    }
    private void createRequest() {
        request = new Requests(index);
        updateRequestCount();
    }
    public Requests makeNewRequest(double time) {
        createRequest();
        request.setStartTime(time);
        return request;
    }
    private void updateRequestCount() {
        requestCount++;
    }
    public void updateRejectionCount() {
        rejectionCount++;
    }
    public double calculateRejectionPercent() {
        if (requestCount == 0) return 0;
        return ((double) rejectionCount / requestCount) * 100;
    }
    public static void resetCounter() { COUNTER.set(1); }
    public int getIndex() {
        return index;
    }
    public int getRequestCount() {
        return  requestCount;
    }
    public int getRejectionCount() {
        return rejectionCount;
    }
}
