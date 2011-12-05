package com.pixem.tabactivity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;

import com.pixem.utility.TabButtonSpec;

/**
 * @author Saman Alvi
 *
 */
public class TabBarActivity extends ActivityGroup {
	
	private ArrayList<TabButtonSpec> navigationList;
	private ArrayList<ArrayList<TabButtonSpec>> tabStackList;
	
	private int currentTabSelected = -1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		
		super.onCreate(savedInstanceState);
		if (navigationList == null)
			navigationList = new ArrayList<TabButtonSpec>();
		
		if (tabStackList == null)
			tabStackList = new ArrayList<ArrayList<TabButtonSpec>>();
	}
	
	/**
	* This is called when a child activity of this one calls its finish method.
	* This implementation calls {@link LocalActivityManager#destroyActivity} on the child activity
	* and starts the previous activity.
	* If the last child activity just called finish(),this activity (the parent),
	* calls finish to finish the entire group.
	*/
	@Override
	public void finishFromChild(Activity child) {
		
	}
	
	public void addTab(String name, Drawable icon, Drawable onState, Drawable offState, Class classIntent) { 

		TabBarActivity activity = Parent(this);
		activity.navigationList.add(new TabButtonSpec(icon, onState, offState, activity.navigationList.size() + "_0", classIntent));

		String n = null;

		if (classIntent != null) {
			n = classIntent.getName();
		} else {
			n = "";
		}

		final ArrayList<TabButtonSpec> childList = new ArrayList<TabButtonSpec>();
		childList.add(new TabButtonSpec(icon, onState, offState, activity.navigationList.size() + "_0", classIntent));

		activity.tabStackList.add(childList);

		n = null; 
	}
	
	public void eventTabButtonClicked(int position) { 
		 
		//remove old tab list
		setCurrentTab(position);
		
	}

	public void setCurrentTab (int position) { 
		this.currentTabSelected = position;
	}
	
	public void pushScreen() { 
		
	}
	
	public void addTab(String name, Drawable d, Class c) { 
		  
	}
	
	public void popScreen() { 
		
	}
	
	public void popScreensToBottomScreen() { 
		
	}
	
	public static TabBarActivity Parent(TabBarActivity activity) {

		return (activity.isChild() ? (TabBarActivity) activity.getParent() : activity);
	}
	
	@Override
	public void onDestroy () { 
		super.onDestroy();
	}
	
	@Override
	public void onLowMemory() { 
		super.onLowMemory();
	}
}
