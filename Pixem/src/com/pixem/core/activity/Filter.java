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
 * Module: Filter.java
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pixem.R;
import com.pixem.core.BorderFactory;
import com.pixem.core.EffectFactory;
import com.pixem.core.PictureSession;
import com.pixem.utility.BorderListener;
import com.pixem.utility.EffectListener;

/**
 * @author 10107896
 *
 */
public class Filter extends Activity {

	private LinearLayout layoutEffects = null;
	private EffectFactory effectFactory;
	private BorderFactory borderFactory;
	private PictureSession session;
	private ImageView img;
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.filter_screen);
		
		((Button) findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		((Button) findViewById(R.id.btnSave)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				savePicture2();
			}
		});
		
		layoutEffects = (LinearLayout) findViewById(R.id.layoutEffects);
		effectFactory = new EffectFactory();
		borderFactory = new BorderFactory();
		img = (ImageView) findViewById(R.id.imgMainImage);
		
	}
	
	@Override
	public void onResume() { 
		super.onResume();
		
		Bitmap bm = null;
		if (getIntent().getExtras() != null) { 
			if (getIntent().getExtras().containsKey("uri")) { 
				bm = getBitmapImage();
			}
		}
		
		if (bm == null) { 
			session = new PictureSession(BitmapFactory.decodeResource(this.getResources(), R.drawable.img_girl), img);
		} else { 
            session = new PictureSession(bm, img);
		}
		
		session.draw();
		addFilterBorderButton();

	}

	private void addFilterBorderButton() { 
		
		if (layoutEffects != null && layoutEffects.getChildCount() != 0) { 
			layoutEffects.removeAllViews();
		}
		
		Button btnFilter = new Button(this);
		btnFilter.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.5f));
		btnFilter.setText(getString(R.string.filters));
		btnFilter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showFilters();
			}
		});
		
		Button btnBorder = new Button(this);
		btnBorder.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 0.5f));
		btnBorder.setText(getString(R.string.borders));
		btnBorder.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showBorders();
			}
		});
		
		layoutEffects.addView(btnFilter);
		layoutEffects.addView(btnBorder);
	}
	
	private void showFilters() { 
		layoutEffects.removeAllViews();
		
		Button btnBack = new Button(this);
		btnBack.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btnBack.setText(getString(R.string.back));
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addFilterBorderButton();
			}
		});
		
		layoutEffects.addView(btnBack);
		layoutEffects.addView(getFilterEffects());
	}
	
	private void showBorders() { 
		layoutEffects.removeAllViews();
		
		Button btnBack = new Button(this);
		btnBack.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		btnBack.setText(getString(R.string.back));
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addFilterBorderButton();
			}
		});
		
		layoutEffects.addView(btnBack);
		layoutEffects.addView(getBorderEffects());
	}
	
	private HorizontalScrollView getFilterEffects() { 
		HorizontalScrollView scrollView = new HorizontalScrollView(this);
		scrollView.setHorizontalScrollBarEnabled(false);

		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		int counter = 0;
		
		for (Drawable d : getFilterImages()) { 
			ImageView img = new ImageView(this);
			img.setPadding(5, 0, 5, 0);
			img.setImageDrawable(d);
			img.setOnClickListener(new EffectListener(effectFactory.getEffect(counter++), session));
			
			layout.addView(img);
		}
		
		scrollView.addView(layout);
		return scrollView;
	}
	
	private HorizontalScrollView getBorderEffects() { 
		HorizontalScrollView scrollView = new HorizontalScrollView(this);
		scrollView.setHorizontalScrollBarEnabled(false);
		
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	
		int counter = 0;
		
		for (Drawable d : getBorderImages()) { 
			ImageView img = new ImageView(this);
			img.setPadding(5, 0, 5, 0);
			img.setImageDrawable(d);
			img.setOnClickListener(new BorderListener(borderFactory.getBorder(counter++), session));
			
			layout.addView(img);
		}
		
		scrollView.addView(layout);
		return scrollView;
	}	
	
	private ArrayList<Drawable> getFilterImages() { 
		
		ArrayList<Drawable> images  = new ArrayList<Drawable>();

		for (Integer i : effectFactory.getEffectIcons()) { 
			images.add(getApplicationContext().getResources().getDrawable(i));
		}
		
		return images;
	}
	
	private ArrayList<Drawable> getBorderImages() { 
		
		ArrayList<Drawable> images  = new ArrayList<Drawable>();

		for (Integer i : borderFactory.getBorderIcons()) { 
			images.add(getApplicationContext().getResources().getDrawable(i));
		}
		
		return images;
	}
	
	@Override
	public void onPause() { 
		super.onPause();
		
		session = null;
	}
	
	public Bitmap getBitmapImage() { 
		
		Bitmap selectedBitmap = null;
		
		try {
			Uri selectedImage = getIntent().getExtras().getParcelable("uri");
			String[] filePathColumn = {MediaStore.Images.Media.DATA};

			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String filePath = cursor.getString(columnIndex);
			cursor.close();
			
			selectedBitmap = decodeUri(selectedImage);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		return selectedBitmap;
	}
	/**
	 * Taken from: http://stackoverflow.com/a/5086706/786414
	 * @param selectedImage
	 * @return
	 * @throws FileNotFoundException
	 */
	private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException { 
		
	    // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 250;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
               || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
	}

	private void savePicture2() { 
        ContentValues values = new ContentValues();
        values.put(Images.Media.TITLE, "title");
        values.put(Images.Media.BUCKET_ID, "test");
        values.put(Images.Media.DESCRIPTION, "test Image taken");
        values.put(Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
        OutputStream outstream;
        try {
                outstream = getContentResolver().openOutputStream(uri);
                img.getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 70, outstream);
                outstream.close();
        } catch (FileNotFoundException e) {
                //
        } catch (IOException e){
                //
        }
	}
	
	
/*	private void savePicture(String fileName) { 

		try {
			String path = Environment.getExternalStorageDirectory().toString();
			OutputStream fOut = null;
			File file = new File(path, fileName + ".jpg");
			fOut = new FileOutputStream(file);
			
			if (img.getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 85, fOut)) { 
				fOut.flush();
				fOut.close();

				MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
				Log.d("", "== saved image");
			} else { 
				fOut.flush();
				fOut.close();
				Log.d("", "== couldn't compress bitmap");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.d("", "== file not found exception");
		} catch (IOException ex) { 
			ex.printStackTrace();
			Log.d("", "== ioexception");
		}
	}
	*/
/*	private void savePicture (String imagePath, long dateTaken) {
		
		ContentValues v = new ContentValues();
		//v.put(Images.Media.TITLE, title);
		v.put(Images.Media.DISPLAY_NAME, "");
		//v.put(Images.Media.DESCRIPTION, description);
		v.put(Images.Media.DATE_ADDED, dateTaken);
		v.put(Images.Media.DATE_TAKEN, dateTaken);
		v.put(Images.Media.DATE_MODIFIED, dateTaken) ;
		v.put(Images.Media.MIME_TYPE, "image/jpeg");
		v.put(Images.Media.ORIENTATION, 0);
		
		File f = new File(imagePath) ;
		File parent = f.getParentFile() ;
		String path = parent.toString().toLowerCase() ;
		String name = parent.getName().toLowerCase() ;
		v.put(Images.ImageColumns.BUCKET_ID, path.hashCode());
		v.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, name);
		v.put(Images.Media.SIZE,f.length()) ;
		f = null ;
		
		if( targ_loc != null ) {
		v.put(Images.Media.LATITUDE, loc.getLatitude());
		v.put(Images.Media.LONGITUDE, loc.getLongitude());
		}
		
		v.put("_data",imagePath) ;
		ContentResolver c = getContentResolver() ;
		c.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, v);
		
		//return c.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, v);
	}*/
}
