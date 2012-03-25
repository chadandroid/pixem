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
 * Module: EffectListener.java
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
package com.pixem.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;

import com.pixem.R;
import com.pixem.borders.Border;
import com.pixem.core.PictureSession;

/**
 * @author 10107896
 *
 */
public class BorderListener implements OnClickListener {
	
	private Border border;
	private PictureSession session;
	private Activity activity;
	
	public BorderListener(Border border, PictureSession session, Activity activity) { 
		this.border = border;
		this.session = session;
		this.activity = activity;
	}
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
      final ProgressDialog pd = ProgressDialog.show(activity, "", activity.getString(R.string.loading));
        
      new Thread() {
            public void run() {
                session.addBorder(border);
                
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        session.draw();
                        pd.dismiss();
                    }
                });
            }
        }.start();
	}

}
