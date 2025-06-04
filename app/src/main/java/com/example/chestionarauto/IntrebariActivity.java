package com.example.chestionarauto;
import com.example.chestionarauto.R;
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
            int checkedId = optiuniGroup.getCheckedRadioButtonId();
            if (checkedId != -1) {
                int selectedIndex = optiuniGroup.indexOfChild(findViewById(checkedId));
                if (selectedIndex == intrebari.get(index).getRaspunsCorect()) {
                    scor++;
                }
                explicatieText.setText(intrebari.get(index).getExplicatie());
                index++;
                if (index < intrebari.size()) {
                    afiseazaIntrebare();
                } else {
                    urmatoarea.setEnabled(false);
                    intrebareText.setText("Scor final: " + scor + "/" + intrebari.size());
                }
            }
        });
    }

    private void afiseazaIntrebare() {
        Intrebare i = intrebari.get(index);
        intrebareText.setText(i.getIntrebare());
        explicatieText.setText("");
        optiuniGroup.removeAllViews();

        for (String opt : i.getOptiuni()) {
            RadioButton rb = new RadioButton(this);
            rb.setText(opt);
            optiuniGroup.addView(rb);
        }
    }
}
