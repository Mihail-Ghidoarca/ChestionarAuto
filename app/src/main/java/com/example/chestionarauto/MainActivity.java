package com.example.chestionarauto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openChestionare(View view) {
        startActivity(new Intent(this, ChestionareActivity.class));
    }

    public void openSimulare(View view) {
        startActivity(new Intent(this, SimulareActivity.class));
    }

    public void openLegislatie(View view) {
        startActivity(new Intent(this, LegislatieActivity.class));
    }
}
