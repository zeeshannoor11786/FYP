package com.example.fyp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.net.URL;

public class activty_cardLayout extends AppCompatActivity implements View.OnClickListener {
    private CardView sofaCard, blackbedcard, moonchairCard, deskCard, bedCard, cupboardCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activty_card_layout);

        sofaCard = (CardView) findViewById(R.id.sofaCardView);
        blackbedcard = (CardView) findViewById(R.id.blackbedCardView);
        moonchairCard = (CardView) findViewById(R.id.moonchairCardView);
        deskCard = (CardView) findViewById(R.id.deskCardView);
        bedCard = (CardView) findViewById(R.id.bedCardView);
        cupboardCard = (CardView) findViewById(R.id.cupboardCardView);

        //setting click listners
        bedCard.setOnClickListener(this);
        sofaCard.setOnClickListener(this);
        moonchairCard.setOnClickListener(this);
        blackbedcard.setOnClickListener(this);
        deskCard.setOnClickListener(this);
        cupboardCard.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.visitSite)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")));
            //Uri.parse("https://www.facebook.com");

        }

        if(item.getItemId()==R.id.about)
        {
            startActivity(new Intent(activty_cardLayout.this, activity_about.class));
            return true;
//            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://zeeshannoor.wordpress.com")));
           // Uri.parse("https://zeeshannoor.wordpress.com");
        }
        if(item.getItemId()==R.id.credit)
        {
            startActivity(new Intent(activty_cardLayout.this, activity_credit.class));
        }
        if(item.getItemId()==R.id.exit)
        {
            finish();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            //Main activity considered as bed activity
            case R.id.bedCardView:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.moonchairCardView:
                i = new Intent(this, activity_chair.class);
                startActivity(i);
                break;
            case R.id.sofaCardView:
                i = new Intent(this, activity_sofa.class);
                startActivity(i);
                break;
            case R.id.cupboardCardView:
                i = new Intent(this, activity_cupboard.class);
                startActivity(i);
                break;
            case R.id.deskCardView:
                i = new Intent(this, activity_desk.class);
                startActivity(i);
                break;
            case R.id.blackbedCardView:
                i = new Intent(this, activity_blackbed.class);
                startActivity(i);
                break;


        }
    }
}
