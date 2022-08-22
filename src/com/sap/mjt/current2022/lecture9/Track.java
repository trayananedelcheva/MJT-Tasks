package com.sap.mjt.current2022.lecture9;

import com.sap.mjt.current2022.lecture9.pit.Pit;

import java.util.List;

public interface Track {
    /**
     * A car enters the pit when it needs maintenance or when it finishes the race.
     * A car can finish the race, if it has no more pitStops to be done.
     */
    void enterPit(Car car);

    /**
     * Returns the number of cars which already finished the race.
     *
     * @return the number of cars which already finished the race
     */
    int getNumberOfFinishedCars();

    /**
     * Returns the ids of the cars which already finished the race.
     *
     * @return the ids of the cars which already finished the race
     */
    List<Integer> getFinishedCarsIds();

    /**
     * Returns the pit.
     *
     * @return the pit
     */
    Pit getPit();
}
