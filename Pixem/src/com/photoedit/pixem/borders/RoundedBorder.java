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
 * Module: RoundedBorder.java
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
package com.photoedit.pixem.borders;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import com.photoedit.pixem.core.Effect;

/**
 * @author 10107896
 *
 */
public class RoundedBorder implements Effect {

	
	private int arcWidth, arcHeight, borderColor;
	private Bitmap bm;
	
	public RoundedBorder() { 
		this(0, 0, 0, null);
	}
	
	public RoundedBorder (int color, int arcWidth, int arcHeight, Bitmap bm) { 
		borderColor = color;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
		this.bm = bm;
	}
	
	public void setBorderColor(int color) { 
		borderColor = color;
	}
	
	public void setArcWidthHeight(int arcWidth, int arcHeight) { 
		this.arcHeight = arcHeight;
		this.arcWidth = arcWidth;
	}
	
	public int getColor() { 
		return borderColor;
	}
	
	public int getArcWidth() { 
		return arcWidth;
	}
	
	public int getArcHeight() { 
		return arcHeight;
	}

	/* (non-Javadoc)
	 * @see com.photoedit.pixem.org.Effect#applyEffect()
	 */
	@Override
	public Bitmap applyEffect() {
		
		 if (bm != null && borderColor != 0 && arcHeight != 0 && arcWidth != 0) { 
				Bitmap modifiedBitmap = bm.copy(Config.ARGB_8888, true);
				
				
				
		 }
		
		return null;
	}
	
	
}
