package com.pixem.core.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.photoedit.pixem.R;

public class MainMenu extends Activity {
	private Button loadButton, galleryButton;
	private Button takePictureButton;
	private Button shareButton;

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
				Intent myIntent = new Intent(v.getContext(), Filter.class);
                startActivityForResult(myIntent, 0);
			}
		});
		
		takePictureButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				createDialog("Not Implemented.");
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
}