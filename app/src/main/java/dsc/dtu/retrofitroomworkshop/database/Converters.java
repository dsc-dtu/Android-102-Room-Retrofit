package dsc.dtu.retrofitroomworkshop.database;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public List<String> stringToListOfString(String str) {
        return Arrays.asList(str.split(";"));
    }

    @TypeConverter
    public String listOfStringToString(List<String> list) {

        if (list == null) { return ""; }

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str).append(";");
        }
        return sb.toString();
    }
}
