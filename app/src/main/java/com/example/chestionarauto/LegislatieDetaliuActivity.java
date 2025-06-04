package com.example.chestionarauto;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chestionarauto.data.LegislatieProvider;

public class LegislatieDetaliuActivity extends AppCompatActivity {

    TextView titluText, continutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislatie_detaliu);

        titluText = findViewById(R.id.titluSectiune);
        continutText = findViewById(R.id.continutSectiune);

        String sectiune = getIntent().getStringExtra("sectiune");
        titluText.setText(sectiune);
        String continut = LegislatieProvider.getLegislatie(this, sectiune);
        continutText.setText(continut);
    }
}
