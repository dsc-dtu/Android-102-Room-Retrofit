package dsc.dtu.retrofitroomworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dsc.dtu.retrofitroomworkshop.api.LaunchPadApi;
import dsc.dtu.retrofitroomworkshop.api.LaunchPadService;
import dsc.dtu.retrofitroomworkshop.database.LaunchPadDao;
import dsc.dtu.retrofitroomworkshop.database.LaunchPadDb;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView responseTextView;
    private Context context;
    private LaunchPadService launchPadService;
    private LaunchPadDao launchPadDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Provider.createDatabase(this);

        context = this;
        responseTextView = findViewById(R.id.responseTextView);
        launchPadService = Provider.getLaunchPadService();
        launchPadDao = Provider.getLaunchPadDao();

        getLaunchPads();
    }

    /**
     * A helper method to initiate the fetching and saving of Launch pads.
     */
    private void getLaunchPads() {
        launchPadService.getAllLaunchPads().enqueue(new Callback<List<LaunchPadApi>>() {
            @Override
            public void onResponse(Call<List<LaunchPadApi>> call, Response<List<LaunchPadApi>> response) {
                List<LaunchPadApi> launchPads = response.body();
                if (launchPads != null) {
                    String text = listOfLaunchPadsToString(launchPads);
                    responseTextView.setText(text);
                    Toast.makeText(context, "Saving data to DB", Toast.LENGTH_LONG).show();
                    saveLaunchPads(launchPads);
                } else {
                    responseTextView.setText("An Error Occurred");
                }
            }

            @Override
            public void onFailure(Call<List<LaunchPadApi>> call, Throwable t) {
                responseTextView.setText("An Error Occured");
                t.printStackTrace();
            }
        });
    }

    /**
     * A helper method to save a list of LaunchPads received from the API to the database
     * on a background thread.
     */
    private void saveLaunchPads(List<LaunchPadApi> launchPads) {
        final List<LaunchPadDb> launchPadDbs = convertToLaunchPadDbs(launchPads);

        AppExecutors.getBackgroundExecutor().submit(new Runnable() {
            @Override
            public void run() {
                launchPadDao.saveAllLaunchPads(launchPadDbs);
            }
        });

    }

    /**
     * A helper method to convert a list of API LaunchPad models to DB LaunchPad models.
     */
    private static List<LaunchPadDb> convertToLaunchPadDbs(List<LaunchPadApi> launchPads) {
        List<LaunchPadDb> launchPadDbs = new ArrayList<>();
        for (LaunchPadApi launchPadApi: launchPads) {
            LaunchPadDb launchPadDb = new LaunchPadDb(launchPadApi.id, launchPadApi.name, launchPadApi.vehiclesLaunched, launchPadApi.attemptedLaunches, launchPadApi.successfulLaunches, launchPadApi.wikipedia);
            launchPadDbs.add(launchPadDb);
        }
        return launchPadDbs;
    }

    /**
     * A helper method to convert a List of LaunchPads into a String
     */
    private String listOfLaunchPadsToString(List<LaunchPadApi> launchPads) {

        StringBuilder sb = new StringBuilder();

        for (LaunchPadApi launchPadApi : launchPads) {
            sb.append(launchPadApi.name).append("\n");
        }

        return sb.toString();
    }
}
