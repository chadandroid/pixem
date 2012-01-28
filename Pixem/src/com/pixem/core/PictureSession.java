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
public class PictureSession {
	
	private Bitmap original;
	private Bitmap current;
	private Border border;
	
	public PictureSession (Bitmap bm) { 
		original = bm.copy(Config.ARGB_8888, true);
		current = bm.copy(Config.ARGB_8888, true);
	}
	
	public void applyEffect(Effect effect) {
		current = effect.applyEffect(current);
	}

	public void addBorder(Border border) {
		this.border = border;
	}

	public void removeBorder() {
		border = null;
	}

	public void removeEffect() {
		current = original.copy(Config.ARGB_8888, true);
	}

	public Bitmap getCombined() {
		// Copy effected image
		Bitmap output = current.copy(Config.ARGB_8888, true);

		Canvas canvas = new Canvas(output);
		Rect rect = new Rect(0, 0, output.getWidth(), output.getHeight());
		
		// Draw border on it, I think this is how its done...
		if(border != null) {
			canvas.drawBitmap(border.generateBorder(current.getWidth(), current.getHeight()), rect, rect, new Paint());
		}
		
		return output;
	}
	
	

}
