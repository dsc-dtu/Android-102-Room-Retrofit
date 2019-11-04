package dsc.dtu.retrofitroomworkshop.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LaunchPadDao {

    @Query("SELECT * FROM launchpad")
    List<LaunchPadDb> getAllLaunchPads();

    @Insert
    void saveAllLaunchPads(List<LaunchPadDb> launchPads);

}
