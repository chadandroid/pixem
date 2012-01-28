package com.pixem.effects;

import android.graphics.Bitmap;

public class NullEffect implements Effect {

	@Override
	public Bitmap applyEffect(Bitmap bm) {
		return bm;
	}

}
