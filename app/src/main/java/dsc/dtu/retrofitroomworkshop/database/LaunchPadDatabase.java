package dsc.dtu.retrofitroomworkshop.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {LaunchPadDb.class}, version = 1, exportSchema = false)
@TypeConverters(value = {Converters.class})
public abstract class LaunchPadDatabase extends RoomDatabase {

    public abstract LaunchPadDao getLaunchPadDao();

}