package com.example.curricularica;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    EditText apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myToolbar);

        apiKey = findViewById(R.id.user_api);

        SharedPreferences sharedPreferences = getSharedPreferences("APIKeyData", MODE_PRIVATE);
        apiKey.setText(sharedPreferences.getString("apiKey", ""));

//        findViewById(R.id.button_import).setOnClickListener(v -> {
//
//        });

        findViewById(R.id.button_create).setOnClickListener(v -> {

        });

//        findViewById(R.id.button_switch).setOnClickListener(v -> {
//
//        });

    }

    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Use SharedPreferences to preserve input data in time
        SharedPreferences sharedPreferences = getSharedPreferences("APIKeyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the input data to SharedPreferences
        editor.putString("apiKey", apiKey.getText().toString());

        editor.apply();
    }

}
