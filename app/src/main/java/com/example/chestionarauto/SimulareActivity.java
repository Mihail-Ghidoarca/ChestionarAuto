package com.example.chestionarauto;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chestionarauto.data.IntrebariProvider;
import com.example.chestionarauto.model.Intrebare;
import java.util.Collections;
import java.util.List;

public class SimulareActivity extends AppCompatActivity {

    private List<Intrebare> intrebari;
    private int index = 0;
    private int scor = 0;

    private TextView intrebareText, timerText;
    private RadioGroup optiuniGroup;
    private Button urmatoarea;
    private CountDownTimer timer;

    private static final long DURATA_EXAMEN_MS = 15 * 60 * 1000; // 15 minute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulare);

        intrebareText = findViewById(R.id.intrebareTextSimulare);
        timerText = findViewById(R.id.timerText);
        optiuniGroup = findViewById(R.id.optiuniGroupSimulare);
        urmatoarea = findViewById(R.id.btnUrmatoareaSimulare);

        intrebari = IntrebariProvider.incarcaIntrebari(this);
        Collections.shuffle(intrebari);
        intrebari = intrebari.subList(0, Math.min(26, intrebari.size())); // 26 întrebări max

        afiseazaIntrebare();

        urmatoarea.setOnClickListener(v -> {
            int checkedId = optiuniGroup.getCheckedRadioButtonId();
            if (checkedId != -1) {
                int selectedIndex = optiuniGroup.indexOfChild(findViewById(checkedId));
                if (selectedIndex == intrebari.get(index).getRaspunsCorect()) {
                    scor++;
                }
                index++;
                if (index < intrebari.size()) {
                    afiseazaIntrebare();
                } else {
                    finalizeazaTest();
                }
            }
        });

        timer = new CountDownTimer(DURATA_EXAMEN_MS, 1000) {
            public void onTick(long millisUntilFinished) {
                long sec = millisUntilFinished / 1000;
                timerText.setText(String.format("Timp rămas: %02d:%02d", sec / 60, sec % 60));
            }

            public void onFinish() {
                finalizeazaTest();
            }
        }.start();
    }

    private void afiseazaIntrebare() {
        Intrebare i = intrebari.get(index);
        intrebareText.setText(i.getIntrebare());
        optiuniGroup.removeAllViews();

        for (String opt : i.getOptiuni()) {
            RadioButton rb = new RadioButton(this);
            rb.setText(opt);
            optiuniGroup.addView(rb);
        }
    }

    private void finalizeazaTest() {
        timer.cancel();
        Intent intent = new Intent(this, RezultatActivity.class);
        intent.putExtra("punctaj", scor);
        intent.putExtra("total", intrebari.size());
        startActivity(intent);
        finish();
    }
}
