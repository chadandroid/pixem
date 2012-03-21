package com.pixem.core;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.pixem.borders.Border;
import com.pixem.borders.NullBorder;
import com.pixem.effects.Effect;

/**
 * @author 10107896
 * 
 */
public class PictureSession {

	private Bitmap original;
	private Bitmap current;
	private Border border;

	private Bitmap smallImage; //for doing the previews and for faster generation
	
	private ImageView img;

	public PictureSession(Bitmap bm, ImageView img) {
		original = bm.copy(Config.ARGB_8888, true);
		current = bm.copy(Config.ARGB_8888, true);

		border = new NullBorder();

		this.img = img;
		
		//makeSmallerImage(); //makes small icon image, sets smallerImage to it
	}

	public void applyEffect(Effect effect) {
		current = effect.applyEffect(original);
	}

	public void addBorder(Border border) {
		this.border = border;
	}

	public void draw() {
		img.setImageBitmap(getCombined());
	}

	/**
	 * http://www.anddev.org/resize_and_rotate_image_-_example-t621.html
	 */
	public void makeSmallerImage() { 
	
		if (current != null) { 
			int orgWidth = current.getWidth();
		    int orgHeight = current.getHeight();
		    int newWidth = 100;
		    int newHeight = 100;
		    
	        // calculate the scale - in this case = 0.4f
	        float scaleWidth = ((float) newWidth) / orgWidth;
	        float scaleHeight = ((float) newHeight) / orgHeight;
	        
	        // createa matrix for the manipulation and resize
	        Matrix matrix = new Matrix();
	        matrix.postScale(scaleWidth, scaleHeight);
	        
	        smallImage = Bitmap.createBitmap(current, 0, 0,
                    newWidth, newHeight, matrix, true);
		}
	}
	
	public boolean save(Context context) {
		String path = Environment.getExternalStorageDirectory().toString();
		File file = new File(path, "pic.png");

		Bitmap bmp = getCombined().copy(Config.ARGB_8888, true);

		try {
			FileOutputStream out = new FileOutputStream(file);
			bmp.compress(Bitmap.CompressFormat.PNG, 90, out);

			out.flush();
			out.close();

			MediaStore.Images.Media.insertImage(context.getContentResolver(),
					file.getAbsolutePath(), file.getName(), file.getName());

			Log.d("Save: ", "Successful");
			return true;
		} catch (Exception e) {
			Log.d("Save: ", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	private Bitmap getCombined() {
		// Copy effected image
		Bitmap output = current.copy(Config.ARGB_8888, true);

		Canvas canvas = new Canvas(output);
		Rect rect = new Rect(0, 0, output.getWidth(), output.getHeight());

		// Draw border on it
		canvas.drawBitmap(
				border.generateBorder(current.getWidth(), current.getHeight()),
				rect, rect, new Paint());

		return output;
	}
	
	/**
	 * @return small image to perform the filters on,
	 * but not the one that is to be saved
	 */
	private Bitmap getSmallImage() { 
		return smallImage;
	}

}
