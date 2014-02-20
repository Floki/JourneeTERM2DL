package com.nadt.drawandrace.game.active;

import android.graphics.Color;
import android.graphics.Path;
import com.nadt.drawandrace.game.GameActivity;
import com.nadt.drawandrace.utils.Pair;

public class TrackStart extends Sprite {

	public TrackStart(float pX, float pY, float pAngle) {
		super(pX, pY, pAngle);
		Path line = new Path();
		int width = GameActivity.virtualXToScreenX(300);
		int height = GameActivity.virtualXToScreenX(300);
		setSize(width, height);
		line.moveTo(pX - width / 2, pY - height / 2);
		line.lineTo(pX + width / 2, pY - height / 2);
		line.lineTo(pX + width / 2, pY + height / 2);
		line.lineTo(pX - width / 2, pY + height / 2);
		this.addComponentToDraw(line, Color.GREEN);
	}
	
	public void setPosition(float pX, float pY) {
		super.setPosition(pX, pY);
		Path line = new Path();
		int width = GameActivity.virtualXToScreenX(300);
		int height = GameActivity.virtualXToScreenX(300);
		setSize(width, height);
		line.moveTo(pX - width / 2, pY - height / 2);
		line.lineTo(pX + width / 2, pY - height / 2);
		line.lineTo(pX + width / 2, pY + height / 2);
		line.lineTo(pX - width / 2, pY + height / 2);
		spriteComponents.clear();
		this.addComponentToDraw(line, Color.GREEN);
	}
}
