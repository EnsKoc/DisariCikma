package com.example.outorin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_activity);

        TextView textView = (TextView)findViewById(R.id.cikmaDurumu);
        Bundle gelenVeri=getIntent().getExtras();
        CharSequence gelenyazi=gelenVeri.getCharSequence("cikma");
        textView.setText(gelenyazi);
    }
}
