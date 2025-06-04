package com.example.chestionarauto.data;
import com.example.chestionarauto.R;
import android.content.Context;
import com.example.chestionarauto.model.Intrebare;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IntrebariProvider {

    public static List<Intrebare> incarcaIntrebari(Context context) {
        List<Intrebare> lista = new ArrayList<>();

        try {
            InputStream is = context.getResources().openRawResource(R.raw.intrebari);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonStr = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String intrebare = obj.getString("intrebare");
                JSONArray optiuniJson = obj.getJSONArray("optiuni");
                List<String> optiuni = new ArrayList<>();
                for (int j = 0; j < optiuniJson.length(); j++) {
                    optiuni.add(optiuniJson.getString(j));
                }
                int raspuns = obj.getInt("raspuns");
                String explicatie = obj.getString("explicatie");
                String sectiune = obj.getString("sectiune");
                lista.add(new Intrebare(intrebare, optiuni, raspuns, explicatie, sectiune));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


    public static List<Intrebare> filtreazaDupaSectiune(List<Intrebare> toate, String sectiune) {
        List<Intrebare> filtrate = new ArrayList<>();
        for (Intrebare i : toate) {
            if (i.getSectiune().equals(sectiune)) {
                filtrate.add(i);
            }
        }
        return filtrate;
    }
}
