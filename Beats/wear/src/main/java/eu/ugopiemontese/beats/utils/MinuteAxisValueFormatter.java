package eu.ugopiemontese.beats.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MinuteAxisValueFormatter implements IAxisValueFormatter {


    public MinuteAxisValueFormatter() {}

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        Date date = new Date((long) value);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateFormatted = formatter.format(date);

        return dateFormatted;
    }

}