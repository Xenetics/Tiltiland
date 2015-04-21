package scom.game.tiltiland;

import android.content.Context;
import android.os.PowerManager;

import com.badlogic.androidgames.framework.impl.AndroidGame;
import com.badlogic.androidgames.framework.Screen;

public class Tiltiland extends AndroidGame
{
	public Screen getStartScreen()
	{
		MakePowMan();
		return new LoadingScreen(this, this);
	}
	
	public void MakePowMan() 
	{
		  Assets.PowMan = (PowerManager) getSystemService(Context.POWER_SERVICE);
	}
	
	/*
	 private InterstitialAd interstitial;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_tiltiland);

	    // Create the interstitial.
	    interstitial = new InterstitialAd(this);
	    interstitial.setAdUnitId(getResources().getString(R.string.ad_unit_id));

	    // Create ad request.
	    AdRequest adRequest = new AdRequest.Builder().build();

	    // Begin loading your interstitial.
	    interstitial.loadAd(adRequest);

	  }

	  // Invoke displayInterstitial() when you are ready to display an interstitial.
	  public void displayInterstitial() {
	    if (interstitial.isLoaded()) {
	      interstitial.show();
	    }
	  }
	 */	  
	
	/*
	 private AdView mAdView;  
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiltiland);

        mAdView = new AdView(this);
        mAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdListener(new ToastAdListener(this));
        LinearLayout layout = (LinearLayout) findViewById(R.id.adView);
        
        layout.addView(mAdView);
        mAdView.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onPause() 
    {
        mAdView.pause();
        super.onPause();
    }

    @Override
    public void onResume() 
    {
        super.onResume();
        mAdView.resume();
    }

    @Override
    public void onDestroy() 
    {
        mAdView.destroy();
        super.onDestroy();
    }
	*/

}
