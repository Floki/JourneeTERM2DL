package com.nadt.drawandrace.game.active;

import android.graphics.drawable.ShapeDrawable;

 

public class Sprite extends ShapeDrawable{

	private float pX;
	private float pY;
	private float pWidth;
	private float pHeight;
	
	public Sprite(float pX, float pY, float pWidth, float pHeight) {
		this.pX = pX;
		this.pY = pY;
		this.pWidth = pWidth;
		this.pHeight = pHeight;
	}
	
	public void setPosition(float x, float y) {
		setPosition(x - this.getWidth()/2, y - this.getHeight()/2);
	}
	
	public float getX() {
		return this.pX;
	}

	public float getY() {
		return this.pY;
	}
	
	public float getWidth() {
		return this.pWidth;
	}
	
	public float getHeight() {
		return this.pHeight;
	}

}
