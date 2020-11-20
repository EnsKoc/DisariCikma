package com.example.outorin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText yas;
    CheckBox evet,hayir;
    Button hesapla;
    String kullaniciYasiStr;
    String cikmaDurumu;
    int kullaniciYasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yas = (EditText)findViewById(R.id.yas);
        evet = (CheckBox)findViewById(R.id.evet);
        hayir = (CheckBox)findViewById(R.id.hayir);
        hesapla = (Button)findViewById(R.id.btn);

        evet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    hayir.setChecked(false);
                }
            }
        });
        hayir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    evet.setChecked(false);
                }
            }
        });

        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kullaniciYasiStr = yas.getText().toString();
                kullaniciYasi = Integer.valueOf(kullaniciYasiStr);

                Date simdikiZaman = new Date();
                DateFormat df = new SimpleDateFormat("E HH.mm");
                String mevcutTarih=df.format(simdikiZaman);
                //System.out.println(mevcutTarih);

                String[] array= mevcutTarih.split(" ");
                double saat = Double.parseDouble(array[1]);

                //System.out.println(saat);

                //20 Yaş Altı
                if(kullaniciYasi<=20){
                    if(saat>13.00 && saat<16.00)
                    {
                        cikmaDurumu="Çıkabilirsin.";
                    }
                    else if((saat<13.00 || saat>16.00) && evet.isChecked())
                    {
                        cikmaDurumu="Şuan Yasak Var!";
                    }
                    else{
                        cikmaDurumu="Şuan Yasak Var!";
                    }
                }
                //65 Yaş Üstü
                else if(kullaniciYasi>=65){
                    if(saat>10.00 && saat<13.00)
                    {
                        cikmaDurumu="Çıkabilirsin.";
                    }
                    else{
                        cikmaDurumu="Şuan Yasak Var!";
                    }
                }
                //20-65 Yaş Arası
                else{
                    if ((array[0].equals("Sat")) || (array[0].equals("Sun")) || (array[0].equals("Cmt")) || (array[0].equals("Paz"))){
                        if (saat>10.00 && saat<20.00){
                            cikmaDurumu="olumlu";
                        }
                        else if((saat<10.00 || saat>20.00) && evet.isChecked())
                        {
                            cikmaDurumu="Çıkabilirsin.";
                        }
                        else {
                            cikmaDurumu="Şuan Yasak Var!";
                        }
                    }
                    else {
                        cikmaDurumu="Çıkabilirsin.";
                    }
                }
                Intent i= new Intent(getApplicationContext(), FinalActivity.class);
                i.putExtra("cikma",cikmaDurumu);
                startActivity(i);
            }
        });
    }
}