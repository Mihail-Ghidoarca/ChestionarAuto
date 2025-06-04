package com.example.chestionarauto;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chestionarauto.data.IntrebariProvider;
import com.example.chestionarauto.model.Intrebare;
import java.util.Collections;
import java.util.List;

public class IntrebariActivity extends AppCompatActivity {

    private List<Intrebare> intrebari;
    private int index = 0;
    private int scor = 0;
    private boolean raspunsVerificat = false;

    private TextView intrebareText, explicatieText;
    private RadioGroup optiuniGroup;
    private Button urmatoarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrebari);

        String sectiune = getIntent().getStringExtra("sectiune");

        intrebari = IntrebariProvider.filtreazaDupaSectiune(
                IntrebariProvider.incarcaIntrebari(this), sectiune);
        Collections.shuffle(intrebari);

        if (intrebari == null || intrebari.isEmpty()) {
            Toast.makeText(this, "Nu s-au găsit întrebări pentru secțiunea: " + sectiune, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        intrebareText = findViewById(R.id.intrebareText);
        explicatieText = findViewById(R.id.explicatieText);
        optiuniGroup = findViewById(R.id.optiuniGroup);
        urmatoarea = findViewById(R.id.btnUrmatoarea);

        afiseazaIntrebare();

        urmatoarea.setOnClickListener(v -> {
            if (!raspunsVerificat) {
                verificaRaspuns();
            } else {
                index++;
                if (index < intrebari.size()) {
                    afiseazaIntrebare();
                    raspunsVerificat = false;
                    urmatoarea.setText("Trimite răspuns");
                } else {
                    intrebareText.setText("Scor final: " + scor + "/" + intrebari.size());
                    explicatieText.setText("");
                    optiuniGroup.setVisibility(View.GONE);
                    urmatoarea.setText("Înapoi la meniu");

                    // 👉 Butonul te duce înapoi la MainActivity
                    urmatoarea.setOnClickListener(view -> {
                        finish(); // închide activitatea și revine la meniu
                    });
                }
            }
        });

    }

    private void afiseazaIntrebare() {
        Intrebare i = intrebari.get(index);
        intrebareText.setText(i.getIntrebare());
        explicatieText.setText("");
        explicatieText.setVisibility(View.GONE);
        optiuniGroup.removeAllViews();

        for (String opt : i.getOptiuni()) {
            RadioButton rb = new RadioButton(this);
            rb.setText(opt);
            rb.setTextColor(Color.BLACK);
            rb.setEnabled(true);
            optiuniGroup.addView(rb);
        }
    }

    private void verificaRaspuns() {
        int checkedId = optiuniGroup.getCheckedRadioButtonId();
        if (checkedId == -1) return;

        RadioButton selectat = findViewById(checkedId);
        int indexSelectat = optiuniGroup.indexOfChild(selectat);
        int indexCorect = intrebari.get(index).getRaspunsCorect();

        for (int i = 0; i < optiuniGroup.getChildCount(); i++) {
            RadioButton rb = (RadioButton) optiuniGroup.getChildAt(i);
            rb.setEnabled(false);
            if (i == indexCorect) {
                rb.setTextColor(Color.GREEN);
            } else if (i == indexSelectat && indexSelectat != indexCorect) {
                rb.setTextColor(Color.RED);
            } else {
                rb.setTextColor(Color.BLACK);
            }
        }

        if (indexSelectat == indexCorect) {
            scor++;
        }

        explicatieText.setText(intrebari.get(index).getExplicatie());
        explicatieText.setVisibility(View.VISIBLE);

        urmatoarea.setText("Următoarea întrebare");
        raspunsVerificat = true;
    }
}
