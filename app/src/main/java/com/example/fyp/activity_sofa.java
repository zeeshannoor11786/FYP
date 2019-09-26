package com.example.fyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class activity_sofa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofa);

        Toast.makeText(this, "No Items in Sofa Cart", Toast.LENGTH_SHORT).show();
    }
}
