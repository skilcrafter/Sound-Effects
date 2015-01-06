package com.example.soundeffects;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends Activity {
    public int soundFile;
    private static final String LOG_TAG = "AudioRecordTest";
    private MediaRecorder mRecorder = null;
    private static String mFileName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button6 = (Button) findViewById(R.id.button4);
        final Button button7 = (Button) findViewById(R.id.button5);
        final Button button8 = (Button) findViewById(R.id.button6);
        final Button button9 = (Button) findViewById(R.id.button8);
        final Button button10 = (Button) findViewById(R.id.button7);
        final Button button11 = (Button) findViewById(R.id.button9);

        button10.setEnabled(false);
        button11.setEnabled(false);

        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button1:
                        soundFile = R.raw.burp1;
                        playSound1();
                        break;
                    case R.id.button2:
                        soundFile = R.raw.air_horn;
                        playSound1();
                        break;
                    case R.id.button3:
                        soundFile = R.raw.angry_chipmunk;
                        playSound1();
                        break;
                    case R.id.button4:
                        soundFile = R.raw.pew_pew;
                        playSound1();
                        break;
                    case R.id.button5:
                        soundFile = R.raw.silly_snoring;
                        playSound1();
                        break;
                    case R.id.button6:
                        soundFile = R.raw.strange_growl;
                        playSound1();
                        break;
                    case R.id.button8:
                        button11.setEnabled(true);
                        startRecording();
                        button9.setEnabled(false);
                        break;
                    case R.id.button7:
                        playRecording();
                        break;
                    case R.id.button9:
                        stopRecording();
                        button9.setEnabled(true);
                        button10.setEnabled(true);
                        button11.setEnabled(false);
                        break;
                }

            }
        };
        button1.setOnClickListener(Listener);
        button2.setOnClickListener(Listener);
        button3.setOnClickListener(Listener);
        button6.setOnClickListener(Listener);
        button7.setOnClickListener(Listener);
        button8.setOnClickListener(Listener);
        button9.setOnClickListener(Listener);
        button10.setOnClickListener(Listener);
        button11.setOnClickListener(Listener);

    }

    private void playSound1() {
        MediaPlayer player = MediaPlayer.create(this,soundFile );
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

    }

    private void playRecording() {
        MediaPlayer player = new MediaPlayer();

        try {
            player.setDataSource(mFileName);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();

    }

   private void startRecording() {

        mFileName = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/myrecording.3gp";;
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile(mFileName);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
        Toast.makeText(getApplicationContext(), "Recording",
               Toast.LENGTH_LONG).show();

    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;

        Toast.makeText(getApplicationContext(), "Audio recorded successfully",
                Toast.LENGTH_LONG).show();
    }

}
