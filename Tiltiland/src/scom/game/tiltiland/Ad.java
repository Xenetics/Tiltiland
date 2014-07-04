package scom.game.tiltiland;

import com.game.tiltiland.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

/**
 * Example of including a Google banner ad in code and listening for ad events.
 */
public class Ad extends Activity 
{
    private AdView mAdView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiltiland);
        

        mAdView = new AdView(this);
        mAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdListener(new ToastAdListener(this));
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.adView);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(mAdView, params);
        mAdView.loadAd(new AdRequest.Builder().build());
    }

    @Override
    protected void onPause() 
    {
        mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() 
    {
        super.onResume();
        mAdView.resume();
    }

    @Override
    protected void onDestroy() 
    {
        mAdView.destroy();
        super.onDestroy();
    }
}
