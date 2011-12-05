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
 * Module: Utility.java
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
package com.pixem.utility;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * @author Saman Alvi
 *
 */
public class Utility {
	
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
