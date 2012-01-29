package com.pixem.core;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
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
 * @author Saman Alvi
 * 
 */
public class PictureSession {

	private Bitmap original;
	private Bitmap current;
	private Border border;

	private ImageView img;

	public PictureSession(Bitmap bm, ImageView img) {
		original = bm.copy(Config.ARGB_8888, true);
		current = bm.copy(Config.ARGB_8888, true);

		border = new NullBorder();

		this.img = img;
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

}
