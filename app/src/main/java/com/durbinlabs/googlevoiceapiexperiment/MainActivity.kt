package com.durbinlabs.googlevoiceapiexperiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* // Get the intent
        Intent intent = getIntent();
        if (AlarmClock.ACTION_SET_ALARM.equals(intent.getAction())) {
            if (intent.hasExtra(AlarmClock.EXTRA_HOUR)) {
                // Step 2: get the rest of the intent extras and set an alarm
                ...
            }

            // Step 3: report the action through the App Indexing API
            Thing alarm = new Thing.Builder()
                .setName("Alarm for 4:00 PM")
                .setDescription("Alarm set for 4:00 PM, with the 'Argon' ringtone"
                        + " and vibrate turned on.")
                .setUrl(APP_URI)
                .build();

            Action setAlarmAction = new Action.Builder(Action.TYPE_ADD)
                .setObject(alarm)
                .setActionStatus(NotificationCompat.Action.STATUS_TYPE_COMPLETED)
                .build();

            AppIndex.AppIndexApi.end(mClient, setAlarmAction);*/

        }
}
