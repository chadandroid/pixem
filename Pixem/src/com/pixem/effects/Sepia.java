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
 * Module: Sepia.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   Saman Alvi
 * Date:        2011-11-08
 * Version:
 *
 *
 */
package com.pixem.effects;

import android.graphics.Bitmap;
import android.graphics.Color;


/**
 * @author Saman Alvi
 *
 */
public class Sepia implements Effect {

	
	/* (non-Javadoc)
	 * @see com.android.pixem.org.Effect#applyEffect()
	 */
	@Override
	public Bitmap applyEffect(Bitmap bm) {
		
        int red = 0, green = 0, blue = 0, clr, avg, sepiaIntensity = 15, sepiaDepth = 20;
			
		for (int x = 0; x < bm.getWidth(); x++) { 
			for (int y = 0; y < bm.getHeight(); y++) { 

				clr = bm.getPixel(x, y);
				red = Color.red(clr);
				green = Color.green(clr);
				blue = Color.blue(clr);
				
				avg = (red + green + blue) / 3;
				red = green = blue = avg;
				
				red = red + (sepiaDepth * 2);
				green = green + sepiaDepth;
				blue -= sepiaIntensity;
				
				if (red > 255)
					red = 255;
				
				if (green > 255)
					green = 255;
				
				if (blue < 0) 
					blue = 0;
				
				if (blue > 255)
					blue = 255;
				
				bm.setPixel(x, y, Color.argb(Color.alpha(clr), red, green, blue));
			}
		}
		
		return bm;
	}
}

