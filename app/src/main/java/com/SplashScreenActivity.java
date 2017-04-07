package com;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

/**
 * Created by eugenio.soberon on 05/04/2017.
 */

public class SplashScreenActivity extends Activity{

    private static final String TAG = MachineListActivity.class.getSimpleName();

    private final Handler mHandler = new Handler();

    private CardScrollView mCardScroller;
    private CardAdapter mCardAdapter;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        mCardAdapter = new CardAdapter(createCards(this));

        mCardScroller = new CardScrollView(this);

        //Este es tap handler, la posicion de las card empiza en 0 y asciende de 1 en 1
        mCardScroller.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                if(mCardScroller.getSelectedItemPosition() == 0) {
                    //El handler activa la visualizacion del menu
                    //o llama a otra activity a traves del activity handler
                    openOptionsMenu();
                }
            }
        });

        mCardScroller.setAdapter(mCardAdapter);

        setContentView(mCardScroller);

    }

    private List<CardBuilder> createCards(Context context) {

        CardBuilder card1 = new CardBuilder(context, CardBuilder.Layout.MENU);
                //Por alguna extraña razon crashea con la siguiente linea
                //card1.addImage(R.drawable.ge_background_title);
                card1.setText("GE Assistant");
                card1.setFootnote("Tap for options");

        ArrayList<CardBuilder> cards = new ArrayList<CardBuilder>();
        cards.add(card1);

        return cards;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.machineList:
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startMachine();
                    }
                });
                return true;
            case R.id.qrCode:
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startQRcode();
                    }
                });
                return true;
            default:
                return false;
        }
    }

    private void startMachine() {
        startActivity(new Intent(this, MachineListActivity.class));
        finish();
    }

    private void startQRcode() {
        startActivity(new Intent(this, QRcodeActivity.class));
        finish();
    }
}
