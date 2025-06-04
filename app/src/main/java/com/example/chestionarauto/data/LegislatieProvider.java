package com.example.chestionarauto.data;

import android.content.Context;
import com.example.chestionarauto.R;  // nu uita să imporți R!
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LegislatieProvider {
    public static String getLegislatie(Context context, String sectiune) {
        try {
            int resId = 0;
            if (sectiune.equals("Reguli Generale")) resId = R.raw.reguli_generale;
            else if (sectiune.equals("Semne de circulație")) resId = R.raw.semne;

            InputStream is = context.getResources().openRawResource(resId);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "Eroare la încărcarea legislației.";
        }
    }
}
