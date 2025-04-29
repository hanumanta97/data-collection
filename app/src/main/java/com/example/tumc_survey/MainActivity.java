package com.example.tumc_survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "login_prefs"; // SharedPreferences name
    private static final String KEY_ATTEMPTS = "attempts"; // Key to store attempts count
    private static final String KEY_TIMESTAMP = "timestamp"; // Key to store the timestamp of the last attempt
    private static final int MAX_ATTEMPTS = 9; // Maximum allowed attempts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        EditText username = findViewById(R.id.etUsername);
        EditText password = findViewById(R.id.etPassword);
        TextView attemptsTextView = findViewById(R.id.tvAttempts); // Attempts TextView
        Button signInButton = findViewById(R.id.btnSignin);

        // Get SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        // Fetch the current attempts and timestamp
        int attempts = sharedPreferences.getInt(KEY_ATTEMPTS, 0);
        long lastAttemptTime = sharedPreferences.getLong(KEY_TIMESTAMP, 0);

        // Check if attempts are more than 5 and reset if it's a new day
        if (System.currentTimeMillis() - lastAttemptTime > 24 * 60 * 60 * 1000) { // 24 hours
            // Reset attempts if it's a new day
            editor.putInt(KEY_ATTEMPTS, 0);
            editor.putLong(KEY_TIMESTAMP, System.currentTimeMillis());
            editor.apply();
            attempts = 0;
        }

        // Update the TextView with the remaining attempts
        attemptsTextView.setText("Attempts remaining: " + (MAX_ATTEMPTS - attempts));

        // Set up the Sign-In button click listener
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Fetch the updated attempts from SharedPreferences every time
                int currentAttempts = sharedPreferences.getInt(KEY_ATTEMPTS, 0);

                String usernameInput = username.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();

                if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if attempts are less than the maximum allowed
                    if (currentAttempts >= MAX_ATTEMPTS) {
                        Toast.makeText(MainActivity.this, "You have exceeded " + MAX_ATTEMPTS + " login attempts today. Please try again tomorrow.", Toast.LENGTH_LONG).show();
                        attemptsTextView.setText("Attempts remaining: 0"); // Update the TextView
                    } else {
                        // Perform authentication (this is just a placeholder, replace it with your actual logic)
                        if (usernameInput.equals("Goat_dev") && passwordInput.equals("897654")) {
                            Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                            // Reset attempts on successful login
                            editor.putInt(KEY_ATTEMPTS, 0); // Reset attempts
                            editor.putLong(KEY_TIMESTAMP, System.currentTimeMillis()); // Update timestamp
                            editor.apply();

                            // Navigate to the WelcomeActivity after successful login
                            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                            startActivity(intent); // Start the WelcomeActivity

                            // Optionally, finish the current activity so the user can't go back to it
                            finish();
                        } else {
                            // Increment attempts on failed login
                            currentAttempts++;
                            editor.putInt(KEY_ATTEMPTS, currentAttempts); // Save new attempt count
                            editor.putLong(KEY_TIMESTAMP, System.currentTimeMillis()); // Update timestamp
                            editor.apply();

                            // Update the TextView with the remaining attempts
                            int remainingAttempts = MAX_ATTEMPTS - currentAttempts;
                            attemptsTextView.setText("Attempts remaining: " + remainingAttempts);

                            Toast.makeText(MainActivity.this, "Invalid credentials, try again. Attempts left: " + remainingAttempts, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
