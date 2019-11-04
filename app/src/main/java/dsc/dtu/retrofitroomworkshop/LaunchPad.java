package dsc.dtu.retrofitroomworkshop;

import java.util.List;

/**
 * A simple Java model class to represent a LaunchPad object parsed from the received JSON.
 */
public class LaunchPad {

    private final int id;
    private final String name;
    private final List<String> vehiclesLaunched;
    private final List<String> attemptedLaunches;
    private final int successfulLaunches;
    private final String wikipedia;


    public LaunchPad(int id, String name, List<String> vehiclesLaunched, List<String> attemptedLaunches, int successfulLaunches, String wikipedia) {
        this.id = id;
        this.name = name;
        this.vehiclesLaunched = vehiclesLaunched;
        this.attemptedLaunches = attemptedLaunches;
        this.successfulLaunches = successfulLaunches;
        this.wikipedia = wikipedia;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getVehiclesLaunched() {
        return vehiclesLaunched;
    }

    public List<String> getAttemptedLaunches() {
        return attemptedLaunches;
    }

    public int getSuccessfulLaunches() {
        return successfulLaunches;
    }

    public String getWikipedia() {
        return wikipedia;
    }
}

