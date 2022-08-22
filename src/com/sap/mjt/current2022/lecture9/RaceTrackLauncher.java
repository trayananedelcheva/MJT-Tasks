package com.sap.mjt.current2022.lecture9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RaceTrackLauncher {
    private static final int NUMBER_OF_CARS = 5;
    private static final int NUMBER_OF_PIT_TEAMS = 3;

    private static List<Car> cars = new ArrayList<>();

    private static void startCarRacing(Track track) {
        for (int i = 0; i < NUMBER_OF_CARS; i++) {
            Car car = new Car(i, new Random().nextInt(6), track);
            car.run();
            cars.add(car);
        }
    }

    public static void main(String[] args) {
        RaceTrack raceTrack = new RaceTrack(NUMBER_OF_PIT_TEAMS);

        startCarRacing(raceTrack);

        if (raceTrack.getNumberOfFinishedCars() == NUMBER_OF_CARS) {
            System.out.println("All cars that started the race, finished it!");
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        raceTrack.getPit().finishRace();
        System.out.println(raceTrack.getPit().getPitStopsCount());
    }
}
