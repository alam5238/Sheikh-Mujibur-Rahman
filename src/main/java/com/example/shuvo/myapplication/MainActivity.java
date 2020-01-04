package com.example.shuvo.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
       //     public void onClick(View view) {
       //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
       //                 .setAction("Action", null).show();
       //     }
       // });

        if(!isNetworkAvail()){
            AlertDialog.Builder Checkbuilder = new  AlertDialog.Builder(MainActivity.this);
            Checkbuilder.setIcon(R.drawable.error);
            Checkbuilder.setTitle("Error!!");
            Checkbuilder.setMessage("Check your Internet Connection.");

            Checkbuilder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Restart the activity
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });

            Checkbuilder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            Checkbuilder.setCancelable(false);

            AlertDialog alert = Checkbuilder.create();
            alert.show();

        }else{

            if(isNetworkAvail()){


            }
        }

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl("file:///android_asset/www/index.html");



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(event.getAction()==KeyEvent.ACTION_DOWN){

            switch (keyCode){
                case KeyEvent.KEYCODE_BACK:
                    if(webView.canGoBack()){
                        webView.goBack();
                    }else{

                        finish();
                    }
                    return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_About) {

            Intent i = new Intent(getApplicationContext(),About.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_Share) {

            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String shareBody = "Apps link here";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
            startActivity(Intent.createChooser(myIntent, "Share App Download link via"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvail(){
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activityNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activityNetworkInfo !=null;

    }

}
