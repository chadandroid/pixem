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
 * Module: Picture.java
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
package com.pixem.core;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.pixem.borders.Border;
import com.pixem.effects.Effect;

/**
 * @author Saman Alvi
 *
 */
public class Picture {
	
	private Bitmap original;
	private Bitmap current;
	private Bitmap border;
	
	public Picture (Bitmap bm) { 
		original = bm.copy(Config.ARGB_8888, true);
		current = bm.copy(Config.ARGB_8888, true);
	}
	
	public void applyEffect(Effect effect) {
		current = effect.applyEffect(current);
	}

	public void addBorder(Border border) {
		this.border = border.generateBorder(original.getWidth(), original.getHeight());
	}

	public void removeBorder() {
		border = null;
	}

	public void revert() {
		current = original.copy(Config.ARGB_8888, true);
	}

	public Bitmap getCombined() {
		// Copy effected image
		Bitmap output = current.copy(Config.ARGB_8888, true);

		Canvas canvas = new Canvas(output);
		Rect rect = new Rect(0, 0, output.getWidth(), output.getHeight());
		
		// Draw border on it, I think this is how its done...
		canvas.drawBitmap(border, rect, rect, new Paint());
		
		return output;
	}
	
	

}
