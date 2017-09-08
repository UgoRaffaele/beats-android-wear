package eu.ugopiemontese.beats;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import eu.ugopiemontese.beats.orm.Beat;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);


                List<Beat> mBeats = Beat.listAll(Beat.class);

                for (Beat mBeat: mBeats) {

                    Date date = new Date(mBeat.getMillis());
                    DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    String dateFormatted = formatter.format(date);

                    mTextView.append("\nTime: " + dateFormatted + " Value: " + mBeat.getValue() + " Accuracy: " + mBeat.getAccuracy());
                    Log.d("mBeat", "Time: " + dateFormatted + " Value: " + mBeat.getValue() + " Accuracy: " + mBeat.getAccuracy());

                }


            }
        });

    }

}
