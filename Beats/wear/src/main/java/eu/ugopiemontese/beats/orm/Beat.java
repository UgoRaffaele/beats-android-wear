package eu.ugopiemontese.beats.orm;

import com.orm.SugarRecord;

public class Beat extends SugarRecord {

    private long millis;
    private int value;
    private int accuracy;

    public Beat() {}

    public Beat(long millis, int value, int accuracy) {
        this.millis = millis;
        this.value = value;
        this.accuracy = accuracy;
    }

    public long getMillis() {
        return millis;
    }

    public int getValue() {
        return value;
    }

    public int getAccuracy() {
        return accuracy;
    }

}
