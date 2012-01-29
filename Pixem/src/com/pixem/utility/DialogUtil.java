package com.pixem.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {
	public static void createDialog(Context context, String message) {
		final AlertDialog alertDialog;
		alertDialog = new AlertDialog.Builder(context).create();
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
}
