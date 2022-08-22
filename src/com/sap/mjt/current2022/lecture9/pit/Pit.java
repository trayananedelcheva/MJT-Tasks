package com.sap.mjt.current2022.lecture9.pit;

import com.sap.mjt.current2022.lecture9.Car;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Pit {
    private int nPitTeams;
    private boolean isRaceFinished = false;
    private AtomicInteger nPitStops = new AtomicInteger(0);

    Queue<Car> waitingCars;
    List<PitTeam> pitTeams;

    public Pit(int nPitTeams) {
        if (nPitTeams < 0) {
            throw new IllegalArgumentException("Number of pit teams should not be negative number or zero!");
        }

        pitTeams = new ArrayList<>();
        this.waitingCars = new LinkedList<>();

        this.nPitTeams = nPitTeams;
        for (int i = 0; i < nPitTeams; i++) {
            PitTeam pitTeam = new PitTeam(i, this);
            pitTeam.start();
            pitTeams.add(pitTeam);
        }
    }

    public void submitCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car value should not be null!");
        }

        synchronized (waitingCars) {
            waitingCars.add(car);
            waitingCars.notifyAll();
        }

        nPitStops.addAndGet(1);
    }

    public Car getCar() {
        waitForCars();

        return waitingCars.poll();
    }

    private void waitForCars() {
        synchronized (waitingCars) {
            while (waitingCars.isEmpty()) {
                System.out.println("stuck");
                if (!isRaceFinished()) {
                    try {
                        waitingCars.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public int getPitStopsCount() {
        return this.nPitStops.get();
    }

    public List<PitTeam> getPitTeams() {
        return this.pitTeams;
    }

    private boolean isRaceFinished() {
        return this.isRaceFinished;
    }

    public void finishRace() {
        this.isRaceFinished = true;
    }
}
