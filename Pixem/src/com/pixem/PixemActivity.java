package com.pixem;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.photoedit.pixem.R;
import com.pixem.borders.Border;
import com.pixem.borders.RoundedBorder;
import com.pixem.borders.StraightBorder;
import com.pixem.effects.BlackAndWhite;
import com.pixem.effects.Contrast;
import com.pixem.effects.Sepia;
import com.pixem.effects.Smooth;
import com.pixem.utility.ColourUtil;

public class PixemActivity extends Activity {
	private Button btnGreyScale, btnSepia, btnContrastBrighter,
			btnContrastDimmer, btnSmooth, btnRectangleBorder, btnRoundedBorder, btnColourFilter, btnDuplicate;
	private ImageView img;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		img = (ImageView) findViewById(R.id.imageChosen);
		img.setDrawingCacheEnabled(true);

		btnGreyScale = (Button) findViewById(R.id.btnGreyScale);
		btnSepia = (Button) findViewById(R.id.btnSepia);
		btnContrastBrighter = (Button) findViewById(R.id.btnContrastBrighter);
		btnContrastDimmer = (Button) findViewById(R.id.btnContrastDimmer);
		btnSmooth = (Button) findViewById(R.id.btnSmooth);
		btnRectangleBorder = (Button) findViewById(R.id.btnRectangleBorder);
		btnRoundedBorder = (Button) findViewById(R.id.btnRoundedBorder);
		btnColourFilter = (Button) findViewById (R.id.btnColourFilter);
		btnDuplicate = (Button) findViewById (R.id.btnDuplicate);
	}

	@Override
	public void onResume() {
		super.onResume();

		setButtonListeners();

	}

	public void setButtonListeners() {
		btnGreyScale.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				img.setImageBitmap(new BlackAndWhite().applyEffect(img.getDrawingCache()));

			}
		});

		btnSepia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				img.setImageBitmap(new Sepia().applyEffect(img.getDrawingCache()));
			}
		});

		btnContrastBrighter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Contrast contrast = new Contrast();
				contrast.setContrast(75);

				img.setImageBitmap(contrast.applyEffect(img.getDrawingCache()));
			}
		});

		btnContrastDimmer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				img.setImageBitmap(new Contrast().applyEffect(img.getDrawingCache()));
			}
		});

		btnSmooth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				img.setImageBitmap(new Smooth().applyEffect(img.getDrawingCache()));
			}
		});

		btnRectangleBorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				StraightBorder border = new StraightBorder(Color.RED);
				
				img.setImageBitmap(border.generateBorder(img.getDrawingCache().getWidth(),
						img.getDrawingCache().getHeight()));
			}
		});

		btnRoundedBorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Border roundedBorder = new RoundedBorder(Color.RED, 25, 25);

				img.setImageBitmap(roundedBorder.generateBorder(25, 25));
			}
		});
		
		btnColourFilter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Effect ColourFilter = new ColourFilter(Color.RED);
				
				img.setImageBitmap(ColourUtil.switchBlueGreen(img.getDrawingCache()));
			}
		});
		
		btnDuplicate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();

		System.gc();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		btnGreyScale = null;
		btnSepia = null;
		btnContrastBrighter = null;
		btnContrastDimmer = null;
		btnSmooth = null;
		btnRectangleBorder = null;
		btnRoundedBorder = null;

		System.gc();
	}
}