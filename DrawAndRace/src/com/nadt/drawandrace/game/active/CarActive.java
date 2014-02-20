package com.nadt.drawandrace.game.active;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;

import com.nadt.drawandrace.utils.Tools;

public class CarActive extends Sprite {

	public CarActive(float pX, float pY, float pAngle, float pWidth, float pHeight) {
		super(pX, pY, pAngle);
		Path car = new Path();
		car.moveTo(pX - pWidth / 2, pY - pHeight / 2);
		car.lineTo(pX + pWidth / 2, pY - pHeight / 2);
		car.lineTo(pX + pWidth / 2, pY + pHeight / 2);
		car.lineTo(pX - pWidth / 2, pY + pHeight / 2);
		Path glass = new Path();
		car.moveTo(pX - pWidth / 4, pY - pHeight / 4);
		car.lineTo(pX + pWidth / 4, pY - pHeight / 4);
		car.lineTo(pX + pWidth / 4, pY - pHeight);
		car.lineTo(pX - pWidth / 4, pY - pHeight);
		this.addComponentToDraw(car, Color.WHITE);
		this.addComponentToDraw(glass, Color.GRAY);
	}
}
