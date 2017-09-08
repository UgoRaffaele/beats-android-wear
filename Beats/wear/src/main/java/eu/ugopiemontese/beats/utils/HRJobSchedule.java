package eu.ugopiemontese.beats.utils;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import eu.ugopiemontese.beats.HRJobService;

public class HRJobSchedule {

    // Schedule the job every 60 minutes
    private final static long DELAY = 60;

    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, HRJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);

        builder.setPeriodic(DELAY * 60 * 1000);

        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(builder.build());
    }

}
