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
 * Module: Gallery.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   10107896
 * Date:        2012-01-28
 * Version:
 *
 *
 */
package com.pixem.core.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

/**
 * @author 10107896
 *
 */
public class Gallery extends Activity {

    private static final int SELECT_PICTURE = 0;
    
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		
		Intent gallIntent = new Intent(Intent.ACTION_GET_CONTENT);
		gallIntent.setType("image/*"); 
		
        startActivityForResult(Intent.createChooser(gallIntent,
                "Select Picture"), SELECT_PICTURE);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) { 
			Uri selectedImage = data.getData();
			String[] filePathColumn = {MediaStore.Images.Media.DATA};

			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String filePath = cursor.getString(columnIndex);
			cursor.close();
			
			Log.d("", "== file path is: " + filePath);
			
			Intent intent = new Intent().setClass(Gallery.this, Filter.class);
			intent.putExtra("filepath", filePath);
			intent.putExtra("uri", selectedImage);
			startActivity(intent);
			
			finish();
		}
	}
}
