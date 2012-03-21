package com.pixem.core;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.pixem.R;
import com.pixem.effects.BlackAndWhite;
import com.pixem.effects.ColoredSquares;
import com.pixem.effects.ColourFilter;
import com.pixem.effects.Contrast;
import com.pixem.effects.Effect;
import com.pixem.effects.NullEffect;
import com.pixem.effects.Sepia;

public class EffectFactory {
	
	private List<Effect> effects;
	
	public EffectFactory() {
		effects = new ArrayList<Effect>();
		effects.add(new NullEffect());
		effects.add(new BlackAndWhite());
		effects.add(new Contrast());
		effects.add(new Sepia());
		effects.add(new ColourFilter(Color.argb(255, 70, 0, 0)));
		effects.add(new ColourFilter(Color.argb(255, 0, 70, 0)));
		effects.add(new ColourFilter(Color.argb(255, 0, 0, 70)));
		effects.add(new ColourFilter(Color.argb(255, 100, 0, 100)));
		effects.add(new ColoredSquares());
		
	}
	
	public int getNumberOfEffects() {
		return effects.size();
	}
	
	public List<Integer> getEffectIcons() {
		List<Integer> icons = new ArrayList<Integer>();
		
		icons.add(R.drawable.a);
		icons.add(R.drawable.b);
		icons.add(R.drawable.c);
		icons.add(R.drawable.d);
		icons.add(R.drawable.e);
		icons.add(R.drawable.f);
		icons.add(R.drawable.g);
		icons.add(R.drawable.h);
		icons.add(R.drawable.i);
		
		return icons;
	}
	
	public Effect getEffect(int id) {
		return effects.get(id);
	}
}
