/**
 *
 * Copyright (c) 2009-2011 Envision Mobile Ltd. All rights reserved.
 *
 * Other software and company names mentioned herein or used for developing
 * and/or running the Envision Mobile Ltd's software may be trademarks or trade
 * names of their respective owners.
 *
 * Everything in the source code herein is owned by Envision Mobile Ltd.
 * The recipient of this source code hereby acknowledges and agrees that such
 * information is proprietary to Envision Mobile Ltd. and shall not be used, 
 * disclosed, duplicated, and/or reversed engineered except in accordance
 * with the express written authorization of Envision Mobile Ltd.
 *
 * Module: Filter.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   10107896
 * Date:        2012-01-28
 * Version:
 *
 *
 */
package com.pixem.core.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.photoedit.pixem.R;
import com.pixem.core.PictureSession;
import com.pixem.effects.EffectFactory;
import com.pixem.utility.EffectListener;

/**
 * @author 10107896
 *
 */
public class Filter extends Activity {

	private LinearLayout layoutEffects = null;
	private EffectFactory effectFactory;
	private PictureSession session;
	private ImageView img;
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.filter_screen);
		
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		layoutEffects = (LinearLayout) findViewById(R.id.layoutEffects);
		effectFactory = new EffectFactory();
		img = (ImageView) findViewById(R.id.imgMainImage);
		
	}
	
	@Override
	public void onResume() { 
		super.onResume();
		
		session = new PictureSession(BitmapFactory.decodeResource(this.getResources(), R.drawable.img_girl), img);
		session.draw();
		
		addFilterBorderButton();

	}

	private void addFilterBorderButton() { 
		
		if (layoutEffects != null && layoutEffects.getChildCount() != 0) { 
			layoutEffects.removeAllViews();
		}
		
		Button btnFilter = new Button(this);
		btnFilter.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.5f));
		btnFilter.setText(getString(R.string.filters));
		btnFilter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showFilters();
			}
		});
		
		Button btnBorder = new Button(this);
		btnBorder.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.5f));
		btnBorder.setText(getString(R.string.borders));
		btnBorder.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showBorders();
			}
		});
		
		layoutEffects.addView(btnFilter);
		layoutEffects.addView(btnBorder);
	}
	
	private void showFilters() { 
		layoutEffects.removeAllViews();
		
		Button btnBack = new Button(this);
		btnBack.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btnBack.setText(getString(R.string.back));
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addFilterBorderButton();
			}
		});
		
		layoutEffects.addView(btnBack);
		layoutEffects.addView(getFilterEffects());
	}
	
	private void showBorders() { 
		layoutEffects.removeAllViews();
		
		Button btnBack = new Button(this);
		btnBack.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btnBack.setText(getString(R.string.back));
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addFilterBorderButton();
			}
		});
		
		layoutEffects.addView(btnBack);
		layoutEffects.addView(getBorderEffects ());
	}
	
	private HorizontalScrollView getFilterEffects() { 
		HorizontalScrollView scrollView = new HorizontalScrollView(this);
		scrollView.setHorizontalScrollBarEnabled(false);

		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		int counter = 0;
		
		for (Drawable d : getFilterImages()) { 
			ImageView img = new ImageView(this);
			img.setPadding(5, 0, 5, 0);
			img.setImageDrawable(d);
			img.setOnClickListener(new EffectListener(effectFactory.getEffect(counter++), session));
			
			layout.addView(img);
		}
		
		scrollView.addView(layout);
		return scrollView;
	}
	
	private HorizontalScrollView getBorderEffects() { 
		HorizontalScrollView scrollView = new HorizontalScrollView(this);
		scrollView.setHorizontalScrollBarEnabled(false);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	
		for (Drawable d : getBorderImages()) { 
			ImageView img = new ImageView(this);
			img.setPadding(5, 0, 5, 0);
			img.setImageDrawable(d);
			
			layout.addView(img);
		}
		
		scrollView.addView(layout);
		return scrollView;
	}	
	
	private ArrayList<Drawable> getFilterImages() { 
		
		ArrayList<Drawable> images  = new ArrayList<Drawable>();

		for (Integer i : effectFactory.getEffectIcons()) { 
			images.add(getApplicationContext().getResources().getDrawable(i));
		}
		
		return images;
	}
	
	private ArrayList<Drawable> getBorderImages() { 
		
		ArrayList<Drawable> images  = new ArrayList<Drawable>();

		for (Integer i : effectFactory.getEffectIcons()) { 
			images.add(getApplicationContext().getResources().getDrawable(i));
		}
		
		return images;
	}
	
	@Override
	public void onPause() { 
		super.onPause();
		
		session = null;
	}
}
