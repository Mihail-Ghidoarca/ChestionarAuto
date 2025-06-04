package com.example.chestionarauto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RezultatActivity extends AppCompatActivity {

    private TextView scorFinal;
    private Button inapoiMeniu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultat);

        scorFinal = findViewById(R.id.scorFinal);
        inapoiMeniu = findViewById(R.id.btnInapoiMeniu);

        int punctaj = getIntent().getIntExtra("punctaj", 0);
        int total = getIntent().getIntExtra("total", 0);

        scorFinal.setText("Scor final: " + punctaj + " / " + total);

        inapoiMeniu.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}
