package dsc.dtu.retrofitroomworkshop;

import androidx.annotation.NonNull;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * A simple Java model class to represent a LaunchPad object parsed from the received JSON.
 */
public class LaunchPad {

    @Json(name = "id")
    public final int id;

    @Json(name = "name")
    public final String name;

    @Json(name = "vehicles_launches")
    public final List<String> vehiclesLaunched;

    @Json(name = "attempted_launches")
    public final int attemptedLaunches;

    @Json(name = "successful_launches")
    public final int successfulLaunches;

    @Json(name = "wikipedia")
    public final String wikipedia;


    public LaunchPad(int id, String name, List<String> vehiclesLaunched, int attemptedLaunches, int successfulLaunches, String wikipedia) {
        this.id = id;
        this.name = name;
        this.vehiclesLaunched = vehiclesLaunched;
        this.attemptedLaunches = attemptedLaunches;
        this.successfulLaunches = successfulLaunches;
        this.wikipedia = wikipedia;
    }
}

