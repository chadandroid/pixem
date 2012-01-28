package com.pixem.core;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.ImageView;

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
	
	private ImageView img;
	
	public PictureSession (Bitmap bm, ImageView img) { 
		original = bm.copy(Config.ARGB_8888, true);
		current = bm.copy(Config.ARGB_8888, true);
		
		this.img = img;
	}
	
	public void applyEffect(Effect effect) {
		current = effect.applyEffect(original);
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

	public void draw() {
		// Copy effected image
		Bitmap output = current.copy(Config.ARGB_8888, true);

		Canvas canvas = new Canvas(output);
		Rect rect = new Rect(0, 0, output.getWidth(), output.getHeight());
		
		// Draw border on it, I think this is how its done...
		if(border != null) {
			canvas.drawBitmap(border.generateBorder(current.getWidth(), current.getHeight()), rect, rect, new Paint());
		}
		
		img.setImageBitmap(output);
	}
	
	

}
