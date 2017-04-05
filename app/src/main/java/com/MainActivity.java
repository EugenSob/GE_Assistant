package com;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
	
	private CardScrollView mCardScroller;
	private CardAdapter mCardAdapter;
	
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        mCardAdapter = new CardAdapter(createCards(this));
        
        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(mCardAdapter);
        
        setContentView(mCardScroller);
    }

   
    private ArrayList<CardBuilder> createCards(Context context) {

        CardBuilder card1 = new CardBuilder(context, CardBuilder.Layout.TEXT);
        card1.setText("First Card");

        CardBuilder card2 = new CardBuilder(context, CardBuilder.Layout.TEXT);
        card2.setText("Second Card");

        CardBuilder card3 = new CardBuilder(context, CardBuilder.Layout.TEXT);
        card3.setText("Third Card");
        
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


//	@Override
//	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		// TODO Auto-generated method stub
//		
//	}
    
    

    
    
}

