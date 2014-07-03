package scom.game.tiltiland;

import android.app.Activity;
import android.os.Bundle;

import com.game.tiltiland.R;
import com.google.android.gms.ads.*;

public class Ad extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tiltiland);

    // Look up the AdView as a resource and load a request.
    AdView adView = (AdView)this.findViewById(R.id.adView);
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);
  }
}