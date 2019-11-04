package dsc.dtu.retrofitroomworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import dsc.dtu.retrofitroomworkshop.api.LaunchPadApi;
import dsc.dtu.retrofitroomworkshop.api.LaunchPadService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseTextView = findViewById(R.id.responseTextView);

        LaunchPadService launchPadService = Provider.getLaunchPadService();

        launchPadService.getAllLaunchPads().enqueue(new Callback<List<LaunchPadApi>>() {
            @Override
            public void onResponse(Call<List<LaunchPadApi>> call, Response<List<LaunchPadApi>> response) {
                List<LaunchPadApi> launchPads = response.body();
                if (launchPads != null) {
                    String text = listOfLaunchPadsToString(launchPads);
                    responseTextView.setText(text);
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
