package dsc.dtu.retrofitroomworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseTextView = findViewById(R.id.responseTextView);

        LaunchPadService launchPadService = Provider.getLaunchPadService();
        List<LaunchPad> launchPads = launchPadService.getAllLaunchPads();

        String text = listOfLaunchPadsToString(launchPads);
        responseTextView.setText(text);
    }

    /**
     * A helper method to convert a List of LaunchPads into a String
     */
    private String listOfLaunchPadsToString(List<LaunchPad> launchPads) {

        StringBuilder sb = new StringBuilder();

        for (LaunchPad launchPad: launchPads) {
            sb.append(launchPad.toString()).append("\n");
        }

        return sb.toString();
    }
}
