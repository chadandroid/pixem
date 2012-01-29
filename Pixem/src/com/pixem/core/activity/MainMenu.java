package com.pixem.core.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.pixem.R;

public class MainMenu extends Activity {
	private Button loadButton, galleryButton;
	private Button takePictureButton;
	private Button shareButton;
	
	private final int GALLERY_PICTURE = 0;
	private final int CAMERA_PICTURE = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
		setContentView(R.layout.main);

		loadButton = (Button) findViewById(R.id.loadImageButton);
		takePictureButton = (Button) findViewById(R.id.takePictureButton);
		shareButton = (Button) findViewById(R.id.shareButton);
		galleryButton = (Button) findViewById(R.id.btnGallery);
	}

	@Override
	public void onResume() {
		super.onResume();
		
		setButtonListeners();
	}

	private void setButtonListeners() {
		loadButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainMenu.this, Filter.class);
                startActivity(myIntent);
			}
		});
		
		takePictureButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	            startActivityForResult(intent, CAMERA_PICTURE);
	        }
		});
		
		shareButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createDialog("Not Implemented.");
			}
		});
		
		galleryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent().setClass(MainMenu.this, Gallery.class);
				startActivity(intent);
			}
		});
	}
	
	private void createDialog(String message) {
		final AlertDialog alertDialog;
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(message);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	            //dismiss the dialog  
	        	alertDialog.dismiss();
	          }
	      });
		
		alertDialog.setCancelable(true);
		alertDialog.show();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();

		System.gc();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();


		System.gc();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == GALLERY_PICTURE && resultCode == RESULT_OK) { 
			Intent intent = getImageData(data);
			startActivity(intent);
		} else if (requestCode == CAMERA_PICTURE && resultCode == RESULT_OK) { 
			Intent intent = getImageData(data);
			startActivity(intent);
		}
	}
	
	private Intent getImageData (Intent data) { 
		
		Uri selectedImage = data.getData();
		String[] filePathColumn = {MediaStore.Images.Media.DATA};

		Log.d("", "== uri is " + selectedImage);
		
		Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
		cursor.moveToFirst();

		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		String filePath = cursor.getString(columnIndex);
		cursor.close();
		
		Log.d("", "== file path is: " + filePath);
		
		Intent intent = new Intent().setClass(MainMenu.this, Filter.class);
		intent.putExtra("filepath", filePath);
		intent.putExtra("uri", selectedImage);
		
		return intent;
	}
}