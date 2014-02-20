package com.nadt.drawandrace.game.active;

import com.nadt.drawandrace.utils.Tools;

public class CarActive extends Sprite {

	public CarActive(float pX, float pY, float pWidth, float pHeight, float pAngle) {
		super(pX, pY, pWidth, pHeight, pAngle);
	}
	
	public void moveWithSpeed(float speed) {
		float angle = super.getAngle();
		float nextX = ((float)Math.cos(Math.toRadians(angle + 90))) * speed;
		float nextY = ((float)Math.sin(Math.toRadians(angle + 90))) * speed;
		Tools.log(this, "Move information : " + angle + " " + nextX + " " + nextY);
		super.setPosition(super.getX() - nextX, super.getY() - nextY);
	}

}
