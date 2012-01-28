package com.pixem.core;

import java.util.ArrayList;
import java.util.List;

import com.photoedit.pixem.R;
import com.pixem.borders.Border;
import com.pixem.borders.NullBorder;
import com.pixem.borders.StraightBorder;

public class BorderFactory {
	
	private List<Border> borders;
	
	public BorderFactory() {
		borders = new ArrayList<Border>();
		borders.add(new NullBorder());
		borders.add(new StraightBorder());
	}
	
	public int getNumberOfEffects() {
		return borders.size();
	}
	
	public List<Integer> getBorderIcons() {
		List<Integer> icons = new ArrayList<Integer>();
		
		icons.add(R.drawable.y);
		icons.add(R.drawable.z);
		
		return icons;
	}
	
	public Border getBorder(int id) {
		return borders.get(id);
	}
}
