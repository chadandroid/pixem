/**
 *
 * Module: RoundedBorder.java
 * Project: Pixem
 *
 * Description:
 *
 * Developer:   Saman Alvi
 * Date:        2011-11-08
 * Version:		1.0
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
 * @author Saman Alvi
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

	/*
	 * http://ruibm.com/?p=184
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
