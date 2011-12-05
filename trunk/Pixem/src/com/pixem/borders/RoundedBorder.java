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
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

/**
 * @author Saman Alvi
 *
 */
public class RoundedBorder implements Border {

	private final static int DEFAULT_ARC_WIDTH = 25;
	private final static int DEFAULT_ARC_HEIGHT = 25;
	
	
	private int arcWidth;
	private int arcHeight;
	private int borderColor;
	
	public RoundedBorder() { 
		this(0, DEFAULT_ARC_WIDTH, DEFAULT_ARC_HEIGHT);
	}
	
	public RoundedBorder(int colour) { 
		this(colour, DEFAULT_ARC_WIDTH, DEFAULT_ARC_HEIGHT);
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
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, width, height);
		RectF rectF = new RectF(rect);
		
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(borderColor);
		canvas.drawRoundRect(rectF, arcWidth, arcHeight, paint);
		
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(output, rect, rect, paint);
		
		return output;
	}
}
