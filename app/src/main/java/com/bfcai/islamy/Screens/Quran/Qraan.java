package com.bfcai.islamy.Screens.Quran;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bfcai.islamy.R;

public class Qraan extends AppCompatActivity {
    ListView pdflistview;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qraan);
        pdflistview=findViewById(R.id.surah_name);
        toolbar = findViewById(R.id.quranToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("القران");
        String[]pdfFilenames={"Faitha","Al-Baqarah","Al Imran","An-Nisa","Al-Ma'idah","Al-An'am","Al-A'raf",
                "Al-Anfal","At-Tawbah","Yunus","Hud","Yusuf","Ar-Ra'd","Ibrahim","Al-Hijr","An-Nahl","Al-Isra",
                "Al-Kahf","Maryam","Ta-Ha"
               ,"Al-Anbiya","Al-Hajj","Al-Mu'minun","An-Nur","Al-Furqan","Ash-Shu'ara","An-Naml","Al-Qasas",
                "Al-Ankabut","Ar-Rum","Luqmaan","As-Sajda","Al-Ahzaab","Saba","Faatir","Ya-Sin","As-Saaffaat"
                ,"Saad","Az-Zumar","Ghafir","Fussilat","Ash_Shooraa","Az-Zukhruf","Ad-Dukhaan","Al-Jaathiyah",
                "Al-Ahqaaf","Muhammad","Al-Fath","Al-Hujuraat"
                ,"Qaaf","Adh-Dhaariyaat","At-Toor","An-Najm","Al-Qamar","Ar-Rahman","Al-Waqi'a",
                "Al-Hadeed","Al-Mojaadala","Al-Hashr","Al-Mumtahanah","As-Saff","Al-Jumu'ah","Al-Munafiqoon",
                "At-Taghabun","At-Talaq","At-Tahreem","Al-Mulk","Al-Qalam","Al-Haaqqa","Al-Ma'aarij","Nooh"
                ,"Al-Jinn","Al-Muzzammil","Al-Muddaththir","Al-Qiyamah","Al-Insaan","Al-Mursalaat","An-Naba'",
                "An-Naazi'aat","Abasa","At-Takweer","Al-Infitar","Al-Mutaffifeen","Al-Inshiqaaq","Al-Burooj"
                ,"At-Taariq","Al-A'laa","Al-Ghaashiyah","Al-Fajr","Al-Balad","Ash-Shams","Al-Layl","Ad-Dhuha"
                ,"Ash-Sharh","At-Teen","Al-Alaq","Al-Qadr","Al-Bayyinahh","Az-Zalzalah","Al-'Aadiyaat","Al-Qaari'ah"
                ,"At-Takaathur","Al-'Asr","Al-Humazah","Al-Feel","Quraysh","Al-Maa'oon","Al-Kawthar"
                ,"Al-Kaafiroon","An-Nasr","Al-Masad","Al-Ikhlaas","Al-Falaq","An-Naas"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pdfFilenames)
        {
            //Alt + insert   use override method


            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view=super.getView(position, convertView, parent);
                TextView myText=(TextView) view.findViewById(android.R.id.text1);
                return super.getView(position, convertView, parent);

            }
        };
        pdflistview.setAdapter(adapter);
        pdflistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=pdflistview.getItemAtPosition(i).toString();
                Intent intent=new Intent(getApplicationContext(),PdfOpner.class);
                intent.putExtra("item",item);
                intent.putExtra("num",i);

                startActivity(intent);


            }
        });


    }
}