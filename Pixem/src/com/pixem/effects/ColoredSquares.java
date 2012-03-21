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
 * Module: ColoredSquares.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   10107896
 * Date:        2012-03-20
 * Version:
 *
 *
 */
package com.pixem.effects;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import com.pixem.utility.ColourUtil;

/**
 * @author 10107896
 *
 */
public class ColoredSquares implements Effect {

	/* (non-Javadoc)
	 * @see com.pixem.effects.Effect#applyEffect(android.graphics.Bitmap)
	 */
	@Override
	public Bitmap applyEffect(Bitmap bm) {
		
		Bitmap b1 = bm.copy(Config.ARGB_8888, true);
		Bitmap b2 = bm.copy(Config.ARGB_8888, true);
		Bitmap b3 = bm.copy(Config.ARGB_8888, true);
		Bitmap b4 = bm.copy(Config.ARGB_8888, true);
		
		Log.d("", "- about to resize images, width/height:"+ b1.getWidth()+"/"+b1.getHeight());
		
		b1 = ColourUtil.getResizedBitmap(b1, b1.getWidth(), b1.getHeight(), 1.0f, 1.0f);
		b2 = ColourUtil.getResizedBitmap(b2, b2.getWidth(), b2.getHeight(), 1.0f, 1.0f);
		b3 = ColourUtil.getResizedBitmap(b3, b3.getWidth(), b3.getHeight(), 0.8f, 0.8f);
		b4 = ColourUtil.getResizedBitmap(b4, b4.getWidth(), b4.getHeight(), 0.8f, 0.8f);

		Log.d("", "- images resized, b1 is: " + b1.getWidth() + " " + b1.getHeight());
		ColourFilter filter = new ColourFilter(Color.argb(255, 250, 0, 0));
		b1 = filter.applyEffect(b1);
		
		filter.setColour(Color.argb(255, 0, 250, 0));
		b2 = filter.applyEffect(b2);
		
		filter.setColour(Color.argb(255, 0, 0, 250));
		b3 = filter.applyEffect(b3);
		
		filter.setColour(Color.argb(255, 70, 30, 70));
		b4 = filter.applyEffect(b4);
		
		// Get your images from their files
		//Bitmap bottomImage = new BitmapFactory.decodeFile("myFirstPNG.png");
		//Bitmap topImage = new BitmapFactor.decodeFile("myOtherPNG.png");

		//http://stackoverflow.com/questions/2738834/combining-two-png-files-in-android
		// As described by Steve Pomeroy in a previous comment, 
		// use the canvas to combine them.
		// Start with the first in the constructor..
		//Canvas comboImage = new Canvas(bottomImage);
		// Then draw the second on top of that
		//comboImage.drawBitmap(topImage, 0f, 0f, null);
		
		Canvas comboImage = new Canvas(b2);
		comboImage.drawBitmap(b1, 0f, 0f, null);

		// bottomImage is now a composite of the two. 

		// To write the file out to the SDCard:
		//OutputStream os = null;
		//try {
		//    os = new FileOutputStream("/sdcard/DCIM/Camera/" + "myNewFileName.png");
		//    image.compress(CompressFormat.PNG, 50, os)
		//} catch(IOException e) {
		 //   e.printStackTrace();
		//}
		
		return b2;
	}

}
