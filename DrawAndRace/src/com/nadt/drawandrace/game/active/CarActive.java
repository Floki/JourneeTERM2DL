package com.nadt.drawandrace.game.active;

public class CarActive extends Sprite {

	public CarActive(float pX, float pY, float pWidth, float pHeight, float pAngle) {
		super(pX, pY, pWidth, pHeight, pAngle);
	}
	
	public void moveWithSpeed(float speed) {
		float angle = super.getAngle();
		float nextX = (float) (super.getX() + Math.cos(angle) * speed);
		float nextY = (float) (super.getY() + Math.sin(angle) * speed);
		super.setPosition(nextX, nextY);
	}

}
