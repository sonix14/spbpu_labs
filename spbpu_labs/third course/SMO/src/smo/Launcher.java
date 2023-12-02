package com.example.smo;

import java.util.ArrayList;
import java.util.Random;

public class Launcher {
    private final InputDispatcher inputDispatcher;
    private final OutputDispatcher outputDispatcher;
    private final Buffer buffer;
    private final Statistics statistics;
    private final int countOfSources;
    private final int countOfRequests;
    private final int countOfInstruments;

    public Launcher(int countOfSources_, int countOfRequests_, int countOfInstruments_ ,
                    int bufferSize, double alpha, double beta, double lambda) {
        statistics = new Statistics(countOfSources_, countOfInstruments_, countOfRequests_, bufferSize);
        buffer = new Buffer(bufferSize);
        countOfSources = countOfSources_;
        countOfRequests = countOfRequests_;
        countOfInstruments = countOfInstruments_;
        inputDispatcher = new InputDispatcher(buffer, statistics, alpha, beta);
        outputDispatcher = new OutputDispatcher(buffer, statistics, lambda);
    }
    public Statistics run() {
        Source.resetCounter();
        Requests.resetCounter();
        Instruments.resetCounter();

        ArrayList<Source> sourceList = new ArrayList<>();
        for (int i = 0; i < countOfSources; i++) {
            Source source = new Source();
            sourceList.add(source);
        }
        statistics.setSources(sourceList);

        for (int i = 0; i < countOfInstruments; i++) {
            Instruments instrument = new Instruments();
            outputDispatcher.addInstrument(instrument);
        }
        statistics.setInstruments(outputDispatcher.getInstrumentsList());

        Random rn = new Random();
        for (int i = 0; i < countOfRequests; i++) {
            int ind = rn.nextInt(sourceList.size());
            inputDispatcher.deliverRequest(sourceList.get(ind));
            outputDispatcher.deliverRequest();
        }

        while (!buffer.isEmpty()) {
            if (outputDispatcher.deliverRequest() == 1) {
                outputDispatcher.stopAnyInstrument();
            }
        }
        outputDispatcher.stopAllInstruments();

        return statistics;
    }
}
