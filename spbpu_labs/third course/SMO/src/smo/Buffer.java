package com.example.smo;

import com.example.smo.exceptions.*;

import java.util.ArrayList;

public class Buffer {
    private final ArrayList<Requests> buffer;
    private final int size;
    private int occupiedSize;
    private int lastRequestPlace;

    public Buffer(int size_) {
        size = size_;
        buffer = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            buffer.add(i, null);
        }
        occupiedSize = 0;
        lastRequestPlace = -1;
    }
    public boolean checkEmptyPlace(int index) {
        if (index >= size) return false;
        return buffer.get(index) == null;
    }
    public boolean isFull() {
        return size == occupiedSize;
    }
    public boolean isEmpty() {
        return occupiedSize == 0;
    }
    private void updateOccupiedSize(boolean flag) {
        if (flag) occupiedSize++;
        else occupiedSize--;
    }
    public int putIn(Requests request) throws OverflowException {
        if (isFull()) throw new OverflowException();
        int place;
        for (place = 0; place < size; place++) {
            if (buffer.get(place) == null) {
                lastRequestPlace = place;
                buffer.set(place, request);
                break;
            }
        }
        updateOccupiedSize(true);
        return place;
    }
    public Requests popOut(int index) throws IndexException {
        if (index < 0 || index >= size) throw new IndexException();
        Requests request = buffer.get(index);
        buffer.set(index, null);
        updateOccupiedSize(false);
        return request;
    }
    public Requests pushOutRejectedRequest() {
        Requests req = buffer.get(lastRequestPlace);
        buffer.set(lastRequestPlace, null);
        updateOccupiedSize(false);
        return req;
    }
    public int getSize() { return size; }
    public int getOccupiedSize() { return occupiedSize; }
    public int getLastRequestPlace() { return lastRequestPlace; }
}
