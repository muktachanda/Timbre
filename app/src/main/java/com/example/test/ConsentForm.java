package com.example.test;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class ConsentForm extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consentform);
    }

    public void acceptForm(View view) {
        startActivity(new Intent(ConsentForm.this, MainActivity2.class));
        finish();
    }

}