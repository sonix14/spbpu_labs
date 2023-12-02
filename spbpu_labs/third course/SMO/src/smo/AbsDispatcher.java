package com.example.smo;

public abstract class AbsDispatcher {
    protected Buffer bufferPtr;
    protected Statistics statistics;
    protected Requests requestPtr;

    public abstract int getBufferSize();
}

