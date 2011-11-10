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
 * Module: Contrast.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   10107896
 * Date:        2011-11-08
 * Version:
 *
 *
 */
package com.photoedit.pixem.effects;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.photoedit.pixem.core.Effect;

/**
 * @author 10107896
 *
 */
public class Contrast implements Effect {

	
	private int contrastFactor;
	private boolean isLowLevel = false;
	private Bitmap bm;
	
	public void setContrast(int contrastFactor) { 
		this.contrastFactor = contrastFactor;
	}
	
	public int getContrastFactor () { 
		return contrastFactor;
	}
	
	public void setImage(Bitmap bm) { 
		this.bm = bm;
	}
	
	public void setLowBrightContrast (boolean isLowLevel) { 
		this.isLowLevel = isLowLevel;
	}
	
	/* (non-Javadoc)
	 * @see com.android.pixem.org.Effect#applyEffect()
	 */
	@Override
	public Bitmap applyEffect() {
        
		int color;
		double red = 0.0, blue = 0.0, green = 0.0, contrast;
		
		if (isLowLevel) { 
			contrast = (100.0 - contrastFactor)/100.0;
		} else { 
			contrast = (100.0 + contrastFactor)/100.0;
		}
		
		if (bm != null) { 
			
			for (int x = 0; x < bm.getWidth(); x++) { 
				for (int y = 0; y < bm.getHeight(); y++) { 
					
					color = bm.getPixel(x, y);
					
					red = Color.red(color) / 255.0;
					green = Color.green(color) / 255.0;
					blue = Color.blue(color) / 255.0;
					
	                red -= 0.5;
	                red *= contrast;
	                red += 0.5;
	                red *= 255;
	                
	                if (red > 255) {
	                    red = 255;
	                }
	                if (red < 0) {
	                    red = 0;
	                }

	                green -= 0.5;
	                green *= contrast;
	                green += 0.5;
	                green *= 255;

	                if (green > 255) {
	                    green = 255;
	                }
	                if (green < 0) {
	                    green = 0;
	                }

	                blue -= 0.5;
	                blue *= contrast;
	                blue += 0.5;
	                blue *= 255;

	                if (blue > 255) {
	                    blue = 255;
	                }
	                if (blue < 0) {
	                    blue = 0;
	                }
	                
	                bm.setPixel(x, y, Color.argb(Color.alpha(color), (int)red, (int)green, (int)blue));
				}
			}
			
			return bm;
		}

		return null;
	}

}
