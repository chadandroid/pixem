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
 * Module: Border.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   10107896
 * Date:        2011-11-10
 * Version:
 *
 *
 */
package com.pixem.borders;

import android.graphics.Bitmap;

/**
 * @author 10107896
 *
 */
public interface Border {

	
	public Bitmap generateBorder(Bitmap bm);
	
	public void setBitmap(Bitmap bm);
	public Bitmap getBitmap();
}
