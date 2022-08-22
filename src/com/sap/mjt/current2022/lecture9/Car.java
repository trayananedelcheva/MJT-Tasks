package com.sap.mjt.current2022.lecture9;

import java.util.Random;

public class Car implements Runnable {
    private int id;
    private int nPitStops;
    private Track track;

    public Car(int id, int nPitStops, Track track) {
        checkParametersValidity(id, nPitStops, track);

        this.id = id;
        this.nPitStops = nPitStops;
        this.track = track;
    }

    private void checkParametersValidity(int id, int nPitStops, Track track) {
        if (id < 0) {
            throw new IllegalArgumentException("Id value should not be negative number!");
        }

        if (nPitStops < 0) {
            throw new IllegalArgumentException("Number of pit stops should not be negative number!");
        }

        if (track == null) {
            throw new IllegalArgumentException("Track value should not be null!");
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < nPitStops; i++) {
            try {
                Thread.sleep(new Random().nextInt(1));
                track.enterPit(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.track.getFinishedCarsIds().add(this.id);
    }

    public int getCarId() {
        return this.id;
    }

    public int getNPitStops() {
        return this.nPitStops;
    }

    public Track getTrack() {
        return this.track;
    }
}
