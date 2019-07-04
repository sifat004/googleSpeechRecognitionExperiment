package com.durbinlabs.googlevoiceapiexperiment;

import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.appindexing.*;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.builders.Actions;


public class VoiceActivity extends AppCompatActivity {
    private static final Uri ALARM_URI = Uri.parse("android-app://com.myclockapp/set_alarm_page");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice2);


        // Get the intent
        Intent intent = getIntent();
        if (AlarmClock.ACTION_SET_ALARM.equals(intent.getAction())) {
            if (intent.hasExtra(AlarmClock.EXTRA_HOUR)) {
                // Step 2: get the rest of the intent extras and set an alarm

            }

            // Step 3: report the action through the App Indexing API


        }
}

  /*  @Override
    protected void onStart() {
        super.onStart();

        //FirebaseAppIndex.getInstance().update(getIndexable());
        FirebaseUserActions.getInstance().start(getAction());
    }

    @Override
    protected void onStop() {
        FirebaseUserActions.getInstance().end(getAction());
        super.onStop();
    }

    public Action getAction() {
        return Actions.newView(mText, mUrl);
    }*/

}
