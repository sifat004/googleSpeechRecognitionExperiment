package com.durbinlabs.googlevoiceapiexperiment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

/**
 * Created by Sifat Ullah Chowdhury on 7/4/2019.
 * Durbin Labs Ltd
 * sif.sifat24@gmail.com
 */
public class HardwareButtonReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("HardwareButtonReceiver2","here I am");
        Intent intent1 = new Intent(context, MainActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);



       /* if (intent.getExtras()==null) return;
        KeyEvent e = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);

        if (e==null) return;
        if(e.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
            Intent intent2 = new Intent(context, VoiceRecognitionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent2);        }*/
    }

}
