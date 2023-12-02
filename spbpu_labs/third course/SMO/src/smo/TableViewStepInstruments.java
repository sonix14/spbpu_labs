package com.example.smo;

import java.util.ArrayList;

public class TableViewStepInstruments {
    private final String pointer;
    private final String name;
    private final String state;
    private final String requestId;
    private final String sourceId;

    public TableViewStepInstruments(ArrayList<String> data) {
        pointer = data.get(0);
        name = data.get(1);
        state = data.get(2);
        requestId = data.get(3);
        sourceId = data.get(4);
    }
    public String getPointer() { return pointer; }
    public String getRequestId() { return requestId; }
    public String getSourceId() { return sourceId; }
    public String getState() { return state; }
    public String getName() { return name; }
}
