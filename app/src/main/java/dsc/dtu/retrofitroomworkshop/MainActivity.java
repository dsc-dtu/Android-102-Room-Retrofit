package dsc.dtu.retrofitroomworkshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
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

        launchPadDao.getAllLaunchPads().observe(this, new Observer<List<LaunchPadDb>>() {
            @Override
            public void onChanged(List<LaunchPadDb> launchPadDbs) {
                Toast.makeText(context, "Received new data from Database", Toast.LENGTH_SHORT).show();
                String text = listOfLaunchPadsToString(launchPadDbs);
                setResponseText(text);
            }
        });

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
                    saveLaunchPads(launchPads);
                } else {
                    setResponseText("An Error Occurred");
                }
            }

            @Override
            public void onFailure(Call<List<LaunchPadApi>> call, Throwable t) {
                setResponseText("An Error Occurred");
                t.printStackTrace();
            }
        });
    }

    /**
     * A helper method to save a list of LaunchPads received from the API to the database
     * on a background thread.
     */
    private void saveLaunchPads(final List<LaunchPadApi> launchPads) {
        Toast.makeText(context, "Saving data to DB", Toast.LENGTH_SHORT).show();
        AppExecutors.getBackgroundExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<LaunchPadDb> launchPadDbs = convertToLaunchPadDbs(launchPads);
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
    private String listOfLaunchPadsToString(List<LaunchPadDb> launchPads) {

        StringBuilder sb = new StringBuilder();

        for (LaunchPadDb launchPadDb : launchPads) {
            sb.append(launchPadDb.name).append("\n");
        }

        return sb.toString();
    }

    /**
     * A helper method to set some text to response TextView.
     */
    private void setResponseText(String text) {
        if (TextUtils.isEmpty(text)) {
            responseTextView.setText("No data available");
        } else {
            responseTextView.setText(text);
        }
    }
}
