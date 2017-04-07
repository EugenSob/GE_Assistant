package com;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

/**
 * Created by eugenio.soberon on 05/04/2017.
 */

public class ApplianceMenuActivity extends Activity{

    private static final String TAG = MachineListActivity.class.getSimpleName();

    private final Handler mHandler = new Handler();
    /** Audio manager used to play system sound effects. */
    private AudioManager mAudioManager;

    /** Gesture detector used to present the options menu. */
    private GestureDetector mGestureDetector;


    private Context context;
    private CardScrollView mCardScroller;
    private CardAdapter mCardAdapter;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        context = this;
        mCardAdapter = new CardAdapter(createCards(this));

        mCardScroller = new CardScrollView(this);
        mCardScroller.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startSteps();
                    }
                });
            }
        });
        mCardScroller.setAdapter(mCardAdapter);

        setContentView(mCardScroller);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private List<CardBuilder> createCards(Context context) {

        CardBuilder card1 = new CardBuilder(context, CardBuilder.Layout.TITLE);
        card1.setText("Appliance Model 1");

        CardBuilder card2 = new CardBuilder(context, CardBuilder.Layout.TITLE);
        card2.setText("Appliance Model 2");

        CardBuilder card3 = new CardBuilder(context, CardBuilder.Layout.TITLE);
        card3.setText("Appliance Model 3");

        ArrayList<CardBuilder> cards = new ArrayList<CardBuilder>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        return cards;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller.activate();
    }

    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        super.onPause();
    }

    private void startSteps() {
        startActivity(new Intent(this, StepMenuActivity.class));
        finish();
    }
}
