package com.joseph.cantv.practicacursoudemy;

import android.content.Intent;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textViewMain);
        btnNext = (Button) findViewById(R.id.buttonGoSharing);

        //Tomar los datos del intent

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null && bundle.getString("greeter") !=null){
          String greeter = bundle.getString("greeter");
            Toast.makeText(SecondActivity.this, greeter, Toast.LENGTH_SHORT).show();
            textView.setText(greeter);
        } else {
            Toast.makeText(SecondActivity.this, "It is empty", Toast.LENGTH_SHORT).show();
        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
