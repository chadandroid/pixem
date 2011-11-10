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
package com.pixem.borders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

import com.pixem.effects.Effect;

/**
 * @author 10107896
 *
 */
public class RoundedBorder implements Border {

	
	private int arcWidth, arcHeight, borderColor;
	private Bitmap bm;
	
	public RoundedBorder() { 
		this(0, 0, 0);
	}
	
	public RoundedBorder (int color, int arcWidth, int arcHeight) { 
		borderColor = color;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
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
	public Bitmap generateBorder(int width, int height) {
		
		 if (bm != null && borderColor != 0 && arcHeight != 0 && arcWidth != 0) { 
			 
			 setBitmap(bm);
				
		        Bitmap output = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Config.ARGB_8888);
		        Canvas canvas = new Canvas(output);

		        final Paint paint = new Paint();
		        final Rect rect = new Rect(0, 0, bm.getWidth(), bm.getHeight());
		        final RectF rectF = new RectF(rect);
		        final float roundPx = arcWidth;

		        paint.setAntiAlias(true);
		        canvas.drawARGB(0, 0, 0, 0);
		        paint.setColor(borderColor);
		        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		        canvas.drawBitmap(bm, rect, rect, paint);

		        return output;
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

	/* (non-Javadoc)
	 * @see com.pixem.borders.Border#generateBorder(android.graphics.Bitmap)
	 */
	@Override
	public Bitmap generateBorder(Bitmap bm) {
		// TODO Auto-generated method stub
		return null;
	}

}
