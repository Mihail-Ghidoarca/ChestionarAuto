package com.example.chestionarauto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ChestionareActivity extends AppCompatActivity {

    String[] sectiuni = {"Reguli Generale", "Semne de circulație"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chestionare);

        ListView listaSectiuni = findViewById(R.id.listaSectiuni);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sectiuni);
        listaSectiuni.setAdapter(adapter);

        listaSectiuni.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, IntrebariActivity.class);
            intent.putExtra("sectiune", sectiuni[i]);
            startActivity(intent);
        });
    }
}
