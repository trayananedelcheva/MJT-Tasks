package com.sap.mjt.current2022.lecture9;

import com.sap.mjt.current2022.lecture9.pit.Pit;

import java.util.ArrayList;
import java.util.List;

public class RaceTrack implements Track {
    private int nPitTeams;

    private Pit pit;

    private List<Integer> listOfFinishedCarsIds;

    public RaceTrack(int nPitTeams) {
        if (nPitTeams < 0) {
            throw new IllegalArgumentException("Number of pit teams should not be negative number or zero!");
        }

        this.nPitTeams = nPitTeams;
        pit = new Pit(nPitTeams);

        listOfFinishedCarsIds = new ArrayList<>();
    }

    @Override
    public void enterPit(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car value should not be null!");
        }

        pit.submitCar(car);
    }

    @Override
    public int getNumberOfFinishedCars() {
        return this.listOfFinishedCarsIds.size();
    }

    @Override
    public List<Integer> getFinishedCarsIds() {
        return this.listOfFinishedCarsIds;
    }

    @Override
    public Pit getPit() {
        return this.pit;
    }
}
