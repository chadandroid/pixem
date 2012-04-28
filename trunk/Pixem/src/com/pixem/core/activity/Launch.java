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
 * Module: Launch.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   10107896
 * Date:        2012-03-25
 * Version:
 *
 *
 */
package com.pixem.core.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;

import com.pixem.R;

/**
 * @author 10107896
 *
 */
public class Launch extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.launch);
		
	}
	
	@Override
	public void onResume() { 
		super.onResume();
		
		new CountDownTimer(3000, 1000) {

		     public void onTick(long millisUntilFinished) {
		     }

		     public void onFinish() {
		    	 
		    	 Intent intent = new Intent().setClass(Launch.this, MainMenu.class);
		    	 startActivity(intent);
		     }
		  }.start();
	}

}
