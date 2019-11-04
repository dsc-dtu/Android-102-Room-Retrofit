package dsc.dtu.retrofitroomworkshop.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "launchpad")
public class LaunchPadDb {

    @ColumnInfo(name = "id")
    @PrimaryKey
    public final int id;

    @ColumnInfo(name = "name")
    public final String name;

    @ColumnInfo(name = "vehicles_launched")
    public final List<String> vehiclesLaunched;

    @ColumnInfo(name = "attempted_launches")
    public final int attemptedLaunches;

    @ColumnInfo(name = "successful_launches")
    public final int successfulLaunches;

    @ColumnInfo(name = "wikipedia")
    public final String wikipedia;


    public LaunchPadDb(int id, String name, List<String> vehiclesLaunched, int attemptedLaunches, int successfulLaunches, String wikipedia) {
        this.id = id;
        this.name = name;
        this.vehiclesLaunched = vehiclesLaunched;
        this.attemptedLaunches = attemptedLaunches;
        this.successfulLaunches = successfulLaunches;
        this.wikipedia = wikipedia;
    }
}
