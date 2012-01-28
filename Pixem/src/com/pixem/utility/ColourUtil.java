package com.pixem.utility;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;

/**
 * @author Saman Alvi
 *
 */
public class ColourUtil {
	
	public static int average (int colour1, int colour2, double percent) { 
		
		try { 
			return Color.argb(255, (int) Math.round(Color.red(colour1) * percent + Color.red(colour2) * (1 - percent)), (int) Math.round(Color.green(colour1) * percent + Color.green(colour2) * (1 - percent)), (int) Math.round(Color.blue(colour1) * percent + Color.blue(colour2) * (1 - percent)));
		} catch (Exception e) { 
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public static Bitmap switchBlueGreen(Bitmap bm) { 
		
		int colour = 0, blue = 0, green = 0;
		if (bm != null) { 
			Bitmap modifiedBitmap = bm.copy(Config.ARGB_8888, true);
			
			for (int i = 0; i < modifiedBitmap.getWidth(); i++) { 
				for (int j = 0; j < modifiedBitmap.getHeight(); j++) { 
					
					colour = modifiedBitmap.getPixel(i, j);
					blue = Color.blue(colour);
					green = Color.green(colour);
					
					modifiedBitmap.setPixel(i, j, Color.argb(255, Color.red(colour), blue, green));
				}
			}
			
			return modifiedBitmap;
		}
		
		return null;
	}
	
	
}
