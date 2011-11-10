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
 * Module: StraightBorder.java
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
package com.pixem.borders;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

/**
 * @author 10107896
 *
 */
public class StraightBorder implements Border {   

	private int color;
	private int frameSize;
	private Bitmap bm;
	
	public StraightBorder() { 
		this (0, 0, null);
	}
	
	public StraightBorder (int color, int frameSize, Bitmap bm) { 
		this.color = color;
		this.frameSize = frameSize;
		this.bm = bm;
	}
	
	public void setFrameSize(int frameSize) { 
		this.frameSize = frameSize;
	}
	
	public void setColor(int color) { 
		this.color = color;
	}
	
	public int getColor() { 
		return color;
	}
	
	public int getFrameSize() { 
		return frameSize;
	}


	@Override
	public Bitmap generateBorder(Bitmap bm) {
		
		if (bm != null && color != 0 && frameSize != 0) { 
			
			setBitmap(bm);
			
			Bitmap modifiedBitmap = bm.copy(Config.ARGB_8888, true);
			
			for (int i = 0; i < modifiedBitmap.getWidth(); i++) { 
				for (int j = 0; j < modifiedBitmap.getHeight(); j++) { 
	                if (i < frameSize / 2 || j < frameSize / 2 || i > bm.getWidth() - frameSize / 2
	                        || j > bm.getHeight() - frameSize / 2) {
	                	modifiedBitmap.setPixel(i, j, color);
	                } else {
	                	modifiedBitmap.setPixel(i, j, bm.getPixel(i, j));
	                }
				}
			}
			
			return modifiedBitmap;
		}
		
		
		return null;
	}
	
	@Override
	public void setBitmap(Bitmap bm) { 
		this.bm = bm;
	}
	
	@Override
	public Bitmap getBitmap() { 
		return bm;
	}
}
