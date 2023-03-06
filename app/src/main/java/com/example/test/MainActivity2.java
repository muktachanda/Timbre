package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
    private EditText editTextHeightFeet, editTextHeightInches, editTextWeight;
    private TextView textViewBMIResult;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private int heightFeet;
    private int heightInches;
    private float weight;
    private float bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize EditText fields
        editTextHeightFeet = findViewById(R.id.editTextHeightFeet);
        editTextHeightInches = findViewById(R.id.editTextHeightInches);
        editTextWeight = findViewById(R.id.editTextWeight);

        // Initialize TextView field
        textViewBMIResult = findViewById(R.id.textViewBMIResult);

        // Add onTextChanged listeners
        editTextHeightFeet.addTextChangedListener(textWatcher);
        editTextHeightInches.addTextChangedListener(textWatcher);
        editTextWeight.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Do nothing
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calculateBMI();
        }

        public void afterTextChanged(Editable s) {
            // Do nothing
        }
    };

    private void calculateBMI() {
        // Get the user inputs for height and weight
        EditText heightFeetEditText = findViewById(R.id.editTextHeightFeet);
        EditText heightInchesEditText = findViewById(R.id.editTextHeightInches);
        EditText weightEditText = findViewById(R.id.editTextWeight);

        String heightFeetStr = heightFeetEditText.getText().toString();
        String heightInchesStr = heightInchesEditText.getText().toString();
        String weightStr = weightEditText.getText().toString();

        // Convert the height and weight strings to float values
        float heightFeet = heightFeetStr.isEmpty() ? 0 : Float.parseFloat(heightFeetStr);
        float heightInches = heightInchesStr.isEmpty() ? 0 : Float.parseFloat(heightInchesStr);
        float weight = weightStr.isEmpty() ? 0 : Float.parseFloat(weightStr);

        // Convert the height values from feet and inches to meters
        float heightMeters = (heightFeet * 0.3048f) + (heightInches * 0.0254f);

        // Calculate the BMI
        bmi = weight / (heightMeters * heightMeters);

        // Display the BMI result in the textViewBMIResult
        TextView bmiResultTextView = findViewById(R.id.textViewBMIResult);
        bmiResultTextView.setText("BMI: " + String.format("%.2f", bmi));
    }

    public void submitForm(View view) {
        // Get the values from the EditText fields
        firstName = editTextFirstName.getText().toString();
        lastName = editTextLastName.getText().toString();
        age = Integer.parseInt(editTextAge.getText().toString());

        // Get the selected radio button from the radio group
        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        gender = radioButton.getText().toString();

        heightFeet = Integer.parseInt(editTextHeightFeet.getText().toString());
        heightInches = Integer.parseInt(editTextHeightInches.getText().toString());
        weight = Float.parseFloat(editTextWeight.getText().toString());

        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }


}