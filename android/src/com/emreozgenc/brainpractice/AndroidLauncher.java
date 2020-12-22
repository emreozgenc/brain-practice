package com.emreozgenc.brainpractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.emreozgenc.brainpractice.BrainPractice;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication implements AdController {

    private static final String bannerID = "ca-app-pub-3940256099942544/6300978111";
    private AdView adView;
    private static final int SHOW_AD = 1;
    private static final int HIDE_AD = 0;

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_AD:
                    adView.setVisibility(View.VISIBLE);
                    break;
                case HIDE_AD:
                    adView.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;

        RelativeLayout relativeLayout = new RelativeLayout(this);
        View gameView = initializeForView(new BrainPractice(this), config);
        relativeLayout.addView(gameView);

        adView = new AdView(this);
        adView.setAdUnitId(bannerID);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setBackgroundColor(android.graphics.Color.TRANSPARENT);

        AdRequest.Builder requestBuilder = new AdRequest.Builder();
        requestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        adView.loadAd(requestBuilder.build());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        relativeLayout.addView(adView, params);

        adView.setVisibility(View.GONE);


        setContentView(relativeLayout);
    }

    @Override
    public void showAd() {
        mHandler.sendEmptyMessage(SHOW_AD);
    }
}
