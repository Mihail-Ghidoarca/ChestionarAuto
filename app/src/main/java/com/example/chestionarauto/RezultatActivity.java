package com.example.chestionarauto;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RezultatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultat);

        int punctaj = getIntent().getIntExtra("punctaj", 0);
        int total = getIntent().getIntExtra("total", 0);

        TextView scorText = findViewById(R.id.scorText);
        scorText.setText("Ai obținut " + punctaj + " puncte din " + total + ".");
    }
}
