package com.example.chestionarauto;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chestionarauto.data.LegislatieProvider;

public class LegislatieActivity extends AppCompatActivity {

    String[] sectiuni = {"Reguli Generale", "Semne de circulație"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislatie);

        ListView lista = findViewById(R.id.listaLegislatie);
        TextView textLegislatie = findViewById(R.id.textLegislatie);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sectiuni);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener((adapterView, view, i, l) -> {
            String continut = LegislatieProvider.getLegislatie(this, sectiuni[i]);
            textLegislatie.setText(continut);
        });
    }
}
