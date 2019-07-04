package com.durbinlabs.googlevoiceapiexperiment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Sifat Ullah Chowdhury on 7/4/2019.
 * Durbin Labs Ltd
 * sif.sifat24@gmail.com
 */
public class ServiceRestarterBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(ServiceRestarterBroadcastReceiver.class.getSimpleName(), "Service Stops! Oooooooooooooppppssssss!!!!");
        context.startService(new Intent(context, VoiceRecognitionService.class));
    }
}
