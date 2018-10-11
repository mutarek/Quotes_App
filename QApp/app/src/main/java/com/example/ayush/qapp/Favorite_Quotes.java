package com.example.ayush.qapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import java.util.LinkedList;

public class Favorite_Quotes extends AppCompatActivity {

    public static LinkedList<String> FavoriteQuotes;
    Button mNextButton;
    Button mLastButton;
    ImageButton mShareButton;
    TextView mQuoteTextView;
    ConstraintLayout constraintLayout;
    public static int i = 0;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite__quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNextButton = findViewById(R.id.nextQuoteButoon);
        mQuoteTextView = findViewById(R.id.QuoteTextView);
        mLastButton = findViewById(R.id.lastQuoteButton);
        mShareButton = findViewById(R.id.shareButton);
        constraintLayout = findViewById(R.id.mainView);

       if(!FavoriteQuotes.isEmpty()){
           mQuoteTextView.setText(FavoriteQuotes.get(i));
       }else{
           mNextButton.setVisibility(View.INVISIBLE);
           mShareButton.setVisibility(View.INVISIBLE);
           mLastButton.setVisibility(View.INVISIBLE);
           mQuoteTextView.setText("OPPS!!\n"+"NO QUOTES HAVE BEEN ADDED TO FAVORITES.");
       }

        constraintLayout.setOnTouchListener(new OnSwipeTouchListener(Favorite_Quotes.this){
            public void onSwipeRight() {
                if(!FavoriteQuotes.isEmpty()){
                    if(i>0){
                        i--;
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }else {
                        i = FavoriteQuotes.size()-1;
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }
                }
            }

            public void onSwipeLeft() {
               if(!FavoriteQuotes.isEmpty()){
                   if(i<FavoriteQuotes.size()-1){
                       i++;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }else {
                       i=0;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }
               }
            }

            public void onSwipeTop(){
                if(!FavoriteQuotes.isEmpty()){
                    Snackbar.make(getWindow().getDecorView().getRootView(),"Deleted From Favorites",Snackbar.LENGTH_LONG).show();
                    FavoriteQuotes.remove(mQuoteTextView.getText().toString());
                    if(FavoriteQuotes.isEmpty()){
                        mNextButton.setVisibility(View.INVISIBLE);
                        mShareButton.setVisibility(View.INVISIBLE);
                        mLastButton.setVisibility(View.INVISIBLE);
                        mQuoteTextView.setText("OPPS!!\n"+"NO QUOTES HAVE BEEN ADDED TO FAVORITES.");
                    }else if(!FavoriteQuotes.isEmpty() && i>=FavoriteQuotes.size()){
                        i--;
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }else {
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }
                }
            }
        });

       mQuoteTextView.setOnTouchListener(new OnSwipeTouchListener(Favorite_Quotes.this){
           public void onSwipeRight() {
               if(!FavoriteQuotes.isEmpty()){
                   if(i>0){
                       i--;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }else {
                       i = FavoriteQuotes.size()-1;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }
               }
           }

           public void onSwipeLeft() {
               if(!FavoriteQuotes.isEmpty()){
                   if(i<FavoriteQuotes.size()-1){
                       i++;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }else {
                       i=0;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }
               }
           }

           public void onSwipeTop(){
               if(!FavoriteQuotes.isEmpty()){
                   Snackbar.make(getWindow().getDecorView().getRootView(),"Deleted From Favorites",Snackbar.LENGTH_LONG).show();
                   FavoriteQuotes.remove(mQuoteTextView.getText().toString());
                   if(FavoriteQuotes.isEmpty()){
                       mNextButton.setVisibility(View.INVISIBLE);
                       mShareButton.setVisibility(View.INVISIBLE);
                       mLastButton.setVisibility(View.INVISIBLE);
                       mQuoteTextView.setText("OPPS!!\n"+"NO QUOTES HAVE BEEN ADDED TO FAVORITES.");
                   }else if(!FavoriteQuotes.isEmpty() && i>=FavoriteQuotes.size()){
                       i--;
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }else {
                       mQuoteTextView.setText(FavoriteQuotes.get(i));
                   }
               }
           }
       });

       Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Oswald-Medium.ttf");
       mQuoteTextView.setTypeface(roboto);

       mNextButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(i<FavoriteQuotes.size()-1){
                   i++;
                   mQuoteTextView.setText(FavoriteQuotes.get(i));
               }else {
                   i=0;
                   mQuoteTextView.setText(FavoriteQuotes.get(i));
               }
           }
       });

       mLastButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(i>0){
                   i--;
                   mQuoteTextView.setText(FavoriteQuotes.get(i));
               }else {
                   i = FavoriteQuotes.size()-1;
                   mQuoteTextView.setText(FavoriteQuotes.get(i));
               }
           }
       });

       mShareButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ShareCompat.IntentBuilder
                       .from(Favorite_Quotes.this)
                       .setType("text/plain")
                       .setText(mQuoteTextView.getText().toString())
                       .setChooserTitle("Share this Quote with")
                       .startChooser();
           }
       });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!FavoriteQuotes.isEmpty()){
                    Snackbar.make(view, "Deleted From Favorites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    FavoriteQuotes.remove(mQuoteTextView.getText().toString());
                    if(FavoriteQuotes.isEmpty()){
                        mNextButton.setVisibility(View.INVISIBLE);
                        mShareButton.setVisibility(View.INVISIBLE);
                        mLastButton.setVisibility(View.INVISIBLE);
                        mQuoteTextView.setText("OPPS!!\n"+"NO QUOTES HAVE BEEN ADDED TO FAVORITES.");
                    }else if(!FavoriteQuotes.isEmpty() && i>=FavoriteQuotes.size()){
                        i--;
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }else {
                        mQuoteTextView.setText(FavoriteQuotes.get(i));
                    }
                }else {
                    Snackbar.make(view, "No Quotes to Delete", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            return true;
        }

        if (id == R.id.action_aboutUs) {
            startActivity(new Intent(this, About_US.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


