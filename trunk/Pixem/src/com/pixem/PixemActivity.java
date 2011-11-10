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
import com.pixem.borders.StraightBorder;
import com.pixem.effects.BlackAndWhite;
import com.pixem.effects.Contrast;
import com.pixem.effects.Sepia;
import com.pixem.effects.Smooth;

public class PixemActivity extends Activity {
	private Button btnGreyScale, btnSepia, btnContrastBrighter, btnContrastDimmer, btnSmooth, btnRectangleBorder, btnRoundedBorder;
	private ImageView img;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        img = (ImageView) findViewById (R.id.imageChosen);
        img.setDrawingCacheEnabled(true);
        
        btnGreyScale = (Button) findViewById(R.id.btnGreyScale);
        btnSepia = (Button) findViewById (R.id.btnSepia);
        btnContrastBrighter = (Button) findViewById (R.id.btnContrastBrighter);
        btnContrastDimmer = (Button) findViewById (R.id.btnContrastDimmer);
        btnSmooth = (Button) findViewById (R.id.btnSmooth);
        btnRectangleBorder = (Button) findViewById (R.id.btnRectangleBorder);
        btnRoundedBorder = (Button) findViewById (R.id.btnRoundedBorder);
    }
    
    
    @Override
    public void onResume() { 
    	super.onResume();
    	
    	setButtonListeners();

    }
    
    public void setButtonListeners() { 
    	final Contrast contrast = new Contrast();
    	
    	btnGreyScale.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				BlackAndWhite greyScale = new BlackAndWhite();
				
				if (greyScale.applyEffect(img.getDrawingCache()) != null) { 
					img.setImageBitmap(greyScale.applyEffect(img.getDrawingCache()));
				}
				
			}
		});
    	
    	btnSepia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Sepia sepia = new Sepia ();
				
				if (sepia.applyEffect(img.getDrawingCache()) != null) { 
					img.setImageBitmap(sepia.applyEffect(img.getDrawingCache()));
				}
			}
		});
    	
    	btnContrastBrighter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				contrast.setBitmap(img.getDrawingCache());
				contrast.setContrast(75);
				contrast.setLowBrightContrast(false);
				
				if (contrast.applyEffect(img.getDrawingCache()) != null) { 
					img.setImageBitmap(contrast.applyEffect(img.getDrawingCache()));
				}
			}
		});
		
		btnContrastDimmer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				contrast.setBitmap(img.getDrawingCache());
				contrast.setContrast(15);
				contrast.setLowBrightContrast(false);
				
				if (contrast.applyEffect(img.getDrawingCache()) != null) { 
					img.setImageBitmap(contrast.applyEffect(img.getDrawingCache()));
				}
			}
		});
		
		btnSmooth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Smooth smooth = new Smooth();
				
				if (smooth.applyEffect(img.getDrawingCache()) != null) { 
					img.setImageBitmap(smooth.applyEffect(img.getDrawingCache()));
				}
			}
		});
		
		btnRectangleBorder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				StraightBorder border = new StraightBorder(Color.RED, 25, img.getDrawingCache());
				if (border.generateBorder(img.getDrawingCache()) != null) { 
					img.setImageBitmap(border.generateBorder(img.getDrawingCache()));
				}
			}
		});
		
		btnRoundedBorder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    @Override
    public void onLowMemory() { 
    	super.onLowMemory();
    	
    	System.gc();
    }
}