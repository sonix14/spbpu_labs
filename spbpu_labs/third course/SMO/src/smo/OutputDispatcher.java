package com.example.smo;

import com.example.smo.exceptions.IndexException;

import java.util.ArrayList;

public class OutputDispatcher extends AbsDispatcher {
    private final ArrayList<Instruments> instrumentsList;
    private int instrumentCursor;
    private int requestCursor;
    final private double lambda;

    public OutputDispatcher(Buffer buffer, Statistics statistics_, double lambda_) {
        bufferPtr = buffer;
        statistics = statistics_;
        requestPtr = null;
        instrumentsList = new ArrayList<>();
        requestCursor = 0;
        instrumentCursor = 0;
        lambda = lambda_;
    }
    public int deliverRequest() { //0 - everything is ok, 1 - all instruments are busy, 2 - buffer error
        if (!checkFreeInstruments()) {
            statistics.addInstrumentStep(Events.ALL_INST_BUSY, 0,
                    0, 0, 0, statistics.getWorkingTime());
            return 1;
        }
        if (!getRequestFromBuffer()) return 2;
        occupyInstrument();
        return 0;
    }
    public void addInstrument(Instruments instrument) { instrumentsList.add(instrument); }
    private boolean checkFreeInstruments() {
        checkInstruments();
        int count = 0;
        for (Instruments instrument : instrumentsList) {
            if (!instrument.getFreeFlag()) count++;
        }
        return count != instrumentsList.size();
    }
    private void checkInstruments() {
        for (Instruments instrument : instrumentsList) {
            if (!instrument.getFreeFlag()) {
                if (instrument.getEndTime() <= statistics.getWorkingTime()) {
                    instrument.stopOccupation();
                    updateTime(instrument);
                    statistics.addInstrumentStep(Events.DONE, instrumentCursor, instrument.getIndex(),
                            instrument.getWorkingRequest().getIndex(),
                            instrument.getWorkingRequest().getSourceIndex(), statistics.getWorkingTime());
                }
            }
        }
    }
    private boolean checkBusyInstruments() {
        for (Instruments instrument : instrumentsList) {
            if (!instrument.getFreeFlag())
                return true;
        }
        return false;
    }
    private boolean getRequestFromBuffer() {
        try {
            while (true) {
                if (!bufferPtr.checkEmptyPlace(requestCursor)) break;
                else updateRequestCursor();
            }
            requestPtr = bufferPtr.popOut(requestCursor);
            requestPtr.setWaitingTime(statistics.getWorkingTime());
            statistics.addFullWaitingTime(requestPtr.getSourceIndex(), requestPtr.getWaitingTime());
            int oldCursor = requestCursor;
            updateRequestCursor();
            statistics.addBufferStep(Events.TOOK_FROM_BUFFER, requestCursor, oldCursor,
                    requestPtr.getIndex(), requestPtr.getSourceIndex(), statistics.getWorkingTime());
            return true;
        } catch(IndexException e) { return false; }
    }
    private void updateRequestCursor() {
        requestCursor++;
        if (requestCursor >= bufferPtr.getSize()) requestCursor = 0;
    }
    private void occupyInstrument() {
        while (true) {
            if (checkFreeInstrument(instrumentCursor)) break;
            else updateInstrumentCursor();
        }
        int oldCursor = instrumentCursor;
        double processingTime = calculateExponentialDistribution();
        instrumentsList.get(instrumentCursor).occupyInstrument(requestPtr, statistics.getWorkingTime(), processingTime);
        updateInstrumentCursor();
        statistics.addInstrumentStep(Events.LOAD_INST, instrumentCursor, instrumentsList.get(oldCursor).getIndex(),
                requestPtr.getIndex(), requestPtr.getSourceIndex(), statistics.getWorkingTime());

        checkInstruments();
    }
    private void updateInstrumentCursor() {
        instrumentCursor++;
        if (instrumentCursor >= instrumentsList.size()) instrumentCursor = 0;
    }
    private boolean checkFreeInstrument(int index) {
        if (index >= instrumentsList.size()) return false;
        return instrumentsList.get(index).getFreeFlag();
    }
    private void updateTime(Instruments instrument) {
        int sourceInd = instrument.getWorkingRequest().getSourceIndex();
        statistics.addProcessingTime(sourceInd,
                instrument.getWorkingRequest().getProcessingTime());
        statistics.addSystemTime(sourceInd,
                instrument.getWorkingRequest().getTimeInSystem());
    }
    private double calculateExponentialDistribution() {
        return -(1 / lambda) * Math.log(1 - Math.random());
    }
    public void stopAllInstruments() {
        boolean flag = checkBusyInstruments();
        while (flag) {
            stopAnyInstrument();
            flag = checkBusyInstruments();
        }
    }
    public void stopAnyInstrument() {
          if (checkBusyInstruments()) {
            ArrayList<Instruments> busyInstruments = new ArrayList<>();
            for (Instruments instrument : instrumentsList) {
                if (!instrument.getFreeFlag())
                    busyInstruments.add(instrument);
            }
            int minInd = 0;
            for (int i = 0; i < busyInstruments.size(); i++) {
                if (busyInstruments.get(minInd).getEndTime() > busyInstruments.get(i).getEndTime())
                    minInd = i;
            }
            statistics.setWorkingTime(busyInstruments.get(minInd).getEndTime());
            busyInstruments.get(minInd).stopOccupation();

            updateTime(busyInstruments.get(minInd));
            statistics.addInstrumentStep(Events.DONE, instrumentCursor, busyInstruments.get(minInd).getIndex(),
                    busyInstruments.get(minInd).getWorkingRequest().getIndex(),
                    busyInstruments.get(minInd).getWorkingRequest().getSourceIndex(), statistics.getWorkingTime());
        }
    }
    @Override
    public int getBufferSize() { return bufferPtr.getSize(); }
    public ArrayList<Instruments> getInstrumentsList() { return instrumentsList; }
}
