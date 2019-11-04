package dsc.dtu.retrofitroomworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

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

        launchPadService.getAllLaunchPads().enqueue(new Callback<List<LaunchPad>>() {
            @Override
            public void onResponse(Call<List<LaunchPad>> call, Response<List<LaunchPad>> response) {
                List<LaunchPad> launchPads = response.body();
                if (launchPads != null) {
                    String text = listOfLaunchPadsToString(launchPads);
                    responseTextView.setText(text);
                } else {
                    responseTextView.setText("An Error Occurred");
                }
            }

            @Override
            public void onFailure(Call<List<LaunchPad>> call, Throwable t) {
                responseTextView.setText("An Error Occured");
                t.printStackTrace();
            }
        });
    }

    /**
     * A helper method to convert a List of LaunchPads into a String
     */
    private String listOfLaunchPadsToString(List<LaunchPad> launchPads) {

        StringBuilder sb = new StringBuilder();

        for (LaunchPad launchPad: launchPads) {
            sb.append(launchPad.name).append("\n");
        }

        return sb.toString();
    }
}
