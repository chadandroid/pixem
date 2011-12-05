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
