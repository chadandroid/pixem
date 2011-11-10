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
 * Module: BrowsePicture.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   10107896
 * Date:        2011-11-08
 * Version:
 *
 *
 */
package com.photoedit.pixem.core;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.photoedit.pixem.R;

/**
 * @author 10107896
 *
 */
public class BrowsePicture extends Activity {

	private static final int SELECT_PICTURE = 1;

	private String selectedImagePath;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

/*		((Button) findViewById(R.id.Button01)).setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {

						// in onCreate or any event where your want the user to
						// select a file
						Intent intent = new Intent();
						intent.setType("image/*");
						intent.setAction(Intent.ACTION_GET_CONTENT);
						startActivityForResult(
								Intent.createChooser(intent, "Select Picture"),
								SELECT_PICTURE);
					}

				});*/
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				Uri selectedImageUri = data.getData();
				selectedImagePath = getPath(selectedImageUri);
			}
		}
	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
}