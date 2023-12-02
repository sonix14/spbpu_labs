package com.example.smo;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {
    private final int sourceCount;
    private final int instrumentsCount;
    private final int requestCount;
    private final int bufferSize;
    private double workingTime;
    private int stepCount;
    private final HashMap<Integer, Double> fullProcessingTime;
    private final HashMap<Integer, ArrayList<Double>> processingTimes;
    private final HashMap<Integer, Double> fullSystemTime;
    private final HashMap<Integer, Double> fullWaitingTime;
    private final HashMap<Integer, ArrayList<Double>> waitingTimes;
    private ArrayList<Source> sourcesList;
    private ArrayList<Instruments> instrumentsList;
    private final HashMap<Integer, ArrayList<ArrayList<String>>> bufferSteps;
    private ArrayList<ArrayList<String>> bufferStepList;
    private final HashMap<Integer, ArrayList<ArrayList<String>>> instrumentsSteps;
    private ArrayList<ArrayList<String>> instrumentsStepList;
    private final HashMap<Integer, ArrayList<ArrayList<String>>> sourcesSteps;
    private ArrayList<ArrayList<String>> sourcesStepList;
    private final HashMap<Integer, EventPairs> eventsMap;

    public Statistics(int sourceCount_, int instrumentsCount_, int requestCount_, int bufferSize_) {
        sourceCount = sourceCount_;
        instrumentsCount = instrumentsCount_;
        requestCount = requestCount_;
        bufferSize = bufferSize_;
        workingTime = 0.0;
        stepCount = 0;
        sourcesList = new ArrayList<>();
        instrumentsList = new ArrayList<>();
        fullProcessingTime = new HashMap<>();
        processingTimes = new HashMap<>();
        fullSystemTime = new HashMap<>();
        fullWaitingTime = new HashMap<>();
        waitingTimes = new HashMap<>();
        bufferSteps = new HashMap<>();
        instrumentsSteps = new HashMap<>();
        sourcesSteps = new HashMap<>();
        eventsMap = new HashMap<>();

        initialiseLists();
    }
    private void initialiseLists() {
        sourcesStepList = new ArrayList<>();
        for (int i = 0; i < sourceCount; i++) {
            ArrayList<String> sourceData = new ArrayList<>(3);
            sourceData.add("Source "+ (i + 1));
            sourceData.add("0");
            sourceData.add("0");
            sourcesStepList.add(sourceData);
        }

        instrumentsStepList = new ArrayList<>();
        for (int i = 0; i< instrumentsCount; i++) {
            ArrayList<String> instrumentsData = new ArrayList<>(5);
            instrumentsData.add("");
            instrumentsData.add("Instrument " + (i + 1));
            instrumentsData.add(State.WAITING.toString());
            instrumentsData.add("");
            instrumentsData.add("");
            instrumentsStepList.add(instrumentsData);
        }

        bufferStepList = new ArrayList<>();
        for (int i = 0; i < bufferSize; i++) {
            ArrayList<String> bufferData = new ArrayList<>(5);
            bufferData.add("");
            bufferData.add(Integer.toString(i));
            bufferData.add(State.FREE.toString());
            bufferData.add("");
            bufferData.add("");
            bufferStepList.add(bufferData);
        }
    }
    public void addProcessingTime(int sourceIndex, double time) {
        if (fullProcessingTime.containsKey(sourceIndex)) {
            double temp = fullProcessingTime.get(sourceIndex);
            fullProcessingTime.replace(sourceIndex, temp, temp + time);
        }
        else fullProcessingTime.put(sourceIndex, time);

        if (processingTimes.containsKey(sourceIndex)) {
            processingTimes.get(sourceIndex).add(time);
        } else {
            ArrayList<Double> newList = new ArrayList<>();
            newList.add(time);
            processingTimes.put(sourceIndex, newList);
        }
    }
    public void addSystemTime(int sourceIndex, double time) {
        if (fullSystemTime.containsKey(sourceIndex)) {
            double temp = fullSystemTime.get(sourceIndex);
            fullSystemTime.replace(sourceIndex, temp, temp + time);
        }
        else fullSystemTime.put(sourceIndex, time);
    }
    public void addFullWaitingTime(int sourceIndex, double time) {
        if (fullWaitingTime.containsKey(sourceIndex)) {
            double temp = fullWaitingTime.get(sourceIndex);
            fullWaitingTime.replace(sourceIndex, temp, temp + time);
        }
        else fullWaitingTime.put(sourceIndex, time);

        if (waitingTimes.containsKey(sourceIndex)) {
            waitingTimes.get(sourceIndex).add(time);
        } else {
            ArrayList<Double> newList = new ArrayList<>();
            newList.add(time);
            waitingTimes.put(sourceIndex, newList);
        }
    }
    private void updateStepCount(Events event) {
        stepCount++;
        switch(event) {
            case CREATION:
                ArrayList<ArrayList<String>> newBuffList1 = new ArrayList<>();
                for (ArrayList<String> strings : bufferStepList) {
                    ArrayList<String> newList_ = new ArrayList<>(strings);
                    newBuffList1.add(newList_);
                }
                bufferSteps.put(stepCount, newBuffList1);

                ArrayList<ArrayList<String>> newInstList1 = new ArrayList<>();
                for (ArrayList<String> strings : instrumentsStepList) {
                    ArrayList<String> newList_ = new ArrayList<>(strings);
                    newInstList1.add(newList_);
                }
                instrumentsSteps.put(stepCount, newInstList1);
                break;
            case REJECTED:
                ArrayList<ArrayList<String>> newInstList2 = new ArrayList<>();
                for (ArrayList<String> strings : instrumentsStepList) {
                    ArrayList<String> newList_ = new ArrayList<>(strings);
                    newInstList2.add(newList_);
                }
                instrumentsSteps.put(stepCount, newInstList2);
                break;
            case PUT_IN_BUFFER, TOOK_FROM_BUFFER:
                ArrayList<ArrayList<String>> newSourceList1 = new ArrayList<>();
                for (ArrayList<String> strings : sourcesStepList) {
                    ArrayList<String> newList_ = new ArrayList<>(strings);
                    newSourceList1.add(newList_);
                }
                sourcesSteps.put(stepCount, newSourceList1);

                ArrayList<ArrayList<String>> newInstList3 = new ArrayList<>();
                for (ArrayList<String> strings : instrumentsStepList) {
                    ArrayList<String> newList_ = new ArrayList<>(strings);
                    newInstList3.add(newList_);
                }
                instrumentsSteps.put(stepCount, newInstList3);
                break;
            case LOAD_INST, ALL_INST_BUSY, DONE:
                ArrayList<ArrayList<String>> newSourceList2 = new ArrayList<>();
                for (ArrayList<String> strings : sourcesStepList) {
                    ArrayList<String> newList_ = new ArrayList<>(strings);
                    newSourceList2.add(newList_);
                }
                sourcesSteps.put(stepCount, newSourceList2);

                ArrayList<ArrayList<String>> newBuffList2 = new ArrayList<>();
                for (ArrayList<String> strings : bufferStepList) {
                    ArrayList<String> newList_ = new ArrayList<>(strings);
                    newBuffList2.add(newList_);
                }
                bufferSteps.put(stepCount, newBuffList2);
                break;
        }
    }
    public void addSourceStep(int place, Events event, int requestId, int sourceId, double time) {
        updateStepCount(event);
        String eventStr = "";
        String oldValue;
        int temp;
        switch (event) {
            case CREATION :
                eventStr = "Created request " + requestId + " by source " + sourceId;

                oldValue = sourcesStepList.get(sourceId - 1).get(1);
                temp = Integer.parseInt(oldValue) + 1;
                sourcesStepList.get(sourceId - 1).set(1, Integer.toString(temp));
                break;
            case REJECTED :
                eventStr = "Rejected request " + requestId + " by source " + sourceId;

                oldValue = sourcesStepList.get(sourceId - 1).get(2);
                temp = Integer.parseInt(oldValue) + 1;
                sourcesStepList.get(sourceId - 1).set(2, Integer.toString(temp));

                bufferStepList.get(place).set(2, State.FREE.toString());
                bufferStepList.get(place).set(3, "");
                bufferStepList.get(place).set(4, "");

                ArrayList<ArrayList<String>> newList = new ArrayList<>();
                for (ArrayList<String> strings : bufferStepList) {
                    ArrayList<String> newList_ = new ArrayList<>(strings);
                    newList.add(newList_);
                }
                bufferSteps.put(stepCount, newList);
                break;
        }

        EventPairs eventTimeMap = new EventPairs(time, eventStr);
        eventsMap.put(stepCount, eventTimeMap);

        ArrayList<ArrayList<String>> newList = new ArrayList<>();
        for (ArrayList<String> strings : sourcesStepList) {
            ArrayList<String> newList_ = new ArrayList<>(strings);
            newList.add(newList_);
        }
        sourcesSteps.put(stepCount, newList);
    }
    public void addBufferStep(Events event, int pointer, int place, int requestId, int sourceId, double time) {
        updateStepCount(event);
        String eventStr = "";
        EventPairs eventTimeMap;
        switch (event) {
            case PUT_IN_BUFFER :
                eventStr = "Put request " + requestId + " by source " + sourceId + " in buffer";

                bufferStepList.get(place).set(2, State.OCCUPIED.toString());
                bufferStepList.get(place).set(3, Integer.toString(requestId));
                bufferStepList.get(place).set(4, Integer.toString(sourceId));

                break;
            case TOOK_FROM_BUFFER:
                eventStr = "Took request " + requestId + " by source " + sourceId + " from buffer";

                for (ArrayList<String> places : bufferStepList) { places.set(0, ""); }
                bufferStepList.get(pointer).set(0, ">");
                bufferStepList.get(place).set(2, State.FREE.toString());
                bufferStepList.get(place).set(3, "");
                bufferStepList.get(place).set(4, "");

                break;
        }
        eventTimeMap = new EventPairs(time, eventStr);
        eventsMap.put(stepCount, eventTimeMap);

        ArrayList<ArrayList<String>> newList = new ArrayList<>();
        for (ArrayList<String> strings : bufferStepList) {
            ArrayList<String> newList_ = new ArrayList<>(strings);
            newList.add(newList_);
        }
        bufferSteps.put(stepCount, newList);
    }
    public void addInstrumentStep(Events event, int pointer, int instrumentId, int requestId, int sourceId, double time) {
        updateStepCount(event);
        String eventStr = "";
        switch (event) {
            case LOAD_INST :
                eventStr = "Occupied instrument " + instrumentId + " with request " + requestId;

                for (ArrayList<String> instruments : instrumentsStepList) { instruments.set(0, ""); }
                instrumentsStepList.get(pointer).set(0, ">");
                instrumentsStepList.get(instrumentId - 1).set(2, State.BUSY.toString());
                instrumentsStepList.get(instrumentId - 1).set(3, Integer.toString(requestId));
                instrumentsStepList.get(instrumentId - 1).set(4, Integer.toString(sourceId));
                break;
            case ALL_INST_BUSY:
                eventStr = "All instruments are busy";
                break;
            case DONE:
                eventStr = "Request " + requestId + " was processed by instrument " + instrumentId;

                instrumentsStepList.get(instrumentId - 1).set(2, State.WAITING.toString());
                instrumentsStepList.get(instrumentId - 1).set(3, "");
                instrumentsStepList.get(instrumentId - 1).set(4, "");
                break;
        }
        EventPairs eventTimeMap = new EventPairs(time, eventStr);
        eventsMap.put(stepCount, eventTimeMap);

        ArrayList<ArrayList<String>> newList = new ArrayList<>();
        for (ArrayList<String> strings : instrumentsStepList) {
            ArrayList<String> newList_ = new ArrayList<>(strings);
            newList.add(newList_);
        }
        instrumentsSteps.put(stepCount, newList);
    }
    public void setSources(ArrayList<Source> sourceList_) { sourcesList = sourceList_; }
    public void setInstruments(ArrayList<Instruments> instrumentsList_) { instrumentsList = instrumentsList_; }
    public void setWorkingTime(double workingTime_) { workingTime = workingTime_; }
    public void updateWorkingTime(double time_) { workingTime += time_; }
    public double getWorkingTime() { return workingTime; }
    public int getSourceCount() { return sourceCount; }
    public int getRequestCount() { return requestCount; }
    public int getInstrumentsCount() { return instrumentsCount; }
    public HashMap<Integer, Double> getFullProcessingTime() { return fullProcessingTime; }
    public HashMap<Integer, ArrayList<Double>> getProcessingTimes() { return processingTimes; }
    public HashMap<Integer, Double> getFullSystemTime() { return fullSystemTime; }
    public HashMap<Integer, Double> getFullWaitingTime() { return fullWaitingTime; }
    public HashMap<Integer, ArrayList<Double>> getWaitingTimes() { return waitingTimes; }
    public HashMap<Integer, ArrayList<ArrayList<String>>> getBufferSteps() { return bufferSteps; }
    public HashMap<Integer, ArrayList<ArrayList<String>>> getInstrumentsSteps() { return instrumentsSteps; }
    public HashMap<Integer, ArrayList<ArrayList<String>>> getSourcesSteps() { return sourcesSteps; }
    public HashMap<Integer, EventPairs> getEventsMap() { return eventsMap; }
    public int getStepCount() { return stepCount; }
    public ArrayList<Source> getSourcesList() { return sourcesList; }
    public ArrayList<Instruments> getInstrumentsList() { return instrumentsList; }
}
