package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.MediaRecorder;
import android.widget.Toast;
import java.io.IOException;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Environment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class MainActivity3 extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private MediaRecorder mediaRecorder;
    private String audioFilePath;
    private boolean isRecording = false;
    Button recordButton;
    private static final int SAMPLE_RATE = 44100;
    private static final int FFT_SIZE = 1024;
    private static final int WAVEFORM_COLOR = 0xff4285F4; // blue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recordButton = findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecord(v);
            }
        });

        // Request RECORD_AUDIO permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
        }
    }

    public void startRecord(View view) {
        if (recordButton == null) {
            return;
        }
        if (!isRecording) {
            try {
                prepareMediaRecorder();
                mediaRecorder.start();
                isRecording = true;
                recordButton.setText("Stop");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Stop recording
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            recordButton.setText("Record");
            Toast.makeText(getApplicationContext(), "Audio recorded in WAV format and saved to " + audioFilePath, Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity3.this, MainActivity4.class));
            finish();
        }
    }

    private String prepareMediaRecorder() throws IOException {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        String audioFileName = "audio_record.wav";
        String downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        audioFilePath = downloadsDir + "/" + audioFileName;
        mediaRecorder.setOutputFile(audioFilePath);
        mediaRecorder.prepare();
        return audioFilePath;
    }

}