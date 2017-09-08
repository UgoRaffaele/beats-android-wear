package eu.ugopiemontese.beats;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;
import java.util.ArrayList;

import com.orm.query.Select;
import com.orm.query.Condition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.charts.LineChart;

import eu.ugopiemontese.beats.orm.Beat;
import eu.ugopiemontese.beats.utils.MinuteAxisValueFormatter;

public class MainActivity extends Activity {

    private LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart = (LineChart) findViewById(R.id.chart);

        chart.setDescription(null);
        chart.getLegend().setEnabled(false);

        chart.getXAxis().setDrawLabels(true);
        chart.getXAxis().setLabelCount(4, true);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisLeft().setDrawLabels(false);

        chart.getAxisRight().setEnabled(false);
        chart.getAxisRight().setDrawLabels(false);

        List<Entry> entries = new ArrayList<Entry>();

        //List<Beat> mBeats = Beat.listAll(Beat.class);
        List<Beat> mBeats = Select.from(Beat.class)
                .where(Condition.prop("millis").gt(Long.toString(System.currentTimeMillis() - (12 * 60 * 60 * 1000))))
                .orderBy("millis")
                .list();

        for (Beat mBeat: mBeats) {

            entries.add(new Entry(mBeat.getMillis(), mBeat.getValue()));

        }

        LineDataSet dataSet = new LineDataSet(entries, getString(R.string.app_name));
        dataSet.setCircleRadius(3f);
        dataSet.setDrawCircleHole(false);
        dataSet.setColor(getColor(R.color.red));
        dataSet.setCircleColor(getColor(R.color.red));
        dataSet.setValueTextColor(getColor(R.color.white));

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);

        chart.getXAxis().setTextColor(getColor(R.color.white));
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setValueFormatter(new MinuteAxisValueFormatter());

        chart.invalidate();

    }

}
