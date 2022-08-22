package com.sap.mjt.current2022.lecture9.pit;

import com.sap.mjt.current2022.lecture9.Car;

import java.util.Random;

public class PitTeam extends Thread {
    private int id;
    private int nPitTeamStoppedCars = 0;
    private Pit pitStop;

    public PitTeam(int id, Pit pitStop) {
        checkParametersValidity(id, pitStop);

        this.id = id;
        this.pitStop = pitStop;
    }

    private void checkParametersValidity(int id, Pit pitStop) {
        if (id < 0) {
            throw new IllegalArgumentException("Id should not be negative number!");
        }

        if (pitStop == null) {
            throw new IllegalArgumentException("Pit stop should not be null!");
        }
    }

    @Override
    public void run() {
        while (!pitStop.getWaitingCars().isEmpty()) {
            Car car = pitStop.getCar();
            this.nPitTeamStoppedCars++;

            while (car != null) {
                try {
                    Thread.sleep(new Random().nextInt(200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getPitTeamStoppedCars() {
        return this.nPitTeamStoppedCars;
    }
}
