package com.example.curricularica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myToolbar);

        findViewById(R.id.button_import).setOnClickListener(v -> {

        });

        findViewById(R.id.button_create).setOnClickListener(v -> {

        });

        findViewById(R.id.button_switch).setOnClickListener(v -> {

        });

    }
}
