package ir.technopedia.wordpressjsonclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import ir.technopedia.wordpressjsonclient.util.Util;

public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private InterstitialAd mInterstitialAd;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);


       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
      //  toolbar.setTitle(getString(R.string.app_name));
      //  setSupportActionBar(toolbar);
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                //showInterstitial();
            }

            @Override
            public void onAdClosed() {
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                overridePendingTransition(0,0);
                SplashActivity.this.finish();
            }
        });




        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                //  Intent mainIntent = new Intent(Splash.this,Menu.class);
                //  Splash.this.startActivity(mainIntent);
                // Splash.this.finish();
                showInterstitial();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

            overridePendingTransition(0,0); //0 for no animation
            SplashActivity.this.finish();
        }
    }



}
