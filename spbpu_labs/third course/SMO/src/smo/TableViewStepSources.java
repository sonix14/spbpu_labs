package com.example.smo;

import java.util.ArrayList;

public class TableViewStepSources {
    private final String name;
    private final int requestCount;
    private final int rejectCount;

    public TableViewStepSources(ArrayList<String> data) {
        name = data.get(0);
        requestCount = Integer.parseInt(data.get(1));
        rejectCount = Integer.parseInt(data.get(2));
    }
    public int getRejectCount() { return rejectCount; }
    public String getName() { return name; }
    public int getRequestCount() { return requestCount; }
}
