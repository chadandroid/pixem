/**
 * Module: TabSpec.java
 * Project: Pixem
 *
 * Description:
 *
 * Developer:   Saman Alvi
 * Date:        2011-11-17
 * Version:		1.0
 *
 */
package com.pixem.utility;

import android.graphics.drawable.Drawable;

public class TabButtonSpec {

	private Drawable icon, onState, offState;
	private String name;
	private Class classIntent;
	
	public TabButtonSpec (Drawable icon, Drawable onState, Drawable offState, String name, Class classIntent) { 
		this.icon = icon;
		this.onState = onState;
		this.offState = offState;
		this.name = name;
		this.classIntent = classIntent;
	}
	
	public Drawable getDrawableIcon() { 
		return icon;
	}
	
	public Drawable getOnDrawable() { 
		return onState;
	}
	
	public Drawable getOffDrawable() { 
		return offState;
	}
	
	public String getName() { 
		return name;
	}
	
	public Class getIntent() { 
		return classIntent;
	}
	
}
