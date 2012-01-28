package com.pixem.effects;

import java.util.ArrayList;
import java.util.List;

import com.photoedit.pixem.R;

public class EffectFactory {
	
	private List<Effect> effects;
	
	public EffectFactory() {
		effects = new ArrayList<Effect>();
		effects.add(new BlackAndWhite());
		effects.add(new Smooth());
		effects.add(new Sepia());
		effects.add(new BlackAndWhite());
		effects.add(new Smooth());
		effects.add(new Sepia());
		effects.add(new BlackAndWhite());
		effects.add(new Smooth());
		effects.add(new Sepia());
		effects.add(new BlackAndWhite());
		effects.add(new Smooth());
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
		icons.add(R.drawable.a);
		icons.add(R.drawable.b);
		icons.add(R.drawable.c);
		icons.add(R.drawable.a);
		icons.add(R.drawable.b);
		icons.add(R.drawable.c);
		icons.add(R.drawable.a);
		icons.add(R.drawable.b);
		icons.add(R.drawable.c);
		
		return icons;
	}
	
	public Effect getEffect(int id) {
		return effects.get(id);
	}
}
