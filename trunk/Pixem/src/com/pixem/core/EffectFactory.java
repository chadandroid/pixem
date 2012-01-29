package com.pixem.core;

import java.util.ArrayList;
import java.util.List;

import com.pixem.R;
import com.pixem.effects.BlackAndWhite;
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
		
		return icons;
	}
	
	public Effect getEffect(int id) {
		return effects.get(id);
	}
}
