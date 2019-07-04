package com.durbinlabs.googlevoiceapiexperiment

import android.Manifest
import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class VoiceRecognitionActivity : AppCompatActivity(), RecognitionListener {
    private var returnedText: TextView? = null
    private var toggleButton: ToggleButton? = null
    private var progressBar: ProgressBar? = null
    private var speech: SpeechRecognizer? = null
    private var recognizerIntent: Intent? = null
    private val LOG_TAG = "VoiceRecgnitionActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceivers(this)
        /*HardwareButtonReceiver2 receiver = new HardwareButtonReceiver2();

        registerReceiver(receiver,new IntentFilter("android.intent.action.MEDIA_BUTTON"));
*/

        returnedText = findViewById<View>(R.id.tvReturned) as TextView
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        toggleButton = findViewById<View>(R.id.toggleButton) as ToggleButton


        progressBar!!.visibility = View.INVISIBLE
        speech = SpeechRecognizer.createSpeechRecognizer(this)
        Log.e(LOG_TAG, "isRecognitionAvailable: " + SpeechRecognizer.isRecognitionAvailable(this))
        speech!!.setRecognitionListener(this)
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent!!.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
            "en")
        recognizerIntent!!.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        recognizerIntent!!.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
        // startService();
        toggleButton!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                progressBar!!.visibility = View.VISIBLE
                progressBar!!.isIndeterminate = true
                ActivityCompat.requestPermissions(this@VoiceRecognitionActivity,
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_PERMISSION)
            } else {
                progressBar!!.isIndeterminate = false
                progressBar!!.visibility = View.INVISIBLE
                speech!!.stopListening()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_RECORD_PERMISSION -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                speech!!.startListening(recognizerIntent)
            } else {
                Toast.makeText(this@VoiceRecognitionActivity, "Permission Denied!", Toast
                    .LENGTH_SHORT).show()
            }
        }
    }

    public override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        if (speech != null) {
            speech!!.destroy()
            Log.i(LOG_TAG, "destroy")
        }
    }

    override fun onBeginningOfSpeech() {
        Log.d(LOG_TAG, "onBeginningOfSpeech")
        progressBar!!.isIndeterminate = false
        progressBar!!.max = 10
    }

    override fun onBufferReceived(buffer: ByteArray) {
        Log.d(LOG_TAG, "onBufferReceived: $buffer")
    }

    override fun onEndOfSpeech() {
        Log.d(LOG_TAG, "onEndOfSpeech")
        progressBar!!.isIndeterminate = true
        toggleButton!!.isChecked = false
    }

    override fun onError(errorCode: Int) {
        val errorMessage = getErrorText(errorCode)
        Log.d(LOG_TAG, "FAILED $errorMessage")
        returnedText!!.text = errorMessage
        toggleButton!!.isChecked = false
    }

    override fun onEvent(arg0: Int, arg1: Bundle) {
        Log.d(LOG_TAG, "onEvent")
    }

    override fun onPartialResults(arg0: Bundle) {
        Log.d(LOG_TAG, "onPartialResults")
    }

    override fun onReadyForSpeech(arg0: Bundle) {
        Log.i(LOG_TAG, "onReadyForSpeech")
    }

    override fun onResults(results: Bundle) {
        Log.d(LOG_TAG, "onResults")
        val matches = results
            .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        var text = ""
        for (result in matches!!)
            text += result + "\n"

        returnedText!!.text = text
    }

    override fun onRmsChanged(rmsdB: Float) {
        Log.d(LOG_TAG, "onRmsChanged: $rmsdB")
        progressBar!!.progress = rmsdB.toInt()
    }

    internal fun startService() {
        val voiceRecognitionService = VoiceRecognitionService(this.applicationContext)
        val mServiceIntent = Intent(this, voiceRecognitionService.javaClass)
        if (!isMyServiceRunning(voiceRecognitionService.javaClass)) {
            startService(mServiceIntent)
        }
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                Log.d("isMyServiceRunning?", true.toString() + "")
                return true
            }
        }
        Log.d("isMyServiceRunning?", false.toString() + "")
        return false
    }

/*   inner class HardwareButtonReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //            Log.e("HardwareButtonReceiver2","here I am");
            //            Intent intent1 = new Intent(context, MainActivity.class);
            //            // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //            context.startActivity(intent1);
            speech?.startListening(recognizerIntent)
        }
    }*/

    companion object {
        private val REQUEST_RECORD_PERMISSION = 100
        fun getErrorText(errorCode: Int): String {
            val message: String
            when (errorCode) {
                SpeechRecognizer.ERROR_AUDIO -> message = "Audio recording error"
                SpeechRecognizer.ERROR_CLIENT -> message = "Client side error"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> message = "Insufficient permissions"
                SpeechRecognizer.ERROR_NETWORK -> message = "Network error"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> message = "Network timeout"
                SpeechRecognizer.ERROR_NO_MATCH -> message = "No match"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> message = "RecognitionService busy"
                SpeechRecognizer.ERROR_SERVER -> message = "error from server"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> message = "No speech input"
                else -> message = "Didn't understand, please try again."
            }
            return message
        }

        @Volatile
        private var receiversRegistered = false

        private fun registerReceivers(contextIn: Context) {
            if (receiversRegistered) return
            val context = contextIn.applicationContext
            val receiver = HardwareButtonReceiver2()
            val providerChanged = IntentFilter()
            providerChanged.addAction("android.media.VOLUME_CHANGED_ACTION")
            context.registerReceiver(receiver, providerChanged)
            Log.i(HardwareButtonReceiver2::class.java.name, "Registered receivers from " + contextIn.javaClass.name)
            receiversRegistered = true
        }
    }
}
