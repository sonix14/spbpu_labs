package com.example.smo;

import com.example.smo.exceptions.OverflowException;

import java.util.ArrayList;

public class InputDispatcher extends AbsDispatcher {
    private final ArrayList<Source> sourcesList;
    private final ArrayList<Integer> rejectionList;
    private final double alpha;
    private final double beta;

    public InputDispatcher(Buffer buffer, Statistics statistics_, double alpha_, double beta_) {
        statistics = statistics_;
        bufferPtr = buffer;
        requestPtr = null;
        rejectionList = new ArrayList<>();
        sourcesList = new ArrayList<>();
        alpha = alpha_;
        beta = beta_;
    }
    public void deliverRequest(Source source) {
        getRequest(source);
        putInBuffer();
    }
    private void getRequest(Source source) {
        addNewSource(source);
        requestPtr = source.makeNewRequest(statistics.getWorkingTime());
        double generationTime = calculateUniformDistribution();
        requestPtr.setGenerationTime(generationTime);

        statistics.updateWorkingTime(generationTime);
        statistics.addSourceStep(0, Events.CREATION, requestPtr.getIndex(),
                source.getIndex(), statistics.getWorkingTime());
    }
    private void addNewSource(Source source) {
        if (!sourcesList.contains(source)) { sourcesList.add(source); }
    }
    private double calculateUniformDistribution() {
        return Math.random() * (beta - alpha) + alpha;
    }
    private void putInBuffer() {
        try {
            int place = bufferPtr.putIn(requestPtr);
            statistics.addBufferStep(Events.PUT_IN_BUFFER, 0,
                    place, requestPtr.getIndex(), requestPtr.getSourceIndex(), statistics.getWorkingTime());
        } catch(OverflowException e) {
            Requests req = bufferPtr.pushOutRejectedRequest();

            rejectionList.add(rejectRequest(req));
            putInBuffer();
        }
    }
    private int rejectRequest(Requests request) {
        int reqIndex = request.getIndex();
        for (Source source : sourcesList) {
            if (source.getIndex() == request.getSourceIndex()) {
                source.updateRejectionCount();
                double reqWaitingTime = statistics.getWorkingTime() - request.getStartTime();
                statistics.addSystemTime(request.getSourceIndex(), request.getGenerationTime() + reqWaitingTime);
                statistics.addFullWaitingTime(request.getSourceIndex(), reqWaitingTime);
                statistics.addSourceStep(bufferPtr.getLastRequestPlace(), Events.REJECTED, reqIndex,
                        source.getIndex(), statistics.getWorkingTime());
                break;
            }
        }
        return reqIndex;
    }
    @Override
    public int getBufferSize() { return bufferPtr.getSize(); }
    public int getFullRejectionCount() { return rejectionList.size(); }
}
