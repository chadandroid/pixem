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
 * Module: ColourFilter.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   Saman Alvi
 * Date:        2011-11-10
 * Version:
 *
 *
 */
package com.pixem.effects;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

/**
 * @author Saman Alvi
 *
 */
public class ColourFilter implements Effect {

	private int colour;
	
	public ColourFilter(int colourChoice) { 
		colour = colourChoice;
	}

	public void setColour (int colour) { 
		this.colour = colour;
	}
	
	public int getColour() { 
		return colour;
	}
	
	@Override
	public Bitmap applyEffect(Bitmap bm) {
			
		Bitmap modifiedBitmap = bm.copy(Config.ARGB_8888, true);
			
		return modifiedBitmap;	
	}	
}
